package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;

public class ErrorHandler extends LexTypeHandler {
    public ErrorHandler() {
    }

    public ErrorHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        System.err.println("词法解析出错!出现不存在的终止状态!");
        System.err.println("这个标识是：" + name);
        System.exit(1);

        return null;
    }
}
