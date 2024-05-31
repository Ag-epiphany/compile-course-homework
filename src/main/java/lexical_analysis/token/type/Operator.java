package lexical_analysis.token.type;

/*
 * 运算符*/
public enum Operator implements SingleCharDelimiter {
    PLUS("+"),       //  +
    MINUS("-"),      // -
    MULTIPLY("*"),   //  *
    DIVIDE("/"),     //  /
    LESS("<"),       //  <
    EQUAL("="),      // =
    COMMA(","),
    ;      // ,

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }
}
