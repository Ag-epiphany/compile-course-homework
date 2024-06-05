package syntax_analysis;

import constant.ProjectConstant;
import lexical_analysis.lexer.Lexer;
import lexical_analysis.token.Token;
import org.junit.jupiter.api.Test;
import recursion.SyntaxerCore;
import recursion.SyntaxerImpl;
import syntax_analysis.config.SyntaxerConfig;
import syntax_analysis.facade.Syntaxer;

import java.util.List;

public class SyntaxTest {
    @Test
    public void testCore() {
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);
        tokenList=lexer.filter();
        SyntaxerCore syn = new SyntaxerCore(tokenList);

        for (Token token : tokenList) {
            System.out.println(token);
        }
    }

    @Test
    public void testAnal() {
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);
        tokenList=lexer.filter();
        SyntaxerCore syn = new SyntaxerCore(tokenList);

        syn.analyze();

    }

    @Test
    public void testLextype() {
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);
        tokenList=lexer.filter();
        Token token = tokenList.get(0);
        System.out.println(token.getLex().toString());
    }

    @Test
    public void testSynCore() {

        SyntaxerConfig config=new SyntaxerConfig();
        Lexer lexer = new Lexer();
        List<Token> tokenList = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);
        tokenList=lexer.filter();
        SyntaxerCore syncore = new SyntaxerCore(tokenList);
        SyntaxerImpl synimpl=new SyntaxerImpl(syncore);
        config.setCore(syncore);
        config.setSyntaxerImpl(synimpl);
        Syntaxer syn = new Syntaxer(config);

        syn.analyze(tokenList);
        //syn.printErrorInfo();
        //syn.printSyntaxTreeText();

    }

}