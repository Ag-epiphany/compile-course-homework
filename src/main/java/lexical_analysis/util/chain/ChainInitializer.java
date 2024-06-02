package lexical_analysis.util.chain;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

abstract public class ChainInitializer {
    public interface FilterCondition{
        boolean filter(HandlerChain handlerChain);
    }

    protected static HandlerChain buildChain(HandlerChain head, String classFilePath, String classFullPath,FilterCondition filter) {
        HandlerChain curr = head;
        File directory = new File(classFilePath);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            String className = classFullPath + file.getName().replaceFirst("[.][^.]+$", "");
            HandlerChain next = null;
            try {
                next = (HandlerChain) Class.forName(className).getConstructor().newInstance();
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }

            if (filter.filter(next)){
                curr.setNextHandler(next);
                curr = next;
            }
        }

        return head;
    }
}
