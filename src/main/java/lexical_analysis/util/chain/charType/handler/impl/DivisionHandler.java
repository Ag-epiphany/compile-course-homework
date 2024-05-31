package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class DivisionHandler extends CharTypeHandler {
    public DivisionHandler() {
        init(CharType.DIVISION, new LparenthesisHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '/';
    }
}
