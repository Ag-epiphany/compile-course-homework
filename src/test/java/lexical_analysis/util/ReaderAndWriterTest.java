package lexical_analysis.util;

import com.alibaba.excel.util.ListUtils;
import lexical_analysis.token.Token;
import lexical_analysis.token.type.DataType;
import lexical_analysis.util.io.ReaderAndWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReaderAndWriterTest {
    @Test
    public void testRead() {
        ReaderAndWriter readerAndWriter = new ReaderAndWriter();
        char word = readerAndWriter.read();
        while (word != (char) -1) {
            System.out.print("第" + readerAndWriter.getLineNumber() + "行    " + word);
            if (word != '\n') {
                System.out.println();
            }
            word = readerAndWriter.read();
        }
    }

    @Test
    public void testWrite() {
        ReaderAndWriter readerAndWriter = new ReaderAndWriter();
        List<Token> list = ListUtils.newArrayList();
        list.add(new Token(1, DataType.INTEGER, "整数"));
        System.out.println(list);
        readerAndWriter.write(list);
    }

    @Test
    public void testTrimEmptyChar() {
        String s = "program" + "\0";
//        System.out.println(s);
        s = s.replace("\0", "");
        Assertions.assertEquals(s, "program");
    }

    @Test
    public void testWhiteSpace() {
        Assertions.assertTrue(Character.isWhitespace('\n'));
    }

    @Test
    public void testStringMidEmpty() {
        String s = "123" + "\0" + "123";
        System.out.println(s);
    }
}
