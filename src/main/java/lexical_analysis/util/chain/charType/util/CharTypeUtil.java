package lexical_analysis.util.chain.charType.util;

import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.handler.CharTypeHandlerChainInitializer;
import lexical_analysis.util.chain.charType.handler.EmbededCharTypeHandler;


public class CharTypeUtil {
    private static final EmbededCharTypeHandler embededCharTypeHandler;

    static {
        embededCharTypeHandler = CharTypeHandlerChainInitializer.initialize();
    }

    public static CharType parseCharType(char ch) {
        return embededCharTypeHandler.getCharType(ch);
    }
}
