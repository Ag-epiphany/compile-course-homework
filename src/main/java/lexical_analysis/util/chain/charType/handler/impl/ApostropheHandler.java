package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class ApostropheHandler extends CharTypeHandler {
    public ApostropheHandler() {
        init(CharType.APOSTROPHE, new OtherHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '\'';
    }
}
