package recursion.ASTNode;

import lexical_analysis.token.type.LexType;
import syntax_analysis.facade.AbstractSyntaxTreeNode;
import lexical_analysis.token.Token;

public class BinaryOpNode extends AbstractSyntaxTreeNode {
    private final Token operatorToken; // 运算符的Token
    private AbstractSyntaxTreeNode left; // 左操作数
    private AbstractSyntaxTreeNode right; // 右操作数

    public BinaryOpNode(Token operatorToken, AbstractSyntaxTreeNode left, AbstractSyntaxTreeNode right) {
        super("BINARY_OPERATION", operatorToken.getLineShow(), 1); // 同上，使用1作为默认列号
        this.operatorToken = operatorToken;
        this.left = left;
        this.right = right;
    }


    // 获取运算符Token
    public Token getOperatorToken() {
        return operatorToken;
    }

    // 获取左操作数
    public AbstractSyntaxTreeNode getLeft() {
        return left;
    }

    // 设置左操作数
    public void setLeft(AbstractSyntaxTreeNode left) {
        this.left = left;
    }

    // 获取右操作数
    public AbstractSyntaxTreeNode getRight() {
        return right;
    }

    // 设置右操作数
    public void setRight(AbstractSyntaxTreeNode right) {
        this.right = right;
    }

    @Override
    public String toString(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(depth)).append("BINARY_OPERATION [line ").append(line).append("]: ").append(operatorToken.getSem()).append("\n");
        sb.append(left.toString(depth + 1));
        sb.append(right.toString(depth + 1));
        return sb.toString();
    }

    private String indent(int depth) {
        return " ".repeat(depth * 2);
    }
}