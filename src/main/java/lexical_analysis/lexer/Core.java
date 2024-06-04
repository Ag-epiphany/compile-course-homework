package lexical_analysis.lexer;

import constant.ProjectConstant;
import lexical_analysis.token.Token;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.util.CharTypeUtil;
import lexical_analysis.util.chain.lexType.util.LexTypeUtil;
import lexical_analysis.util.io.ReaderAndWriter;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static lexical_analysis.lexer.Core.State.*;

public class Core {
    @Target({ElementType.FIELD, ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    private @interface TransitionState {
        CharType[] chars();

        State[] nextStates();
    }

    public interface IState {
        IState getInitialState();

        boolean isTerminalState();

        boolean isError();

        int ordinal0();

        IState valueOf0(String stateName);

        IState transitionState(CharType charType, IState[][] stateTable);
    }


    private static class StateConfig {
        @TransitionState(
                chars = {CharType.LETTER, CharType.DIGIT, CharType.PLUS, CharType.SUBTRACTION,
                        CharType.MULTIPLICATION, CharType.DIVISION, CharType.L_PARENTHESIS, CharType.R_PARENTHESIS,
                        CharType.L_MID_PARENTHESIS, CharType.R_MID_PARENTHESIS, CharType.SEMICOLON, CharType.EQUAL,
                        CharType.LESS_THAN, CharType.COLON, CharType.LCOMMENT, CharType.DOT,
                        CharType.APOSTROPHE, CharType.EOF, CharType.WHITE_SPACE, CharType.COMMA,
                        CharType.EMPTY},

                nextStates = {State.INID, State.INNUM, State.DONE, State.DONE,
                        State.DONE, State.DONE, State.DONE, State.DONE,
                        State.DONE, State.DONE, State.DONE, State.DONE,
                        State.DONE, State.PRE_INASSIGN, State.INCOMMENT, State.INDOT,
                        State.INCHAR, State.PROGRAMME_DONE, DONE, DONE,
                        START}
        )
        String start;

        @TransitionState(
                chars = {CharType.LETTER, CharType.DIGIT, CharType.EMPTY, CharType.EOF,
                        CharType.WHITE_SPACE},
                nextStates = {State.INID, State.INID, State.INIDENTIFIER, State.INIDENTIFIER,
                        State.INIDENTIFIER}
        )
        String inid;

        @TransitionState(
                chars = {CharType.DIGIT, CharType.EMPTY, CharType.EOF, CharType.WHITE_SPACE},
                nextStates = {INNUM, INUNSIGNED_INTEGER, INUNSIGNED_INTEGER, INUNSIGNED_INTEGER}
        )
        String innum;

        @TransitionState(
                chars = {CharType.EQUAL, CharType.EMPTY},
                nextStates = {INASSIGN, PRE_INASSIGN}
        )
        String pre_inassign;

        @TransitionState(
                chars = {CharType.LETTER, CharType.DIGIT, CharType.PLUS, CharType.SUBTRACTION,
                        CharType.MULTIPLICATION, CharType.DIVISION, CharType.L_PARENTHESIS, CharType.R_PARENTHESIS,
                        CharType.L_MID_PARENTHESIS, CharType.R_MID_PARENTHESIS, CharType.SEMICOLON, CharType.COLON,
                        CharType.EQUAL, CharType.LESS_THAN, CharType.LCOMMENT, CharType.RCOMMENT,
                        CharType.DOT, CharType.COMMA, CharType.APOSTROPHE, CharType.EMPTY,
                        CharType.WHITE_SPACE},
                nextStates = {INCOMMENT, INCOMMENT, INCOMMENT, INCOMMENT,
                        INCOMMENT, INCOMMENT, INCOMMENT, INCOMMENT,
                        INCOMMENT, INCOMMENT, INCOMMENT, INCOMMENT,
                        INCOMMENT, INCOMMENT, INCOMMENT, COMMENT_DONE,
                        INCOMMENT, INCOMMENT, INCOMMENT, INCOMMENT,
                        INCOMMENT}
        )
        String incomment;

        @TransitionState(
                chars = {CharType.DOT, CharType.EOF, CharType.EMPTY, CharType.WHITE_SPACE,
                        CharType.LETTER, CharType.DIGIT, CharType.PLUS, CharType.SUBTRACTION,
                        CharType.MULTIPLICATION, CharType.DIVISION, CharType.L_PARENTHESIS, CharType.R_PARENTHESIS,
                        CharType.L_MID_PARENTHESIS, CharType.R_MID_PARENTHESIS, CharType.SEMICOLON, CharType.COLON,
                        CharType.EQUAL, CharType.LESS_THAN, CharType.LCOMMENT, CharType.RCOMMENT,
                        CharType.COMMA, CharType.APOSTROPHE},
                nextStates = {INRANGE, PROGRAMME_DONE, INDOT, PROGRAMME_DONE,
                        PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE,
                        PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE,
                        PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE,
                        PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE, PROGRAMME_DONE,
                        PROGRAMME_DONE, PROGRAMME_DONE}
        )
        String indot;

        @TransitionState(
                chars = {CharType.LETTER, CharType.DIGIT},
                nextStates = {CHAR_DONE, CHAR_DONE}
        )
        String inchar;

        @TransitionState(
                chars = {CharType.APOSTROPHE},
                nextStates = {CHAR}
        )
        String char_done;
    }

    enum State implements IState {
        START(StateStage.INITIAL),              // 初始状态

        /*中间状态*/
        INID(StateStage.INTERMEDIATE),               // 标识符状态
        INNUM(StateStage.INTERMEDIATE),              // 数字状态
        PRE_INASSIGN(StateStage.INTERMEDIATE),       // 预赋值状态
        INCOMMENT(StateStage.INTERMEDIATE),          // 注释状态
        INDOT(StateStage.INTERMEDIATE),              // 点状态
        INCHAR(StateStage.INTERMEDIATE),             // 字符标志状态
        CHAR_DONE(StateStage.INTERMEDIATE),          // 字符完成状态

        /*终止状态*/
        INIDENTIFIER(StateStage.TERMINAL),       // 标识符状态
        INUNSIGNED_INTEGER(StateStage.TERMINAL), // 无符号整数状态
        DONE(StateStage.TERMINAL),               // 完成状态（单分隔符结束状态）
        INASSIGN(StateStage.TERMINAL),           // 赋值状态
        COMMENT_DONE(StateStage.TERMINAL),       // 注释结束状态
        INRANGE(StateStage.TERMINAL),            // 数组下标界限状态
        PROGRAMME_DONE(StateStage.TERMINAL),     // 程序结束状态
        CHAR(StateStage.TERMINAL),               // 字符状态
        ERROR(StateStage.TERMINAL),              // 出错
        ;

        private enum StateStage {
            INITIAL,
            INTERMEDIATE,
            TERMINAL,
        }

        private StateStage stateStage;

        State(StateStage stateStage) {
            this.stateStage = stateStage;
        }

        @Override
        public IState getInitialState() {
            return START;
        }

        @Override
        public boolean isTerminalState() {
            return this.stateStage.equals(StateStage.TERMINAL);
        }

        @Override
        public boolean isError() {
            return this.equals(ERROR);
        }

        @Override
        public int ordinal0() {
            return this.ordinal();
        }

        @Override
        public IState valueOf0(String stateName) {
            return State.valueOf(stateName);
        }

        @Override
        public IState transitionState(CharType charType, IState[][] stateTransitionTable) {
            return stateTransitionTable[this.ordinal()][charType.ordinal()];
        }

        public StateStage getStateStage() {
            return stateStage;
        }
    }

    private State[][] stateTransitionTable;
    private int stateCnt;

    private IState currentState;
    private ReaderAndWriter readerAndWriter;

    public void init(File src) {
        readSrcFile(src);
        this.currentState = START;
        initStateCnt();
        initStateTransitionTable();

    }

    public void readSrcFile() {
        readSrcFile(new File(ProjectConstant.DEFAULT_SOURCE_fILEPATH));
    }

    public void readSrcFile(File src) {
        this.readerAndWriter = new ReaderAndWriter(src);
    }

    public List<Token> analyzeAndGenerateTokenList() {
        List<Token> tokenList = new ArrayList<>();
        char ch = getNextChar();
        CharType charType = CharTypeUtil.parseCharType(ch);
        StringBuilder sb = new StringBuilder();
        currentState = START;
        while (charType != CharType.EOF) {
//            转移状态
            sb.append(ch);
            currentState = currentState.transitionState(charType, stateTransitionTable);

            Token token = tryGetToken(sb, currentState);
            if (token != null) {
                tokenList.add(token);
                currentState = START;
            }

            ch = getNextChar();
            charType = CharTypeUtil.parseCharType(ch);
        }

        currentState = currentState.transitionState(charType, stateTransitionTable);
        Token token = tryGetToken(sb, currentState);
        if (token != null) {
            tokenList.add(token);
        }
        return tokenList;
    }

    public void write(List<Token> tokenList) {
        write(tokenList, ProjectConstant.DEFAULT_TOKEN_EXCEL_FILEPATH);
    }

    public void write(List<Token> tokenList, String targetFilePath) {
        readerAndWriter.write(tokenList, targetFilePath);
    }

    private char getNextChar() {
        return readerAndWriter.read();
    }

    private void setStateTransitionTableElement(State sourceState, CharType charType, State targetState) {
        stateTransitionTable[sourceState.ordinal()][charType.ordinal()] = targetState;
    }

    private void initStateCnt() {
        this.stateCnt = State.values().length;
    }

    private void initStateTransitionTable() {
        stateTransitionTable = new State[stateCnt][CharType.values().length];
        Field[] fields = StateConfig.class.getDeclaredFields();
        for (Field field : fields) {
            TransitionState transitionState = field.getAnnotation(TransitionState.class);
            if (transitionState == null) {
                continue;
            }

            CharType[] chars = transitionState.chars();
            State[] nextStates = transitionState.nextStates();
            State start = State.valueOf(field.getName().toUpperCase());
            for (int i = 0; i < chars.length; i++) {
                setStateTransitionTableElement(start, chars[i], nextStates[i]);
            }
        }

        for (int i = 0; i < stateTransitionTable.length; i++) {
            for (int j = 0; j < stateTransitionTable[0].length; j++) {
                if (stateTransitionTable[i][j] == null) {
                    stateTransitionTable[i][j] = State.ERROR;
                }
            }
        }
    }

    private Token tryGetToken(StringBuilder sb, IState currentState) {
        if (currentState.isTerminalState()) {
            String sbString = sb.toString().replace("\0", "");
            LexType lexType = LexTypeUtil.parseLexType(currentState, sbString);
            Token token = new Token(readerAndWriter.getLineNumber(), lexType, sbString);
            sb.setLength(0);
            return token;
        }

        return null;
    }
}
