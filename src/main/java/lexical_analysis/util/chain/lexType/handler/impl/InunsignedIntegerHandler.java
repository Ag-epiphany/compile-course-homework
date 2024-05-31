package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.Constant;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;

public class InunsignedIntegerHandler extends LexTypeHandler {
    public InunsignedIntegerHandler() {
    }

    public InunsignedIntegerHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return Constant.UNSIGNED_INTEGER;
    }
}
