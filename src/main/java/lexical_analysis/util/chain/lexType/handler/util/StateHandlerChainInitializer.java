package lexical_analysis.util.chain.lexType.handler.util;

import Constant.ProjectConstant;
import lexical_analysis.util.chain.lexType.handler.EmbededLexTypeHandler;
import lexical_analysis.util.chain.lexType.handler.LexTypeHandler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class StateHandlerChainInitializer {
    public static EmbededLexTypeHandler initialize() {
        EmbededLexTypeHandler embededLexTypeHandler = new EmbededLexTypeHandler();
        LexTypeHandler curr = embededLexTypeHandler;
        File directory = new File(ProjectConstant.LEXTYPE_HANDLER_CLASSPATH);
        for (File file : directory.listFiles()) {
            String className = ProjectConstant.LEXTYPE_HANDLER_FULL_CLASSPATH +
                    file.getName().replaceFirst("[.][^.]+$", "");

            LexTypeHandler next = null;
            try {
                next = (LexTypeHandler) Class.forName(className).getConstructor().newInstance();
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            curr.setNextHandler(next);
            curr = next;
        }

        return embededLexTypeHandler;
    }
}
