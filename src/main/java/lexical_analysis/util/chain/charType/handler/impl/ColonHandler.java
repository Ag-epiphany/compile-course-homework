package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class ColonHandler extends CharTypeHandler {
    public ColonHandler() {
        init(CharType.COLON,new EqualHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch==':';
    }
}
