package recursion;

import lexical_analysis.token.Token;
import lexical_analysis.token.type.EndDelimiter;
import lombok.SneakyThrows;
import recursion.ASTNode.BinaryOpNode;
import recursion.ASTNode.IdentifierNode;
import recursion.ASTNode.IntLiteralNode;
import syntax_analysis.facade.AbstractSyntaxTreeNode;
import syntax_analysis.facade.AbstractSyntaxerCore;
import syntax_analysis.facade.AbstractSyntaxTree;
import recursion.ASTNode.ArrayAccessNode;
import recursion.ASTNode.FieldAccessNode;

import java.util.ArrayList;
import java.util.List;

public class SyntaxerCore extends AbstractSyntaxerCore {

    private int currentPosition = 0; // 当前正在处理的Token位置

    public SyntaxerCore(List<Token> tokenList) {
        this.tokenList = tokenList;
        this.errorList = new ArrayList<>();
        this.syntaxTree = null;
    }

    @Override
    public boolean hasError() {
        // 返回errorList是否为空的相反值，如果errorList不为空，说明有错误
        return !errorList.isEmpty();
    }

    @Override
    public void read(List<Token> tokenList) {
        // 清空现有的Token列表，以防之前有遗留的Token
        this.tokenList.clear();
        // 将传入的Token列表复制给类内部的Token列表
        this.tokenList.addAll(tokenList);
        // 初始化当前位置
        this.currentPosition = 0;

    }

    @Override
    public void analyze() {
        // 开始语法分析
        // 设置语法树为空对象
        this.syntaxTree = new AbstractSyntaxTree();
        // 调用主解析方法，开始递归解析
        parseProgram();
        // 检查是否有未解析的Token
        if (currentPosition < tokenList.size()) {
            // 如果还有剩余的Token未被解析，这是一个错误
            errorList.add(new SyntaxerError("Unparsed tokens remaining", currentPosition));
        }
        //System.out.println("Syntax Tree : " + syntaxTree);
    }

    private void parseProgram() {
        // 语法分析的入口点，解析整个程序
        // 这里可以调用解析子程序处理程序头部、声明部分和主体
        // 例如：
        parseProgramHead();
        parseDeclarePart();
        parseProgramBody();
    }

    private void parseProgramHead() {
        // 解析程序头部的逻辑
        // 检查并匹配相应的Token，递归调用其他解析子程序
        // ...
        match("PROGRAM"); // 匹配"PROGRAM"
        parseProgramName();
        //System.out.println("PROGRAM");
    }

    private void parseProgramName() {
        match("IDENTIFIER"); // 匹配标识符
        //System.out.println("IDENTIFIER");
    }


   private void parseDeclarePart() {
        // 解析声明部分的逻辑
        parseTypeDec();
        parseVarDec();
        parseProcDec();
    }

    private void parseTypeDec() {
        if (lookaheadIs("TYPE")) {
            match("TYPE");
            parseTypeDeclaration();
        }else {
            parseEpsilon(); // 处理可选的空串
        }
    }


    private void parseEpsilon() {//空格
        // 不执行任何操作，这表示当前规则可以匹配空串
    }

    private void parseTypeDeclaration() {
        parseTypeDecList();
    }

    private void parseTypeDecList() {
        parseTypeId();
        match("="); // 匹配"="
        parseTypeName();
        match(";"); // 匹配";"
        parseTypeDecMore();
    }

    private void parseTypeDecMore() {
        if (lookaheadIs("IDENTIFIER") || lookaheadIs("TYPE")) { // 检查下一个Token是否是标识符或'TYPE'
            parseTypeDecList(); // 如果是，继续解析类型定义列表
        }
    }

    private void parseTypeId() {
        match("IDENTIFIER"); // 匹配并消费标识符Token
    }

    // 解析类型名(TypeName)，它可以是基本类型、数组类型、记录类型或用户定义的类型
    private void parseTypeName() {
        if (lookaheadIs("INTEGER") || lookaheadIs("CHAR")) {
            match("INTEGER"); // 或者 match("CHAR"), 根据实际情况选择
        } else if (lookaheadIs("ARRAY")) {
            parseArrayType();
        } else if (lookaheadIs("RECORD")) {
            parseRecType();
        } else {
            match("IDENTIFIER");
        }
    }

