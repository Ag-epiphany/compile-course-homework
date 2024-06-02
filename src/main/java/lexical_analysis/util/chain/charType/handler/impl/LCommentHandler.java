package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class LCommentHandler extends CharTypeHandler {
    public LCommentHandler() {
        init(CharType.LCOMMENT);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch=='{';
    }
}
