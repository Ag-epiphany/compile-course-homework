package lexical_analysis.token.type;

public enum EndDelimiter implements SingleCharDelimiter{
    SEMICOLON(";"),      //  ;
    DOT("."),     // .
    EOF(String.valueOf((char) -1)),
    ;     //  EOF

    private final String symbol;

    EndDelimiter(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
