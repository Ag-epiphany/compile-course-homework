package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class LetterHandler extends CharTypeHandler {
    public LetterHandler() {
        init(CharType.LETTER);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return Character.isLetter(ch);
    }
}
