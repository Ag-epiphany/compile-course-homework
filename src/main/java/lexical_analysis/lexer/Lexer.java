package lexical_analysis.lexer;

import lexical_analysis.token.Token;

import java.io.File;
import java.util.List;

public class Lexer {
    private Core core;

    public Lexer() {
        this.core = new Core();
    }

    public List<Token> generateTokenList(File file) {
        core.init(file);
        return core.analyzeAndGenerateTokenList();
    }

    public List<Token> generateTokenList(String filepath) {
        return generateTokenList(new File(filepath));
    }

    public void write(List<Token> tokenList) {
//        todo 暂不支持
    }
}
