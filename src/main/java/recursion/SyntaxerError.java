package recursion;

import syntax_analysis.facade.AbstractError;

public class SyntaxerError extends AbstractError {
    public SyntaxerError(String message, int position) {
        super(message, position);
    }

    @Override
    public String getErrorMessage() {
        return "Syntax Error: " + getMessage() + " at position " + getPosition();
    }
}