    // 解析数组类型
    private void parseArrayType() {
        match("ARRAY");
        match("[");
        parseLow();
        match("..");
        parseTop();
        match("]");
        match("OF");
        parseTypeName();
    }

//    // 解析表达式的因子
//    private AbstractSyntaxTreeNode parseFactor() {
//        Token currentToken = lookahead();
//        if (currentToken.getLex().toString().equals("INTC")) {
//            return new IntLiteralNode(consume("INTC"));
//        } else if (currentToken.getLex().toString().equals("IDENTIFIER")) {
//            return new IdentifierNode(consume("IDENTIFIER"));
//        } else if (currentToken.getLex().toString().equals("LPAREN")) {
//            consume("LPAREN");
//            AbstractSyntaxTreeNode expr = parseLow(); // 假设 parseLow 是正确的解析方法
//            consume("RPAREN");
//            return expr;
//        } else {
//            throw new SyntaxerError("Unexpected token while parsing factor", currentPosition);
//        }
//    }

    // 解析表达式的因子
    private AbstractSyntaxTreeNode parseFactor() {
        Token currentToken = null;
        currentToken = lookahead();
        if (currentToken.getLex().toString().equals("INTC")) {
            return new IntLiteralNode(consume("INTC"));
        } else if (currentToken.getLex().toString().equals("IDENTIFIER")) {
            // 调用parseVariable来解析变量及其后缀
            AbstractSyntaxTreeNode varNode = parseVariable();
            return varNode;
        } else if (currentToken.getLex().toString().equals("LPAREN")) {
            consume("LPAREN");
            AbstractSyntaxTreeNode expr = parseLow();
            consume("RPAREN");
            return expr;
        } else {
            throw new SyntaxerError("Unexpected token while parsing factor", currentPosition);
        }
    }

    // 解析低优先级表达式
    private AbstractSyntaxTreeNode parseLow() {
        AbstractSyntaxTreeNode left = parseTop();
        while (lookaheadIs("LOW_OP")) {
            Token op = consume("LOW_OP");
            AbstractSyntaxTreeNode right = parseTop();
            left = new BinaryOpNode(op, left, right);
        }
        return left;
    }

    // 解析高优先级表达式
    private AbstractSyntaxTreeNode parseTop() {
        AbstractSyntaxTreeNode left = parseFactor();
        while (lookaheadIs("TOP_OP")) {
            Token op = consume("TOP_OP");
            AbstractSyntaxTreeNode right = parseFactor();
            left = new BinaryOpNode(op, left, right);
        }
        return left;
    }
    private void parseRecType() {
        match("RECORD");
        parseFieldDecList();
        match("END");
    }

    // 解析字段声明列表
    private void parseFieldDecList() {
        parseTypeName();
        parseIdList();
        match(";");
        parseFieldDecMore();
    }

    // 解析字段声明列表的后续部分
    private void parseFieldDecMore() {
        if (lookaheadIs("INTEGER") || lookaheadIs("CHAR") || lookaheadIs("ARRAY") || lookaheadIs("RECORD")) {
            parseFieldDecList();
        }
    }

    // 解析标识符列表
    private void parseIdList() {
        match("IDENTIFIER");
        parseIdMore();
    }

    // 解析标识符列表的后续部分
    private void parseIdMore() {
        if (lookaheadIs(",")) {
            match(",");
            parseIdList();
        }
    }



    // 解析变量声明部分
    private void parseVarDec() {
        // VarDec的规则可能需要根据具体文法进行解析
        if (lookaheadIs("VAR")) {
            match("VAR");
            parseVarDecList();
        } else {
            parseEpsilon(); // 如果VAR不是下一个Token，则视为可选的空串
        }
    }

    // 解析变量声明列表
    private void parseVarDecList() {
        // VarDecList的规则可能包含多个变量声明
        parseVarIdList();
        match(":");
        parseTypeName();
        match(";");
        parseVarDecMore();
    }

    // 解析变量声明列表的后续部分
    private void parseVarDecMore() {
        if (lookaheadIs("IDENTIFIER")) {
            parseVarDecList(); // 如果下一个Token是标识符，继续解析变量声明列表
        }
    }

    // 解析变量标识符列表
    private void parseVarIdList() {
        match("IDENTIFIER"); // 匹配并消费第一个标识符
        parseVarIdMore();
    }

    // 解析变量标识符列表的后续部分
    private void parseVarIdMore() {
        if (lookaheadIs(",")) {
            match(","); // 匹配并消费逗号
            parseVarIdList(); // 继续解析变量标识符列表
        }
    }

