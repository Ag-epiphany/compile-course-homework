package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

/*
 * 运算符*/
@TokenColor(color = IndexedColors.PINK1)
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

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
