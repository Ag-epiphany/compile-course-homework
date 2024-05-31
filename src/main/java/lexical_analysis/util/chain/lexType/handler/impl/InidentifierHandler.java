package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.Identifier;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;
import lexical_analysis.util.dict.KeyWordDict;

public class InidentifierHandler extends LexTypeHandler {
    public InidentifierHandler() {
    }

    public InidentifierHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return KeyWordDict.contains(name) ? KeyWordDict.getLexType(name) : new Identifier();
    }
}
