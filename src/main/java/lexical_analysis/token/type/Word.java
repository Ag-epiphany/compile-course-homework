package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.LIGHT_YELLOW)
public enum Word implements KeyWord {
    PROGRAM,
    PROCEDURE,
    TYPE,
    VAR,
    IF,
    THEN,
    ELSE,
    FI,
    WHILE,
    DO,
    ENDWH,
    BEGIN,
    END,
    READ,
    WRITE,       // 输入输出语句
    OF,
    RETURN,
    ;

    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
