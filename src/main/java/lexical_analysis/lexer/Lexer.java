package lexical_analysis.lexer;

import constant.ProjectConstant;
import lexical_analysis.token.Token;
import lexical_analysis.token.type.Comment;
import lexical_analysis.token.type.LexType;
import lexical_analysis.token.type.WhiteSpace;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lexer {
    private Core core;
    private List<Token> tokenList;

    public interface Filter {
        boolean filter(Token token);
    }

    public Lexer() {
        this.core = new Core();
    }

    public List<Token> generateTokenList(File file) {
        core.init(file);
        tokenList = core.analyzeAndGenerateTokenList();
        return tokenList;
    }

    public List<Token> generateTokenList(String filepath) {
        return generateTokenList(new File(filepath));
    }

    public void write() {
        write(this.tokenList);
    }

    public void write(List<Token> tokenList) {
        write(tokenList, ProjectConstant.DEFAULT_TOKEN_EXCEL_FILEPATH);
    }

    public void write(List<Token> tokenList, String targetFilePath) {
        core.write(tokenList, targetFilePath);
    }

    public List<Token> filter() {
        return filter(Objects.requireNonNull(this.tokenList),
                token -> {
                    LexType lexType = token.getLex();
                    return !(lexType instanceof Comment) && !(lexType instanceof WhiteSpace);
                });
    }

    public List<Token> filter(List<Token> tokenList, Filter filter) {
        List<Token> filteredTokenList = new ArrayList<>();
        for (Token token : tokenList) {
            if (filter.filter(token)) {
                filteredTokenList.add(token);
            }
        }

        return filteredTokenList;
    }
}
