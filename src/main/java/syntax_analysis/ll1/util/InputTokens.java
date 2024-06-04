package syntax_analysis.ll1.util;

import lexical_analysis.lexer.Lexer;
import lexical_analysis.token.Token;

import java.util.ArrayList;
import java.util.List;

import static constant.ProjectConstant.DEFAULT_SOURCE_fILEPATH;
import static constant.ProjectConstant.DEFAULT_TOKEN_EXCEL_FILEPATH;

public class InputTokens {
    private static List<Token> tokenList;

    private static void init(){
        //	获取Lexer
        Lexer lexer = new Lexer();
        String srcFilePath = DEFAULT_SOURCE_fILEPATH;
        tokenList = lexer.generateTokenList(srcFilePath);  // 获取srcFile的原始Token序列
        List<Token> filteredTokenList = lexer.filter(tokenList,token->true);//实现自定义过滤器过滤Token序列，lexer内置提供了默认的过滤器，将注释和空白符类型token过滤掉

        String targetPath = DEFAULT_TOKEN_EXCEL_FILEPATH;
        lexer.write(tokenList, targetPath);    //	持久化解析出来的Token序列
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
