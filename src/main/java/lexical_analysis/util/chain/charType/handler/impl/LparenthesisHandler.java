package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class LparenthesisHandler extends CharTypeHandler {
    public LparenthesisHandler() {
        init(CharType.L_PARENTHESIS, new RparenthesisHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '(';
    }
}
