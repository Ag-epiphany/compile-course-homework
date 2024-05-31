package lexical_analysis.util.dict;

import lexical_analysis.token.type.DataType;
import lexical_analysis.token.type.KeyWord;
import lexical_analysis.token.type.Word;

import java.util.HashSet;
import java.util.Set;

public class KeyWordDict{
    private static final Set<String> dateTypeSet;
    private static final Set<String> wordSet;

    static {
        dateTypeSet = new HashSet<>();
        wordSet = new HashSet<>();
        for (DataType dataType : DataType.values()) {
            dateTypeSet.add(dataType.name().toLowerCase());
        }

        for (Word word : Word.values()) {
            wordSet.add(word.name().toLowerCase());
        }
    }

    public static boolean contains(String string) {
        return dateTypeSet.contains(string) || wordSet.contains(string);
    }

    public static KeyWord getLexType(String string) {
        if (!contains(string)) {
            return null;
        }

        return dateTypeSet.contains(string) ? DataType.valueOf(string.toUpperCase()) : Word.valueOf(string.toUpperCase());
    }
}
