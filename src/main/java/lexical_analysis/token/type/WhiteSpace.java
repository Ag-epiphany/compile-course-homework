package lexical_analysis.token.type;

public enum WhiteSpace implements SingleCharDelimiter {
    SPACE(" "),
    NEW_LINE("\n"),
    ;

    private String symbol;

    WhiteSpace(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
