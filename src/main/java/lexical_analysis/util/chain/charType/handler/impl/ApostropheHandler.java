package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class ApostropheHandler extends CharTypeHandler {
    public ApostropheHandler() {
        init(CharType.APOSTROPHE);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '\'';
    }
}
