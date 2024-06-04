package syntax_analysis.ll1.Adapter;

import syntax_analysis.facade.AbstractSyntaxerImpl;
import syntax_analysis.ll1.ll1;

import java.io.File;

import static syntax_analysis.ll1.ll1.storeGraph;
import static syntax_analysis.ll1.ll1.storeTxt;

public class ImplAdapter extends AbstractSyntaxerImpl {


    @Override
    public void printErrorInfo() {
        ll1.printError();
    }

    // 输出文本格式
    @Override
    public void printSyntaxTreeText() {
        ll1.text();
    }

    // 输出文件树格式
    @Override
    public void printSyntaxTreeGraph() {
        ll1.graph();
    }

    //  持久化语法树
    @Override
    public void writeTree(File targetFile) {

    }

    //  持久化文本语法树
    @Override
    public void writeSyntaxTreeText(File targetFile) {

        storeTxt(targetFile.getAbsolutePath());

    }

    //  持久化图形语法树
    @Override
    public void writeSyntaxTreeGraph(File tagetFile) {
        storeGraph(tagetFile.getAbsolutePath());
    }

    @Override
    public boolean hasError() {
        return false;
    }
}
