package lexical_analysis.util.io;

import Constant.ProjectConstant;
import lexical_analysis.util.chain.charType.handler.CharType;
import lexical_analysis.util.chain.charType.util.CharTypeUtil;

import java.io.*;


// todo 重构write()，使其能将文件写入.xlsx
public class ReaderAndWriter {
    private BufferedReader sourceFile;
    private BufferedWriter targetFile;

    private int lineNumber;
    private String currentLine;
    private int pos;
    private boolean hasNewLine;
    private char tmpChar;
    private boolean isFirstCallRead;

    public ReaderAndWriter() {
        this(ProjectConstant.DEFAULT_SOURCE_fILEPATH, ProjectConstant.DEFAULT_TARGET_fILEPATH);
    }

    public ReaderAndWriter(File sourceFile) {
        this(sourceFile.getAbsolutePath());
    }

    public ReaderAndWriter(String sourceFile) {
        this(sourceFile, ProjectConstant.DEFAULT_TARGET_fILEPATH);
    }

    public ReaderAndWriter(String sourceFile, String targetFile) {
        try {
            this.sourceFile = new BufferedReader(new FileReader(sourceFile));
            this.targetFile = new BufferedWriter(new FileWriter(targetFile));
            this.lineNumber = 0;
            this.hasNewLine = false;
            this.tmpChar = '\0';
            this.isFirstCallRead = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public char read() {
        if (isFirstCallRead) {
            isFirstCallRead = false;
            read0();
            read0();
        }

        return read0();
    }

    //    由于设计原因，导致会在第一行前凭空多读出一个'\n'，所以使用read多调用一次read0()，来fix bug
    private char read0() {
        if (tmpChar != '\0') {
            char ch = tmpChar;
            tmpChar = '\0';
            return ch;
        }

        if (currentLine != null && pos < currentLine.length()) {
            char ch = currentLine.charAt(pos++);
            CharType charType = CharTypeUtil.parseCharType(ch);
            if (charType.equals(CharType.LETTER) || charType.equals(CharType.DIGIT) || charType.equals(CharType.EMPTY)) {
                return ch;
            }

            tmpChar = ch;
            return '\0';
        }

        try {
            currentLine = sourceFile.readLine();
            if (currentLine != null) {
                lineNumber++;
                pos = 0;
                hasNewLine = true;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (hasNewLine) {
            hasNewLine = false;
            tmpChar = '\n';
            return '\0';
        }

        return (char) -1;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public void write(String content) {
        try {
            targetFile.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSourceFile(String filePath) {
        try {
            this.sourceFile = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTargetFile(String filePath) {
        try {
            this.targetFile = new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
