package syntax_analysis.facade;

import java.io.File;

abstract public class AbstractSyntaxerImpl {
    abstract public void printErrorInfo();                  //  标准输出错误信息

    abstract public void printSyntaxTreeText();             //  标准输出语法树

    abstract public void printSyntaxTreeGraph();            //  标准输出语法树

    abstract public void writeTree(File targetFile);        //  持久化语法树

    abstract public void writeSyntaxTreeText(File targetFile);  //  持久化文本语法树

    abstract public void writeSyntaxTreeGraph(File tagetFile);  //  持久化图形语法树

    abstract public boolean hasError();
}
