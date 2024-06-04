package syntax_analysis.ll1.util;

import lexical_analysis.lexer.Lexer;
import lexical_analysis.token.Token;
import syntax_analysis.ll1.Adapter.CoreAdapter;

import java.util.ArrayList;
import java.util.List;

import static constant.ProjectConstant.DEFAULT_SOURCE_fILEPATH;
import static constant.ProjectConstant.DEFAULT_TOKEN_EXCEL_FILEPATH;

public class InputTokens {
    private static List<Token> tokenList;

    private static void init(){
        tokenList = new CoreAdapter().getTokenList();
    }
    public static List<String> getInputTokens(){
        init();
        List<String> arr = new ArrayList<>();
        for(Token token : tokenList){
            arr.add(token.getLex().toString());;
        }
        return arr;
    }

    public static List<Integer> getLineofToken(){
        List<Integer> arr = new ArrayList<>();
        for(Token token : tokenList){
            arr.add(token.getLineShow());;
            //System.out.println(token.getLineShow());
        }

        return arr;
    }
}
