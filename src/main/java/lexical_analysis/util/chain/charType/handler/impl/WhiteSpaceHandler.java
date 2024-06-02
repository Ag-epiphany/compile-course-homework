package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class WhiteSpaceHandler extends CharTypeHandler {
    public WhiteSpaceHandler() {
        init(CharType.WHITE_SPACE);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return Character.isWhitespace(ch);
    }
}
