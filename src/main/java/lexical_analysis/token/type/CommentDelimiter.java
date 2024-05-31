package lexical_analysis.token.type;

public enum CommentDelimiter implements SingleCharDelimiter {
    L_COMMENT("{"),       //  { 注释头符
    R_COMMENT("}"),      //  }
    ;
    private final String symbol;

    CommentDelimiter(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return "";
    }
}
