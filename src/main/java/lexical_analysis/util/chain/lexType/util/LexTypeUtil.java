package lexical_analysis.util.chain.lexType.util;

import lexical_analysis.lexer.Core;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.EmbededLexTypeHandler;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandlerChainInitializer;

public class LexTypeUtil {
    private static final EmbededLexTypeHandler embededLexTypeHandler;

    static {
        embededLexTypeHandler = LexTypeHandlerChainInitializer.initialize();
    }

    public static LexType parseLexType(Core.IState state, String string) {
        return embededLexTypeHandler.getLexTypeByState(state, string);
    }
}
