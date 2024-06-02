package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

/*
 * 需要成对出现的界限符*/
@TokenColor(color = IndexedColors.LIGHT_ORANGE)
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

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
