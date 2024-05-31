package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.DoubleCharDelimiter;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;

public class InassignHandler extends LexTypeHandler {
    public InassignHandler() {
    }

    public InassignHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return DoubleCharDelimiter.ASSIGN;
    }
}
