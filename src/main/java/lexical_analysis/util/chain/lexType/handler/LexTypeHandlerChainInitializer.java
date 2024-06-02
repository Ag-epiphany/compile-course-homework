package lexical_analysis.util.chain.lexType.handler;

import constant.ProjectConstant;
import lexical_analysis.util.chain.ChainInitializer;

public class LexTypeHandlerChainInitializer extends ChainInitializer {
    public static EmbededLexTypeHandler initialize() {
        return (EmbededLexTypeHandler) buildChain(new EmbededLexTypeHandler(),
                ProjectConstant.LEXTYPE_HANDLER_CLASSPATH,
                ProjectConstant.LEXTYPE_HANDLER_FULL_CLASSPATH,
                handlerChain -> true);
    }
}
