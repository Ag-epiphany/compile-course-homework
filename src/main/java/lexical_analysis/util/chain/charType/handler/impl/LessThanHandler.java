package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class LessThanHandler extends CharTypeHandler {
    public LessThanHandler() {
        init(CharType.LESS_THAN,new WhiteSpaceHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '<';
    }
}
