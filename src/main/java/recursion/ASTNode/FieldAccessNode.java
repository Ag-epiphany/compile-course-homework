package recursion.ASTNode;

import lexical_analysis.token.Token;
import syntax_analysis.facade.AbstractSyntaxTreeNode;


public class FieldAccessNode extends AbstractSyntaxTreeNode {
    private AbstractSyntaxTreeNode objectExpression;
    private String fieldName;
    private Token token;

    public FieldAccessNode(Token token, AbstractSyntaxTreeNode objectExpression, String fieldName) {
        super("FIELD_ACCESS", token.getLineShow(), 0); // 使用列号0作为默认值，因为Token类中没有getColumn()方法
        this.objectExpression = objectExpression;
        this.fieldName = fieldName;
        this.token = token;
    }

    public AbstractSyntaxTreeNode getObjectExpression() {
        return objectExpression;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent(depth)).append("FIELD_ACCESS [line ").append(line).append("]: ").append(fieldName).append("\n");
        sb.append(objectExpression.toString(depth + 1));
        return sb.toString();
    }

    private String indent(int depth) {
        return " ".repeat(depth * 2);
    }

}