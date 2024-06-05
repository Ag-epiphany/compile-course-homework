package recursion.ASTNode;

import syntax_analysis.facade.AbstractSyntaxTreeNode;
import lexical_analysis.token.Token;

public class IdentifierNode extends AbstractSyntaxTreeNode {
    private final Token token; // 保存原始Token

    public IdentifierNode(Token token) {
        super("IDENTIFIER", token.getLineShow(), 1); // 同上，使用1作为默认列号
        this.token = token;
    }

    // 从Token获取标识符名称
    public String getName() {
        return token.getSem();
    }

    // 获取Token
    public Token getToken() {
        return token;
    }

    public String toString(int depth) {
        return indent(depth) + "IDENTIFIER [line " + line + "]: " + getName() + "\n";
    }

    private String indent(int depth) {
        return " ".repeat(depth * 2);
    }
}