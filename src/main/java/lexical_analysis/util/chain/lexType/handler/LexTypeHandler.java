package lexical_analysis.util.chain.lexType.handler;

import lexical_analysis.lexer.Core;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.HandlerChain;
import lexical_analysis.util.chain.lexType.handler.annotation.ExcludeAutoSelfState;

abstract public class LexTypeHandler implements HandlerChain {
    private Core.IState selfState;
    private LexTypeHandler nextHandler;
//    private IndexedColors color;

    public LexTypeHandler() {
    }

    public LexTypeHandler(LexTypeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public boolean isSelfState(Core.IState ctxState) {
        if (selfState == null) {
//          排除不参与处理链自动装配selfState的处理器
            ExcludeAutoSelfState excludeChain = this.getClass().getAnnotation(ExcludeAutoSelfState.class);
            if (excludeChain != null) {
                return false;
            }

            initSelfState(ctxState);
        }

        return this.selfState.equals(ctxState);
    }

    protected void setSelfState(Core.IState ctxState) {
        this.selfState = ctxState;
    }

    public LexType getLexTypeByState(Core.IState ctxState, String name) {
        if (isSelfState(ctxState)) {
            return convertLexType(name);
        }

        return nextHandler != null ? nextHandler.getLexTypeByState(ctxState, name) : null;
    }

    @Override
    public void setNextHandler(HandlerChain handlerChain){
        this.nextHandler = (LexTypeHandler) handlerChain;
    }

    protected void initSelfState(Core.IState ctxState) {
        String selfClassName = this.getClass().getSimpleName();
        String selfStateName = selfClassName.substring(0, selfClassName.length() - 7);
        StringBuilder tmp = new StringBuilder().append(selfStateName.charAt(0));
        for (int i = 1; i < selfStateName.length(); i++) {
            if (Character.isUpperCase(selfStateName.charAt(i))) {
                tmp.append('_');
            }

            tmp.append(selfStateName.charAt(i));
        }

        selfStateName = tmp.toString().toUpperCase();
        setSelfState(ctxState.valueOf0(selfStateName));
    }

    abstract protected LexType convertLexType(String name);

}
