package lexical_analysis.util.chain.lexType.handler;

import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.annotation.ExcludeAutoSelfState;

@ExcludeAutoSelfState
public class EmbededLexTypeHandler extends LexTypeHandler {
    public EmbededLexTypeHandler() {

    }

    public EmbededLexTypeHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return null;
    }
}
