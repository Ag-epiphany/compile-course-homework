package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class SemicolonHandler extends CharTypeHandler {
    public SemicolonHandler() {
        init(CharType.SEMICOLON);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == ';';
    }
}
