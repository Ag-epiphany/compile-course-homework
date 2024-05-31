package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class RMidParenthesis extends CharTypeHandler {
    public RMidParenthesis() {
        init(CharType.R_MID_PARENTHESIS,new SemicolonHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch==']';
    }
}
