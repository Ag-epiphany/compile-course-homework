package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.MAROON)
public enum CommentDelimiter implements SingleCharDelimiter {
    L_COMMENT("{"),       //  { 注释头符
    R_COMMENT("}"),      //  }
    ;
    private final String symbol;

    CommentDelimiter(String symbol) {
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
