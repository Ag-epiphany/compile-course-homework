package lexical_analysis.token.type;

public enum DataType implements KeyWord {
    INTEGER,
    CHAR,
    ARRAY,
    RECORD,
    UNSIGNED_INTEGER    //  无符号整数
    ;

    @Override
    public String toString() {
        return "DATATYPE/" + this.name();
    }
}
