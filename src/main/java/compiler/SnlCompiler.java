package compiler;

import lexical_analysis.lexer.Lexer;
import lexical_analysis.token.Token;
import lombok.Setter;
import syntax_analysis.facade.Syntaxer;
import syntax_analysis.config.SyntaxerConfig;

import java.io.File;
import java.util.List;

public class SnlCompiler {
    private final Lexer lexer;
    private final Syntaxer syntaxer;
    @Setter
    private File compiledResultPathDirectory;

    public SnlCompiler() {
        this.lexer = new Lexer();
        this.syntaxer = new Syntaxer();
    }

    public void compile(File src) {
        List<Token> tokenList = lexer.generateTokenList(src);
        lexer.write(tokenList, getCompiledTokenFilePath(src));

        syntaxer.analyze(tokenList);
        if (syntaxer.hasError()) {
            System.err.println("编译错误！");
            syntaxer.printErrorInfo();
            System.exit(1);
        }

        syntaxer.writeSyntaxTreeGraph(new File(getCompiledSyntaxerTreeGraphFilePath(src)))
                .writeSyntaxTreeText(new File(getCompiledSyntaxerTreeTextFilePath(src)));
    }

    public void flipSyntaxer(SyntaxerConfig config) {
        syntaxer.filp(config);
    }

    private String getCompiledTokenFilePath(File src) {
        return compiledResultPathDirectory +
                src.getName().replaceFirst("[.][^.]+$", "Token.xlsx");
    }

    private String getCompiledSyntaxerTreeGraphFilePath(File src) {
        return compiledResultPathDirectory + src.getName().replaceFirst("[.][^.]+$", "SyntaxTreeGraph.txt");
    }

    private String getCompiledSyntaxerTreeTextFilePath(File src) {
        return compiledResultPathDirectory + src.getName().replaceFirst("[.][^.]+$", "SyntaxTreeText.txt");
    }
}
