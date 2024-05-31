package lexical_analysis.token.type;

public enum DoubleCharDelimiter implements Delimiter {
    ASSIGN(":="),     //  :=
    ARRAY_SUBSCRIPT_BOUND(".."),      //  ..
    ;
    private final String symbol;

    DoubleCharDelimiter(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
