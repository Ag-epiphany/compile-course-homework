package lexical_analysis.util.chain.charType.handler;

import constant.ProjectConstant;
import lexical_analysis.util.chain.ChainInitializer;
import lexical_analysis.util.chain.charType.handler.annotation.Skip;
import lexical_analysis.util.chain.charType.handler.impl.OtherHandler;

public class CharTypeHandlerChainInitializer extends ChainInitializer {
    public static EmbededCharTypeHandler initialize() {
        EmbededCharTypeHandler embededCharTypeHandler = new EmbededCharTypeHandler();
        buildChain(embededCharTypeHandler,
                ProjectConstant.CHARTYPE_HANDLER_CLASSPATH,
                ProjectConstant.CHARTYPE_HANDLER_FULL_CLASSPATH,
                handlerChain -> {
                    Skip skip = handlerChain.getClass().getAnnotation(Skip.class);
                    return skip == null;
                });

        CharTypeHandler curr = embededCharTypeHandler;
        while (curr.getNextHandler() != null) {
            curr = curr.nextHandler;
        }
        curr.nextHandler = new OtherHandler();

        return embededCharTypeHandler;
    }
}
