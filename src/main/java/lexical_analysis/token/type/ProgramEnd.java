package lexical_analysis.token.type;

import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.util.TokenTypeToStringUtil;
import org.apache.poi.ss.usermodel.IndexedColors;

@TokenColor(color = IndexedColors.LIGHT_CORNFLOWER_BLUE)
public class ProgramEnd implements LexType{
    @Override
    public String toString() {
        return TokenTypeToStringUtil.getString(this);
    }
}
