package lexical_analysis.util.chain.charType.handler;

import lexical_analysis.util.chain.HandlerChain;

abstract public class CharTypeHandler implements HandlerChain {
    protected CharTypeHandler nextHandler;
    protected CharType selfCharType;

    protected CharType getCharType(char ch) {
        if (isSelfCharType(ch)) {
            return selfCharType;
        }

        return nextHandler.getCharType(ch);
    }

    protected void init(CharType selfCharType, CharTypeHandler nextHandler) {
        this.selfCharType = selfCharType;
        this.nextHandler = nextHandler;
    }

    protected void setNextHandler(CharTypeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected void setSelfCharType(CharType selfCharType) {
        this.selfCharType = selfCharType;
    }

    abstract public boolean isSelfCharType(char ch);
}
