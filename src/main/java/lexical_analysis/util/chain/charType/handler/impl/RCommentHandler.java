package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;

public class RCommentHandler extends CharTypeHandler {
    public RCommentHandler() {
        init(CharType.RCOMMENT,new DotHandler());
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return ch=='}';
    }
}
