package lexical_analysis.util.chain.lexType.handler.impl;

import lexical_analysis.token.type.Comment;
import lexical_analysis.token.type.LexType;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;

public class CommentDoneHandler extends LexTypeHandler {
    public CommentDoneHandler() {
    }

    public CommentDoneHandler(LexTypeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    protected LexType convertLexType(String name) {
        return new Comment();
    }
}
