package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.RED)
public enum WhiteSpace implements SingleCharDelimiter {
    SPACE(" "),
    NEW_LINE("\n"),
    TAB("\t"),
    CARRIAGE_RETURN("\r")
    ;

    private final String symbol;

    WhiteSpace(String symbol) {
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
