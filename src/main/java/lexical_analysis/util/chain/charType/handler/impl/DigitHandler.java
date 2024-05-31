package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class DigitHandler extends CharTypeHandler {
    public DigitHandler() {
        init(CharType.DIGIT,new PlusHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return Character.isDigit(ch);
    }
}
