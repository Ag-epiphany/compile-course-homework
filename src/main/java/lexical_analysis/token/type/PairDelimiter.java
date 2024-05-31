package lexical_analysis.token.type;

/*
 * 需要成对出现的界限符*/
public enum PairDelimiter implements SingleCharDelimiter {
    L_PARENTHESIS("("), //  (
    R_PARENTHESIS(")"),   //  )
    L_MID_PARENTHESIS("["),  // [
    R_MID_PARENTHESIS("]"),  // ]
    APOSTROPHE("'"),        //  单引号 '
    ;
    private final String symbol;

    PairDelimiter(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
