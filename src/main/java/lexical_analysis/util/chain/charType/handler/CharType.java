package lexical_analysis.util.chain.charType.handler;

public enum CharType {
    LETTER,
    DIGIT,
    PLUS,   // +
    SUBTRACTION,    //  -
    MULTIPLICATION, //  *
    DIVISION,   //  /
    L_PARENTHESIS, //  (
    R_PARENTHESIS,   //  )
    L_MID_PARENTHESIS,  // [
    R_MID_PARENTHESIS,  // ]
    SEMICOLON,  //  分号
    COLON,  //  :
    EQUAL,  //  =
    LESS_THAN,  //  <
    LCOMMENT,   // {
    RCOMMENT,   // }
    DOT,
    COMMA,  //  逗号
    APOSTROPHE, //  单引号
    EOF,    //  文件结束符
    EMPTY,
    WHITE_SPACE,    //  空白字符
    OTHER,
    ;
}
