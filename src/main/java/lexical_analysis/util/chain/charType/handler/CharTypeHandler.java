package lexical_analysis.util.chain.charType.handler;

import lexical_analysis.util.chain.HandlerChain;
import lombok.Getter;

abstract public class CharTypeHandler implements HandlerChain {
    @Getter
    protected CharTypeHandler nextHandler;
    protected CharType selfCharType;

    public CharTypeHandler() {
    }

    protected CharType getCharType(char ch) {
        if (isSelfCharType(ch)) {
            return selfCharType;
        }

        return nextHandler.getCharType(ch);
    }

    protected void init(CharType selfCharType) {
        this.selfCharType = selfCharType;
//        this.nextHandler = nextHandler;
    }

    @Override
    public void setNextHandler(HandlerChain nextHandler) {
        this.nextHandler = (CharTypeHandler) nextHandler;
    }

    protected void setSelfCharType(CharType selfCharType) {
        this.selfCharType = selfCharType;
    }

    abstract public boolean isSelfCharType(char ch);
}
