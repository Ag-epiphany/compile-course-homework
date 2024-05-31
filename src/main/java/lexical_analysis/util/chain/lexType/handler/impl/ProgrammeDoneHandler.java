package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.LexType;
import lexical_analysis.token.type.ProgramEnd;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;

public class ProgrammeDoneHandler extends LexTypeHandler {
    public ProgrammeDoneHandler() {
    }

    public ProgrammeDoneHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return new ProgramEnd();
    }
}
