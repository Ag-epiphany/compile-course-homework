package lexical_analysis.util.dict;

import lexical_analysis.token.type.*;

import java.util.HashMap;
import java.util.Map;

public class SingleCharDelimiterDict {
    private static final Map<String, SingleCharDelimiter> pairDelimiters;
    private static final Map<String, SingleCharDelimiter> operators;
    private static final Map<String, SingleCharDelimiter> endDelimiters;
    private static final Map<String, SingleCharDelimiter> WhiteSpaces;

    static {
        pairDelimiters = new HashMap<>();
        operators = new HashMap<>();
        endDelimiters = new HashMap<>();
        WhiteSpaces = new HashMap<>();

        for (PairDelimiter pairDelimiter : PairDelimiter.values()) {
            pairDelimiters.put(pairDelimiter.getSymbol(), pairDelimiter);
        }

        for (Operator operator : Operator.values()) {
            operators.put(operator.getSymbol(), operator);
        }

        for (EndDelimiter endDelimiter : EndDelimiter.values()) {
            endDelimiters.put(endDelimiter.getSymbol(), endDelimiter);
        }

        for (SingleCharDelimiter whiteSpace : WhiteSpace.values()) {
            WhiteSpaces.put(whiteSpace.getSymbol(), whiteSpace);
        }
    }

    public static LexType getLexType(String name) {
        LexType lexType = null;
        if (pairDelimiters.containsKey(name)) {
            lexType = pairDelimiters.get(name);
        } else if (operators.containsKey(name)) {
            lexType = operators.get(name);
        } else if (endDelimiters.containsKey(name)) {
            lexType = endDelimiters.get(name);
        } else if (WhiteSpaces.containsKey(name)) {
            lexType = WhiteSpaces.get(name);
        }

        return lexType;
    }
}
