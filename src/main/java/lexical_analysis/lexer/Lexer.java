package lexical_analysis.lexer;

import constant.ProjectConstant;
import lexical_analysis.token.Token;

import java.io.File;
import java.util.List;

public class Lexer {
    private Core core;
    private List<Token> tokenList;

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
}
