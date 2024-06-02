package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.LIGHT_GREEN)
public class Identifier implements Iidentifier{
    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
