package syntax_analysis.facade;

abstract public class AbstractError extends RuntimeException {

    protected String message;
    protected int position;

    public AbstractError(String message, int position) {
        this.message = message;
        this.position = position;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getPosition() {
        return position;
    }

    public String getMessage() {
        return message;
    }
}