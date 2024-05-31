package lexical_analysis.token;

import Constant.ProjectConstant;
import lexical_analysis.token.type.LexType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

@Getter
@Setter
public class Token {
    int lineShow;   //  单词在源程序中的行数，从1开始计数
    LexType lex;    //  单词的词法信息，即单词的类型
    String sem;     //  单词的语义信息

    public Token(int lineShow, LexType lex, String sem) {
        this.lex = lex;
        this.lineShow = lineShow;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sem.length(); i++) {
            String s = String.valueOf(sem.charAt(i));
            if (Character.isWhitespace(s.charAt(0))) {
                if (s.charAt(0) == ' ') {
                    s = ProjectConstant.ESCAPED_SPACE;
                }
                else {
                    s = StringEscapeUtils.escapeJava(s);
                }
            }

            sb.append(s);
        }
        this.sem = sb.toString();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("行号：")
                .append(lineShow)
                .append("           ")
                .append("类型：")
                .append(lex)
                .append("                           ")
                .append("语义：")
                .append(sem)
                .toString();
    }
}

// todo 编写Token包装器：等间距的Token，以及 颜色高亮的Token