package syntax_analysis.facade;

import lexical_analysis.token.Token;

public abstract class AbstractSyntaxTreeNode {
    protected String type; // 节点类型，例如：INT_LITERAL, IDENTIFIER, BINARY_OPERATION
    protected int line; // 行号
    protected int column; // 列号
    private AbstractSyntaxTreeNode leftChild;
    private AbstractSyntaxTreeNode rightChild;

    public AbstractSyntaxTreeNode(String type, int line, int column) {
        this.type = type;
        this.line = line;
        this.column = column;
    }

    // 获取节点类型
    public String getType() {
        return type;
    }

    // 获取行号
    public int getLine() {
        return line;
    }

    // 获取列号
    public int getColumn() {
        return column;
    }

    public Token getToken() {

        return null;
    }

    public abstract String toString(int depth);

    public void setLeftChild(AbstractSyntaxTreeNode leftChild) {
        this.leftChild = leftChild;
    }



    public void setRightChild(AbstractSyntaxTreeNode rightChild) {
        this.rightChild = rightChild;
    }


    // 可以添加更多的通用方法，例如：toString(), accept()等
}