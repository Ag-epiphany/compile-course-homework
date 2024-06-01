package syntax_analysis.facade;

import constant.ProjectConstant;
import lexical_analysis.token.Token;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Syntaxer {
    protected AbstractSyntaxerCore core;
    protected AbstractSyntaxerImpl syntaxerImpl;

    /*语法分析*/
    public Syntaxer analyze(List<Token> tokenList) {
        core.read(tokenList);
        core.analyze();
        return this;
    }

    /*打印错误信息*/
    public Syntaxer printErrorInfo() {
        syntaxerImpl.printErrorInfo();
        return this;
    }

    /*打印语法树*/
    public Syntaxer printSyntaxTreeText() {
        syntaxerImpl.printSyntaxTreeText();
        return this;
    }

    public Syntaxer printSyntaxTreeGraph() {
        syntaxerImpl.printSyntaxTreeGraph();
        return this;
    }

    public Syntaxer writeTree(File targetFile) {
        syntaxerImpl.writeTree(targetFile);
        return this;
    }

    public Syntaxer writeSyntaxTreeText() {
        return writeSyntaxTreeText(new File(ProjectConstant.DEFAULT_SYNTAX_TREE_TEXT_FILEPATH));
    }

    public Syntaxer writeSyntaxTreeText(File targetFile) {
        syntaxerImpl.writeSyntaxTreeText(targetFile);
        return this;
    }

    public Syntaxer writeSyntaxTreeGraph() {
        return writeSyntaxTreeGraph(new File(ProjectConstant.DEFAULT_SYNTAX_TREE_GRAPH_FILEPATH));
    }

    public Syntaxer writeSyntaxTreeGraph(File targetFile) {
        syntaxerImpl.writeSyntaxTreeGraph(targetFile);
        return this;
    }
}
