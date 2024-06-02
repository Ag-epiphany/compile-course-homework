package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class MultiplicationHandler extends CharTypeHandler {
    public MultiplicationHandler() {
        init(CharType.MULTIPLICATION);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == '*';
    }
}
