package syntax_analysis.facade;

import lexical_analysis.token.Token;
import lombok.Getter;

import java.util.List;

abstract public class AbstractSyntaxerCore {
    protected List<Token> tokenList;
    @Getter
    protected List<AbstractError> errorList;
    @Getter
    protected AbstractSyntaxTree syntaxTree;

    abstract public void read(List<Token> tokenList);

    abstract public void analyze();
}
