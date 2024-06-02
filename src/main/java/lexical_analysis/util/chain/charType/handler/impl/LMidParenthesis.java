package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class LMidParenthesis extends CharTypeHandler {
    public LMidParenthesis() {
        init(CharType.L_MID_PARENTHESIS);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '[';
    }
}
