package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class EmptyHandler extends CharTypeHandler {
    public EmptyHandler() {
        init(CharType.EMPTY);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '\0';
    }
}
