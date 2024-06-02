package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class RparenthesisHandler extends CharTypeHandler {
    public RparenthesisHandler() {
        init(CharType.R_PARENTHESIS);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == ')';
    }
}
