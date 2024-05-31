package lexical_analysis.token.type;

public enum Word implements KeyWord {
    PROGRAM,
    PROCEDURE,
    TYPE,
    VAR,
    IF,
    THEN,
    ELSE,
    FI,
    WHILE,
    DO,
    ENDWH,
    BEGIN,
    END,
    READ,
    WRITE,       // 输入输出语句
    OF,
    RETURN,
    ;

    @Override
    public String toString() {
        return "WORD/" + this.name();
    }
}
