package lexical_analysis.util.dict;

import lexical_analysis.token.type.LexType;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TokenColorDictTest {
    @Test
    public void testTokenColorDict() {
        Map<String, Class<? extends LexType>> map=TokenColorDict.getLexTypeMap();
        for (Map.Entry<String, Class<? extends LexType>> entry : map.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue().getSimpleName());
        }
    }
}
