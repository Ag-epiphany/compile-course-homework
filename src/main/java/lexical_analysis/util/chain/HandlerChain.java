package lexical_analysis.util.chain;

public interface HandlerChain {
    void setNextHandler(HandlerChain nextHandler);
}
