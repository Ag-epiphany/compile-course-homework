package lexical_analysis.token.util;

import lexical_analysis.token.type.LexType;

import java.lang.reflect.InvocationTargetException;

public class TokenTypeToStringUtil {
    public static String getString(LexType lexType) {
        Class<? extends LexType> clazz = lexType.getClass();
        String string = clazz.getSimpleName().toUpperCase();
        if (clazz.isEnum()) {
            try {
                string += "/" + ((String) clazz.getMethod("name").invoke(lexType)).toUpperCase();
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        return string;
    }
}
