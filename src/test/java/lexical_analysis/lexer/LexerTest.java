package lexical_analysis.lexer;

import Constant.ProjectConstant;
import lexical_analysis.token.Token;
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
}
