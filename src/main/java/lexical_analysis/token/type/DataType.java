package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.LIGHT_BLUE)
public enum DataType implements KeyWord {
    INTEGER,
    CHAR,
    ARRAY,
    RECORD,
    UNSIGNED_INTEGER    //  无符号整数
    ;

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
