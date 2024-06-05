package recursion.ASTNode;

import syntax_analysis.facade.AbstractSyntaxTreeNode;
import lexical_analysis.token.Token;

public class IntLiteralNode extends AbstractSyntaxTreeNode {
    private final Token token; // 保存原始Token

    public IntLiteralNode(Token token) {
        super("INT_LITERAL", token.getLineShow(), 1); // 假设列号不重要，使用1作为默认值
        this.token = token;
    }

    // 从Token获取整数值
    public int getValue() {
        try {
            return Integer.parseInt(token.getSem());
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Cannot parse integer from token: " + token.getSem());
        }
    }

    // 获取Token
    public Token getToken() {
        return token;
    }

    @Override
    public String toString(int depth) {
        return indent(depth) + "INT_LITERAL [line " + line + "]: " + getValue() + "\n";
    }

    private String indent(int depth) {
        return " ".repeat(depth * 2);
    }

}