    // 解析过程声明部分
    private void parseProcDec() {
        // ProcDec的规则可能需要根据具体文法进行解析
        if (lookaheadIs("PROCEDURE")) {
            match("PROCEDURE");
            parseProcName();
            parseProcFormalParams();
            parseProcBody();
        } else {
            parseEpsilon(); // 如果PROCEDURE不是下一个Token，则视为可选的空串
        }
    }

    // 解析过程名称
    private void parseProcName() {
        match("IDENTIFIER"); // 匹配并消费过程名称标识符
    }

    // 解析过程的形式参数
    private void parseProcFormalParams() {
        // 这里可能需要解析形式参数列表
        if (lookaheadIs("(")) {
            match("(");
            parseParamList();
            match(")");
        }
    }

    // 解析参数列表
    private void parseParamList() {
        // ParamList的规则可能包含多个参数声明
        parseParam();
        parseParamMore();
    }

    // 解析参数列表的后续部分
    private void parseParamMore() {
        if (lookaheadIs(",")) {
            match(",");
            parseParamList();
        }
    }

    // 解析单个参数
    private void parseParam() {
        parseTypeName();
        parseVarIdList();
    }

    // 解析过程体
    private void parseProcBody() {
        parseBlock();
    }

    // 解析块
    private void parseBlock() {
        // Block的规则可能包含声明部分和语句列表
        parseDeclarePart();
        parseStatementList();
    }

