package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class SubtractionHandler extends CharTypeHandler {
    public SubtractionHandler() {
        init(CharType.SUBTRACTION);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch=='-';
    }
}
