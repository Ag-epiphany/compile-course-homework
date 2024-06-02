package lexical_analysis.util.chain.charType.handler;

public class EmbededCharTypeHandler extends CharTypeHandler{
    public EmbededCharTypeHandler() {
    }

    public CharType getCharType(char ch) {
        return nextHandler.getCharType(ch);
    }

    @Override
    public boolean isSelfCharType(char ch) {
        return false;
    }
}