    private void parseStatementList() {
        while (lookaheadIs("STATEMENT_START")) { // 这里的"STATEMENT_START"应该替换为你语言中语句开始的标记
            parseStatement(); // 假设你已经有了parseStatement方法来解析单个语句
        }
    }
    private void parseStatement() {
        Token currentToken = lookahead();;
//        try {
//            currentToken = lookahead();
//        } catch (EndOfFileException e) {
//            throw new RuntimeException(e);
//        }

        if (currentToken.getLex().equals("EOF")|| currentToken.getLex() == EndDelimiter.EOF) {
            // 到达文件结尾，结束解析
            return;
        }

        if (currentToken.getLex().toString().equals("IF")) {
            parseConditionalStm();
        } else if (currentToken.getLex().toString().equals("WHILE")) {
            parseLoopStm();
        } else if (currentToken.getLex().toString().equals("READ")) {
            parseInputStm();
        } else if (currentToken.getLex().toString().equals("WRITE")) {
            parseOutputStm();
        } else if (currentToken.getLex().toString().equals("RETURN")) {
            parseReturnStm();
        } else if (currentToken.getLex().toString().equals("IDENTIFIER")) {
            parseIdentifierStm();
        } else {
            try {
                throw new SyntaxException("Unexpected token in statement: " + currentToken.getLex());
            } catch (SyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void parseConditionalStm() {
        match("IF");
        parseRelExp();
        match("THEN");
        parseStmList();
        match("ELSE");
        parseStmList();
        match("FI");
    }

    private void parseRelExp() {
        parseExp();
        match("CMP_OP"); // CMP_OP 应该是你定义的比较运算符的类型
        parseExp();
    }

//    private void parseExp() {
//        parseTerm();
//        while ("ADD_OP".equals(lookahead().getLex().toString())) { // ADD_OP 是加减运算符的类型
//            match("ADD_OP");
//            parseTerm();
//        }
//    }

    private void parseTerm() {
        // 实现解析术语（term）的逻辑
        // 这可能涉及到解析因子（factor）、操作符（operator）等
        // 示例：
        parseFactor();
        while (true) {
            if (!("MUL_OP".equals(lookahead().getLex().toString()) || "DIV_OP".equals(lookahead().getLex().toString())))
                break;
            match(lookahead().getLex().toString());
            parseFactor();
        }
    }

    private void parseStmList() {
//        while (!"END".equals(lookahead().getLex().toString())) { // "END" 应该是结束的标记
            parseStatement();
//        }

        parseStmMore();
    }

    // 解析语句列表的后续部分
    private void parseStmMore() {
        if (lookaheadIs(";")) {
            match(";");
            parseStmList();
        }
    }

    private void parseLoopStm() {
        match("WHILE");
        parseRelExp();
        match("DO");
        parseStmList();
        match("ENDWH");
    }

    private void parseInvar() {
        match("IDENTIFIER");
    }

    private void parseInputStm() {
        match("READ");
        match("(");
        parseInvar();
        match(")");
    }

    private void parseOutputStm() {
        match("WRITE");
        match("(");
        parseExp();
        match(")");
    }

    private void parseReturnStm() {
        match("RETURN");
        match("(");
        parseExp();
        match(")");
    }

    private void parseIdentifierStm() {
        Token identifierToken = match("IDENTIFIER");
        String identifier = "";
        if (identifierToken != null) {
            // 原始代码，调用getSem()
            identifier = identifierToken.getSem();
        } else {
            // 处理identifierToken为null的情况
        }

        // Check if next token is an assignment operator
        Token nextToken = null;
        nextToken = lookahead();

        if ("ASSIGNMENT_OPERATOR".equals(nextToken.getLex().toString())) parseAssignmentRest(identifier);
        else {
            parseCallStmRest(identifier);
        }
    }

    private void parseAssignmentRest(String identifier) {
        match("ASSIGNMENT_OPERATOR");
        parseExp();
    }

    private void parseCallStmRest(String identifier) {
        match("(");
        parseActParamList();
        match(")");
    }

    // 解析实际参数列表
    private void parseActParamList() {
        if (lookaheadIs("INTC") || lookaheadIs("IDENTIFIER") || lookaheadIs("LPAREN")) {
            parseExp();
            while (lookaheadIs(",")) {
                match(",");
                parseExp();
            }
        }
    }

    // 解析实际参数列表的后续部分
    private void parseActParamMore() {
        if (lookaheadIs(",")) {
            match(",");
            parseActParamList();
        }
    }

    // 解析关系表达式的后续部分
    private void parseOtherRelE() {
        match("CmpOp");
        parseExp();
    }

    // 解析算术表达式
    private void parseExp() {
        parseTerm();
        parseOtherTerm();
    }

    // 解析算术表达式的后续部分
    private void parseOtherTerm() {
        if (lookaheadIs("AddOp")) {
            matchAddOp();
            parseExp();
        }
    }

    // 解析乘法表达式的后续部分
    private void parseOtherFactor() {
        if (lookaheadIs("MultOp")) {
            match("MultOp");
            parseTerm();
        }
    }

    // 解析变量及其可能的后缀
    private AbstractSyntaxTreeNode parseVariable() {
        match("IDENTIFIER");
        AbstractSyntaxTreeNode identifierNode = new IdentifierNode(consume("IDENTIFIER"));

        // 解析变量的后续部分，如果存在
        parseVariMore(identifierNode);

        return identifierNode;
    }

    private void parseVariMore(AbstractSyntaxTreeNode baseNode) {
        Token nextToken = null;
        nextToken = lookahead();

        while (true) {
            if (nextToken.getLex().toString().equals("LSQUARE")) {
                // 解析数组下标
                consume("LSQUARE");
                AbstractSyntaxTreeNode indexExpr = null;
                parseExp();
                consume("RSQUARE");

                // 创建数组访问节点并将其附加到基础节点上
                baseNode = createArrayAccessNode(baseNode, indexExpr);
            } else if (nextToken.getLex().toString().equals("DOT")) {
                // 解析字段访问
                consume("DOT");
                AbstractSyntaxTreeNode fieldNode = parseVariable();

                // 创建字段访问节点并将其附加到基础节点上
                baseNode = createFieldAccessNode(baseNode, fieldNode);
            } else {
                break;
            }

            // 确保更新下一个符号
            nextToken = lookahead();
        }
    }

    // 辅助方法用于创建数组访问节点
    private AbstractSyntaxTreeNode createArrayAccessNode(AbstractSyntaxTreeNode baseNode, AbstractSyntaxTreeNode indexExpr) {
        if (baseNode instanceof IdentifierNode) {
            IdentifierNode identifierNode = (IdentifierNode) baseNode;
            return new ArrayAccessNode(identifierNode.getToken(), identifierNode, indexExpr);
        } else if (baseNode instanceof FieldAccessNode) {
            FieldAccessNode fieldAccessNode = (FieldAccessNode) baseNode;
            return new ArrayAccessNode(fieldAccessNode.getToken(), fieldAccessNode, indexExpr);
        }
        throw new IllegalStateException("Unsupported node type for ArrayAccessNode creation.");
    }

    // 辅助方法用于创建字段访问节点
    private AbstractSyntaxTreeNode createFieldAccessNode(AbstractSyntaxTreeNode baseNode, AbstractSyntaxTreeNode fieldNode) {
        if (baseNode instanceof IdentifierNode) {
            IdentifierNode identifierNode = (IdentifierNode) baseNode;
            return new FieldAccessNode(fieldNode.getToken(), identifierNode, ((IdentifierNode)fieldNode).getName());
        } else if (baseNode instanceof ArrayAccessNode) {
            ArrayAccessNode arrayAccessNode = (ArrayAccessNode) baseNode;
            return new FieldAccessNode(fieldNode.getToken(), arrayAccessNode, ((IdentifierNode)fieldNode).getName());
        }
        throw new IllegalStateException("Unsupported node type for FieldAccessNode creation.");
    }


    // 解析字段变量
    private void parseFieldVar() {
        match("IDENTIFIER");
        parseFieldVarMore();
    }

    // 解析字段变量的后续部分
    private void parseFieldVarMore() {
        if (lookaheadIs("LSQUARE")) {
            match("LSQUARE");
            parseExp();
            match("RSQUARE");
        }
    }

    // 匹配比较运算符
    private void matchCmpOp() {
        if (lookaheadIs("<") || lookaheadIs("=")) {
            match("CmpOp");
        } else {
            try {
                throw new SyntaxException("Expected comparison operator, got: " + lookahead().getLex());
            } catch (SyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // 匹配加减运算符
    @SneakyThrows
    private void matchAddOp() {
        if (lookaheadIs("+") || lookaheadIs("-")) {
            match("AddOp");
        } else {
            throw new SyntaxException("Expected addition or subtraction operator, got: " + lookahead().getLex());
        }
    }

    // 匹配乘除运算符
    @SneakyThrows
    private void matchMultOp() {
        if (lookaheadIs("*") || lookaheadIs("/")) {
            match("MultOp");
        } else {
            throw new SyntaxException("Expected multiplication or division operator, got: " + lookahead().getLex());
        }
    }


    public class SyntaxException extends Exception {
        public SyntaxException(String message) {
            super(message);
        }
    }





    private void parseProgramBody() {
        // 解析程序主体的逻辑
        // 首先匹配 'BEGIN'
        Token beginToken = match("BEGIN");
        if (beginToken == null) {
            // 'BEGIN'未匹配成功，处理错误
            return;
        }

        // 解析语句列表
        parseStmList();

        // 尝试匹配 'END'，如果失败，可能是由于文件结尾或其他原因
        Token endToken = match("END");
        if (endToken == null) {
            // 'END'未匹配成功，处理错误
            return;
        }

        System.out.println("parseProgramBody");
    }

    // 更多解析子程序...

    // 消耗当前词法单元
    private Token consume(String expectedType) {
        if (currentPosition < tokenList.size() && tokenList.get(currentPosition).getLex().equals(expectedType)) {
            return tokenList.get(currentPosition++);
        }
        throw new SyntaxerError("Expected token of type " + expectedType + " but found " + (currentPosition < tokenList.size() ? tokenList.get(currentPosition).getLex() : "end of file"), currentPosition);
    }

    // 判断下一个词法单元是否是"type"
// 改进后的lookaheadIs，仅检查下一个token的类型，不改变状态
    public boolean lookaheadIs(String type) {
        if (currentPosition + 1 < tokenList.size()) {
            return tokenList.get(currentPosition + 1).getLex().equals(type);
        }
        return false;
    }

    private Token lookahead() {
        if (currentPosition + 1 < tokenList.size()) {
            return tokenList.get(currentPosition + 1);
        } else {
            // 返回一个代表EOF的特殊标记
            return new Token(-1, EndDelimiter.EOF, "");
        }
    }

    public class EndOfFileException extends Exception {
        public EndOfFileException() {
            super("End of file reached.");
        }
    }

    private Token match(String expectedType) {
        if (currentPosition < tokenList.size()) {
            Token currentToken = tokenList.get(currentPosition);
            if (currentToken.getLex().equals(expectedType)) {
                currentPosition++; // 移动到下一个Token
                return currentToken; // 返回匹配成功的Token
            } else {
                errorList.add(new SyntaxerError("Expected '" + expectedType + "'", currentPosition));
            }
        } else {
            errorList.add(new SyntaxerError("Expected '" + expectedType + "' but reached end of input", currentPosition));
        }
        return null; // 未匹配成功，返回null
    }
}