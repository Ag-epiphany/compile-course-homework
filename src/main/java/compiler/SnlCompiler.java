package compiler;

import lexical_analysis.lexer.Lexer;
import lexical_analysis.token.Token;
import lombok.Setter;
import syntax_analysis.config.SyntaxerAutoConfig;
import syntax_analysis.facade.AbstractSyntaxerCore;
import syntax_analysis.facade.AbstractSyntaxerImpl;
import syntax_analysis.facade.Syntaxer;
import syntax_analysis.config.SyntaxerConfig;
import syntax_analysis.ll1.Adapter.CoreAdapter;
import syntax_analysis.ll1.Adapter.ImplAdapter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SyntaxerAutoConfig(
        cores = {CoreAdapter.class,},
        impls = {ImplAdapter.class,}
)
public class SnlCompiler {
    private final Lexer lexer;
    private final Syntaxer syntaxer;
    @Setter
    private File compiledResultPathDirectory;
    private List<SyntaxerConfig> configs;
    private int configIndex;

    public SnlCompiler() {
        this.lexer = new Lexer();
        this.syntaxer = new Syntaxer();
        this.configs = new ArrayList<>();
        this.configIndex = 0;

        SyntaxerAutoConfig syntaxerAutoConfig = this.getClass().getAnnotation(SyntaxerAutoConfig.class);
        Class<? extends AbstractSyntaxerCore>[] cores = syntaxerAutoConfig.cores();
        Class<? extends AbstractSyntaxerImpl>[] impls = syntaxerAutoConfig.impls();
        int size = cores.length;
        for (int i = 0; i < size; i++) {
            try {
                AbstractSyntaxerCore core = cores[i].getConstructor().newInstance();
                AbstractSyntaxerImpl impl = impls[i].getConstructor().newInstance();
                SyntaxerConfig config = new SyntaxerConfig(core, impl);
                configs.add(config);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void compile(File src, int index) {
        List<Token> tokenList = lexer.generateTokenList(src);
        lexer.write(tokenList, getCompiledTokenFilePath(src));

        syntaxer.filp(configs.get(index % configs.size()));
        syntaxer.analyze(tokenList);
        if (syntaxer.hasError()) {
            System.err.println("编译错误！");
            syntaxer.printErrorInfo();
            System.exit(1);
        }

        syntaxer.writeSyntaxTreeGraph(new File(getCompiledSyntaxerTreeGraphFilePath(src)))
                .writeSyntaxTreeText(new File(getCompiledSyntaxerTreeTextFilePath(src)));
    }

    public void flipSyntaxer() {
        syntaxer.filp(configs.get(configIndex));
        configIndex = (configIndex + 1) % configs.size();
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
