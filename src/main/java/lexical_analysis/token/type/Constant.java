package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

/*
 * 常量*/
@TokenColor(color = IndexedColors.LIGHT_TURQUOISE1)
public enum Constant implements Iidentifier {
    UNSIGNED_INTEGER,
    CHAR,
    ;

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
