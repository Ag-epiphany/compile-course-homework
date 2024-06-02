package lexical_analysis.util.chain.charType.handler.impl;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandler;
import lexical_analysis.util.chain.charType.handler.annotation.Skip;

@Skip
public class OtherHandler extends CharTypeHandler {
    public OtherHandler() {
        init(CharType.OTHER);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return true;
    }
}
