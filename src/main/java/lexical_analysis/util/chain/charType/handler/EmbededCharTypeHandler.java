package lexical_analysis.util.chain.charType.handler;

import lexical_analysis.util.chain.charType.handler.impl.LessThanHandler;

public class EmbededCharTypeHandler extends CharTypeHandler{
//    private final CharHandlerContext charHandlerContext;

    public EmbededCharTypeHandler() {
        setNextHandler(new LessThanHandler());
    }

    public CharType getCharType(char ch) {
        return nextHandler.getCharType(ch);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return false;
    }
}
