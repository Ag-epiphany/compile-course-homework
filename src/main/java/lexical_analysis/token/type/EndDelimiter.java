package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.BRIGHT_GREEN)
public enum EndDelimiter implements SingleCharDelimiter {
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

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
