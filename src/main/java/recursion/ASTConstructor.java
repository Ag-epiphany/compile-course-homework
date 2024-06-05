package recursion;

import java.util.List;
import syntax_analysis.facade.AbstractSyntaxTreeNode;
import lexical_analysis.token.Token;
import recursion.ASTNode.BinaryOpNode;
import recursion.ASTNode.IdentifierNode;
import recursion.ASTNode.IntLiteralNode;
import recursion.ASTNode.ArrayAccessNode;
import recursion.ASTNode.FieldAccessNode;


public class ASTConstructor {

    /**
     * 从标记列表构建AST。
     * 注意：这是一个简化的示例，实际应用中需要更详细的逻辑。
     *
     * @param tokens 标记列表
     * @return 构建好的AST节点
     */
    public static AbstractSyntaxTreeNode constructFromTokens(List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("Token list cannot be empty.");
        }

        Token firstToken = tokens.get(0);

        if ("BINARY_OPERATION".equals(firstToken.getLex().toString())) {
            // 假设接下来的两个标记是左右操作数
            // 这里需要进一步的逻辑来解析左右操作数，例如递归调用本方法
            AbstractSyntaxTreeNode left = constructFromTokens(tokens.subList(1, tokens.size() / 2));
            AbstractSyntaxTreeNode right = constructFromTokens(tokens.subList(tokens.size() / 2 + 1, tokens.size()));
            return new BinaryOpNode(firstToken, left, right);
        } else if ("IDENTIFIER".equals(firstToken.getLex().toString())) {
            return new IdentifierNode(firstToken);
        } else if ("INT_LITERAL".equals(firstToken.getLex().toString())) {
            return new IntLiteralNode(firstToken);
        } else if ("ARRAY_ACCESS".equals(firstToken.getLex().toString())) {
            // 类似地，解析数组访问的左右子表达式
            AbstractSyntaxTreeNode arrayExpr = constructFromTokens(tokens.subList(1, tokens.size() / 2));
            AbstractSyntaxTreeNode indexExpr = constructFromTokens(tokens.subList(tokens.size() / 2 + 1, tokens.size()));
            return new ArrayAccessNode(firstToken, arrayExpr, indexExpr);
        } else if ("FIELD_ACCESS".equals(firstToken.getLex().toString())) {
            // 解析字段访问的对象表达式
            AbstractSyntaxTreeNode objectExpr = constructFromTokens(tokens.subList(1, tokens.size() - 1));
            String fieldName = tokens.get(tokens.size() - 1).getSem(); // 假设字段名是最后一个标记
            return new FieldAccessNode(firstToken, objectExpr, fieldName);
        } else {
            throw new IllegalArgumentException("Unsupported token type: " + firstToken.getLex());
        }
    }
}