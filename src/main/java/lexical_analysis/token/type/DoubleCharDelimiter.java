package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.BROWN)
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

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
