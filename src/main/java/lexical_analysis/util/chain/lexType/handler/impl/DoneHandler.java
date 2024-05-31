package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;
import lexical_analysis.util.dict.SingleCharDelimiterDict;

public class DoneHandler extends LexTypeHandler {
    public DoneHandler() {
    }

    public DoneHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return SingleCharDelimiterDict.getLexType(name);
    }
}
