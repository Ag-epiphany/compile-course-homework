package TestLL1;

import constant.ProjectConstant;
import lexical_analysis.lexer.Lexer;
import lexical_analysis.token.Token;
import org.junit.jupiter.api.Test;
import syntax_analysis.config.SyntaxerConfig;
import syntax_analysis.facade.Syntaxer;
import syntax_analysis.ll1.Adapter.CoreAdapter;
import syntax_analysis.ll1.Adapter.ImplAdapter;

import java.util.List;

public class TestLL1 {
    @Test
    public void test(){
        Lexer lexer = new Lexer();

        List<Token> token = lexer.generateTokenList(ProjectConstant.DEFAULT_SOURCE_fILEPATH);

        token = lexer.filter();


        System.out.println(token);

        SyntaxerConfig sc = new SyntaxerConfig(new CoreAdapter(),new ImplAdapter());

        Syntaxer syntaxer = new Syntaxer(sc);

        syntaxer.analyze(token);

        syntaxer.printSyntaxTreeGraph();


    }
}
