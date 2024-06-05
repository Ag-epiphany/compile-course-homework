package recursion.ASTNode;

import lexical_analysis.token.Token;
import syntax_analysis.facade.AbstractSyntaxTreeNode;

public class ArrayAccessNode extends AbstractSyntaxTreeNode {
    private AbstractSyntaxTreeNode arrayExpression;
    private AbstractSyntaxTreeNode indexExpression;
    private Token token;

    public ArrayAccessNode(Token token, AbstractSyntaxTreeNode arrayExpression, AbstractSyntaxTreeNode indexExpression) {
        super("ARRAY_ACCESS", token.getLineShow(), 0); // 使用列号0作为默认值，因为Token类中没有getColumn()方法
        this.arrayExpression = arrayExpression;
        this.indexExpression = indexExpression;
        this.token = token;
    }

    public AbstractSyntaxTreeNode getArrayExpression() {
        return arrayExpression;
    }

    public AbstractSyntaxTreeNode getIndexExpression() {
        return indexExpression;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(depth)).append("ARRAY_ACCESS [line ").append(line).append("]:\n");
        sb.append(arrayExpression.toString(depth + 1));
        sb.append(indexExpression.toString(depth + 1));
        return sb.toString();
    }

    private String indent(int depth) {
        return " ".repeat(depth * 2);
    }

}
