package lexical_analysis.util.dict;

import constant.ProjectConstant;
import lexical_analysis.token.annotation.TokenColor;
import lexical_analysis.token.type.LexType;
import lombok.Getter;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TokenColorDict {
    @Getter
    private static Map<String, Class<? extends LexType>> lexTypeMap;

    static {
        lexTypeMap = new HashMap<>();
        File directory = new File(ProjectConstant.TOKEN_TYPE_CLASSPATH);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String className = ProjectConstant.TOKEN_TYPE_FULL_CLASSPATH +
                    file.getName().replaceFirst("[.][^.]+$", "");
            Class<? extends LexType> lexTypeClass = null;
            try {
                lexTypeClass = (Class<? extends LexType>) Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if (lexTypeClass.isInterface()) {
                continue;
            }

            if (lexTypeClass.isEnum()) {
                LexType[] lexTypes = lexTypeClass.getEnumConstants();
                for (LexType lexType : lexTypes) {
                    try {
                        Method nameMethod = lexType.getClass().getMethod("name");
                        String name = ((String) nameMethod.invoke(lexType)).toUpperCase();
                        String typeName = lexType.getClass().getSimpleName().toUpperCase();
                        String lexTypeName = typeName + "/" + name;
                        lexTypeMap.put(lexTypeName, lexTypeClass);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                String lexTypenName = lexTypeClass.getSimpleName().toUpperCase();
                lexTypeMap.put(lexTypenName, lexTypeClass);
            }
        }
    }

    public static IndexedColors getIndexedColor(String lexTypeName) {
        Class<? extends LexType> clazz = lexTypeMap.get(lexTypeName);
        TokenColor color = clazz.getAnnotation(TokenColor.class);
        return color.color();
    }
}
