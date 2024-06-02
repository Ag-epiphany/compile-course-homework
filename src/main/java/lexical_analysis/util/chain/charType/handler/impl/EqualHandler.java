package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class EqualHandler extends CharTypeHandler {
    public EqualHandler() {
        init(CharType.EQUAL);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch=='=';
    }
}
