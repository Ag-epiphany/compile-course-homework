package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class EofHandler extends CharTypeHandler {
    public EofHandler() {
        init(CharType.EOF);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch == (char) -1;
    }
}
