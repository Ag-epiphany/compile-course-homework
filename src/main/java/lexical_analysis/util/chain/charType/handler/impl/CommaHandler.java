package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class CommaHandler extends CharTypeHandler {
    public CommaHandler() {
        init(CharType.COMMA);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch==',';
    }
}
