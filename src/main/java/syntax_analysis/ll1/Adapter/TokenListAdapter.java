package syntax_analysis.ll1.Adapter;
import syntax_analysis.ll1.util.InputTokens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TokenListAdapter {

    static List<String> input = new ArrayList<>();
    private static List<String> modifiedInputToken = new ArrayList<>();
    private static List<Integer> targetline = new ArrayList<>();
    public static void convert(){
        Map<String, String> symbolMap = new HashMap<>();
        symbolMap.put("PLUS", "+");
        symbolMap.put("MINUS", "-");
        symbolMap.put("MULTIPLY", "*");
        symbolMap.put("DIVIDE", "/");
        symbolMap.put("LESS", "<");
        symbolMap.put("EQUAL", "=");
        symbolMap.put("COMMA", ",");
        symbolMap.put("L_PARENTHESIS", "(");
        symbolMap.put("R_PARENTHESIS", ")");
        symbolMap.put("L_MID_PARENTHESIS", "[");
        symbolMap.put("R_MID_PARENTHESIS", "]");
        symbolMap.put("APOSTROPHE", "'");
        symbolMap.put("ASSIGN", ":=");
        symbolMap.put("SEMICOLON", ";");
        symbolMap.put("DOT", ".");
        symbolMap.put("UNSIGNED_INTEGER", "INTC");
        symbolMap.put("PROGRAMEND","RETURN");
        symbolMap.put("ARRAY_SUBSCRIPT_BOUND","..");



        input = InputTokens.getInputTokens();
        List<Integer> line = InputTokens.getLineofToken();

        for(int i = 0 ; i < input.size(); i++){
            int slashIndex = input.get(i).indexOf('/');
            int index = input.indexOf(input.get(i));
            String a;
            if(slashIndex != -1){
                a = input.get(i).substring(slashIndex + 1);
            }else{
                a = input.get(i);
            }

            if (!"NEW_LINE".equals(a)&&!"SPACE".equals(a)&&!"COMMENT".equals(a)) {
                if (symbolMap.containsKey(a)) {
                    modifiedInputToken.add(symbolMap.get(a));


                } else {
                    modifiedInputToken.add(a);
                }
                targetline.add(line.get(i));

            }
        }

    }
    public static List<String> getTokens(){
        convert();
        return modifiedInputToken;
    }

    public static List<Integer> getTokensLine(){
        return targetline;
    }
}
