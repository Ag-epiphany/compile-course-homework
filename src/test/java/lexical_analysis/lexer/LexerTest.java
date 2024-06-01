package lexical_analysis.lexer;

import constant.ProjectConstant;
import lexical_analysis.token.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LexerTest {
    @Test
    public void testCore() {
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);
        for (Token token : tokenList) {
            System.out.println(token);
        }
    }

    @Test
    public void testWrite() {
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);
        lexer.write();
    }

    @Test
    public void testSimpleName() {
        String str = "a\\b\\c.txt";
        String s = str.substring(str.lastIndexOf('\\') + 1);
        Assertions.assertEquals("c.txt", s);
    }
}
