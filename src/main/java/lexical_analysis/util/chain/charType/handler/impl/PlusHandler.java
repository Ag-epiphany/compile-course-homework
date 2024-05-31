package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class PlusHandler extends CharTypeHandler {
    public PlusHandler() {
        init(CharType.PLUS, new SubtractionHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '+';
    }
}
