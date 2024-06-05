package recursion;

import syntax_analysis.facade.AbstractSyntaxerImpl;
import syntax_analysis.facade.AbstractSyntaxerCore;
import syntax_analysis.facade.AbstractError;
import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

public class SyntaxerImpl extends AbstractSyntaxerImpl {

    private AbstractSyntaxerCore core;

    public SyntaxerImpl(AbstractSyntaxerCore core) {
        this.core = core;
    }

    @Override
    public boolean hasError() {
        // 返回errorList是否为空的相反值，如果errorList不为空，说明有错误
        return !core.getErrorList().isEmpty();
    }

    @Override
    public void printErrorInfo() {
        List<AbstractError> errors = core.getErrorList();
        for (AbstractError error : errors) {
            System.out.println(error.getMessage());
        }
    }

    @Override
    public void printSyntaxTreeText() {
        ASTConstructor astConstructor = new ASTConstructor();
        System.out.println(core.getSyntaxTree().toText());
    }

    @Override
    public void printSyntaxTreeGraph() {
        System.out.println(core.getSyntaxTree().toGraph());
    }

    @Override
    public void writeTree(File targetFile) {
        // 保存语法树到文件的代码
    }

    @Override
    public void writeSyntaxTreeText(File targetFile) {
        // 保存语法树文本表示到文件的代码
        try (PrintWriter writer = new PrintWriter(new FileWriter(targetFile))) {
            writer.print(core.getSyntaxTree().toText());
        } catch (IOException e) {
            System.err.println("Error writing syntax tree text to file: " + e.getMessage());
        }
    }

    @Override
    public void writeSyntaxTreeGraph(File targetFile) {
        // 保存语法树图形表示到文件的代码
        try (PrintWriter writer = new PrintWriter(new FileWriter(targetFile))) {
            writer.print(core.getSyntaxTree().toGraph());
        } catch (IOException e) {
            System.err.println("Error writing syntax tree graph to file: " + e.getMessage());
        }
    }
}