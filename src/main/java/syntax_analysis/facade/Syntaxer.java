package syntax_analysis.facade;

import constant.ProjectConstant;
import lexical_analysis.token.Token;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import syntax_analysis.config.SyntaxerConfig;

import java.io.File;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Syntaxer {
    protected AbstractSyntaxerCore core;
    protected AbstractSyntaxerImpl syntaxerImpl;

    public Syntaxer(SyntaxerConfig config) {
        this.core = config.getCore();
        this.syntaxerImpl = config.getSyntaxerImpl();
    }

    /*语法分析*/
    public Syntaxer analyze(List<Token> tokenList) {
        System.out.println("Begin Analying");
        core.read(tokenList);
        core.analyze();
        System.out.println("No Error");
        return this;
    }

    /*打印错误信息*/
    public Syntaxer printErrorInfo() {
        syntaxerImpl.printErrorInfo();
        return this;
    }

    /*打印语法树*/
    public Syntaxer printSyntaxTreeText() {
        syntaxerImpl.printSyntaxTreeText();
        //System.out.println("Print Syntax Tree Text");

        /*System.out.println("\n" +
                "line 2 :Program       ProgramHead   program       \n" +
                "line 2 :                            ProgramName   example       \n" +
                "line 3 :              DeclarePart   TypeDecpart   TypeDec       type          \n" +
                "line 3 :                                                        TypeDecList   TypeId        w1            \n" +
                "line 3 :                                                                      =             \n" +
                "line 3 :                                                                      TypeDef       StructureType RecType       record        \n" +
                "line 4 :                                                                                                                FieldDecList  BaseType      integer       \n" +
                "line 4 :                                                                                                                              IdList        x             \n" +
                "line 4 :                                                                                                                                            IdMore        \n" +
                "line 4 :                                                                                                                              ;             \n" +
                "line 5 :                                                                                                                              FieldDecMore  FieldDecList  BaseType      integer       \n" +
                "line 5 :                                                                                                                                                          IdList        y             \n" +
                "line 5 :|             |             |             |             |             |             |             |             |             |             |             |             IdMore        \n" +
                "line 5 :|             |             |             |             |             |             |             |             |             |             |             ;             \n" +
                "line 6 :|             |             |             |             |             |             |             |             |             |             |             FieldDecMore  \n" +
                "line 6 :|             |             |             |             |             |             |             |             end           \n" +
                "line 6 :|             |             |             |             |             ;             \n" +
                "line 7 :|             |             |             |             |             TypeDecMore   \n" +
                "line 7 :|             |             VarDecpart    VarDec        var           \n" +
                "line 8 :|             |             |             |             VarDecList    TypeDef       BaseType      integer       \n" +
                "line 8 :|             |             |             |             |             VarIdList     a             \n" +
                "line 8 :|             |             |             |             |             |             VarIdMore     \n" +
                "line 8 :|             |             |             |             |             ;             \n" +
                "line 9 :|             |             |             |             |             VarDecMore    VarDecList    TypeDef       BaseType      integer       \n" +
                "line 9 :|             |             |             |             |             |             |             VarIdList     b             \n" +
                "line 9 :|             |             |             |             |             |             |             |             VarIdMore     \n" +
                "line 9 :|             |             |             |             |             |             |             ;             \n" +
                "line 10 :|             |             |             |             |             |             |             VarDecMore    VarDecList    TypeDef       w1            \n" +
                "line 10 :|             |             |             |             |             |             |             |             |             VarIdList     c             \n" +
                "line 10 :|             |             |             |             |             |             |             |             |             |             VarIdMore     \n" +
                "line 10 :|             |             |             |             |             |             |             |             |             ;             \n" +
                "line 12 :|             |             |             |             |             |             |             |             |             VarDecMore    \n" +
                "line 12 :|             |             ProDecpart    ProDec        procedure     \n" +
                "line 12 :|             |             |             |             ProName       sd            \n" +
                "line 12 :|             |             |             |             (             \n" +
                "line 12 :|             |             |             |             ParamList     ParamDecList  Param         TypeDef       BaseType      integer       \n" +
                "line 12 :|             |             |             |             |             |             |             FormList      s1            \n" +
                "line 12 :|             |             |             |             |             |             |             |             FidMore       \n" +
                "line 12 :|             |             |             |             |             |             ParamMore     ;             \n" +
                "line 12 :|             |             |             |             |             |             |             ParamDecList  Param         var           \n" +
                "line 12 :|             |             |             |             |             |             |             |             |             TypeDef       BaseType      integer       \n" +
                "line 12 :|             |             |             |             |             |             |             |             |             FormList      s2            \n" +
                "line 12 :|             |             |             |             |             |             |             |             |             |             FidMore       \n" +
                "line 12 :|             |             |             |             |             |             |             |             ParamMore     \n" +
                "line 12 :|             |             |             |             )             \n" +
                "line 12 :|             |             |             |             ;             \n" +
                "line 13 :|             |             |             |             ProcDecPart   DeclarePart   TypeDecpart   \n" +
                "line 13 :|             |             |             |             |             |             VarDecpart    VarDec        var           \n" +
                "line 13 :|             |             |             |             |             |             |             |             VarDecList    TypeDef       BaseType      integer       \n" +
                "line 13 :|             |             |             |             |             |             |             |             |             VarIdList     ss            \n" +
                "line 13 :|             |             |             |             |             |             |             |             |             |             VarIdMore     \n" +
                "line 13 :|             |             |             |             |             |             |             |             |             ;             \n" +
                "line 14 :|             |             |             |             |             |             |             |             |             VarDecMore    \n" +
                "line 14 :|             |             |             |             |             |             ProDecpart    ProDec        procedure     \n" +
                "line 14 :|             |             |             |             |             |             |             |             ProName       sss           \n" +
                "line 14 :|             |             |             |             |             |             |             |             (             \n" +
                "line 14 :|             |             |             |             |             |             |             |             ParamList     ParamDecList  Param         TypeDef       BaseType      integer       \n" +
                "line 14 :|             |             |             |             |             |             |             |             |             |             |             FormList      a             \n" +
                "line 14 :|             |             |             |             |             |             |             |             |             |             |             |             FidMore       \n" +
                "line 14 :|             |             |             |             |             |             |             |             |             |             ParamMore     \n" +
                "line 14 :|             |             |             |             |             |             |             |             )             \n" +
                "line 14 :|             |             |             |             |             |             |             |             ;             \n" +
                "line 15 :|             |             |             |             |             |             |             |             ProcDecPart   DeclarePart   TypeDecpart   \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             VarDecpart    \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             ProDecpart    ProDec        procedure     \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             ProName       ddd           \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             (             \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             ParamList     ParamDecList  Param         TypeDef       BaseType      integer       \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             FormList      b             \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             FidMore       \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             ParamMore     \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             )             \n" +
                "line 15 :|             |             |             |             |             |             |             |             |             |             |             |             ;             \n" +
                "line 16 :|             |             |             |             |             |             |             |             |             |             |             |             ProcDecPart   DeclarePart   TypeDecpart   \n" +
                "line 16 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             VarDecpart    \n" +
                "line 16 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             ProDecpart    \n" +
                "line 16 :|             |             |             |             |             |             |             |             |             |             |             |             ProcBody      ProgramBody   begin         \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             StmList       Stm           OutputStm     write         \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             (             \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        Variable      b             \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 17 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             )             \n" +
                "line 18 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             StmMore       \n" +
                "line 18 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             end           \n" +
                "line 19 :|             |             |             |             |             |             |             |             |             |             |             |             ProDecMore    \n" +
                "line 19 :|             |             |             |             |             |             |             |             ProcBody      ProgramBody   begin         \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             StmList       Stm           ss            \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             |             |             AssCall       AssignmentRestVariMore      \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             :=            \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        20            \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 20 :|             |             |             |             |             |             |             |             |             |             |             StmMore       ;             \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             StmList       Stm           OutputStm     write         \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             (             \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        Variable      a             \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             )             \n" +
                "line 21 :|             |             |             |             |             |             |             |             |             |             |             |             |             StmMore       ;             \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             StmList       Stm           ddd           \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             AssCall       CallStmRest   (             \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             ActParamList  Exp           Term          Factor        Variable      ss            \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             ActParamMore  \n" +
                "line 22 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             )             \n" +
                "line 23 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             StmMore       \n" +
                "line 23 :|             |             |             |             |             |             |             |             |             |             end           \n" +
                "line 24 :|             |             |             |             |             |             |             |             ProDecMore    \n" +
                "line 24 :|             |             |             |             ProcBody      ProgramBody   begin         \n" +
                "line 25 :|             |             |             |             |             |             StmList       Stm           ConditionalStmif            \n" +
                "line 25 :|             |             |             |             |             |             |             |             |             RelExp        Exp           Term          Factor        Variable      s1            \n" +
                "line 25 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 25 :|             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 25 :|             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 25 :|             |             |             |             |             |             |             |             |             |             OtherRelE     CmpOp         <             \n" +
                "line 25 :|             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        10            \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             then          \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             StmList       Stm           s2            \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             AssCall       AssignmentRestVariMore      \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             :=            \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        Variable      s1            \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     AddOp         +             \n" +
                "line 26 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        10            \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             StmMore       \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             else          \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             StmList       Stm           s2            \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             AssCall       AssignmentRestVariMore      \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             :=            \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        Variable      s1            \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     AddOp         -             \n" +
                "line 27 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             Exp           Term          Factor        10            \n" +
                "line 28 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 28 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 28 :|             |             |             |             |             |             |             |             |             |             StmMore       \n" +
                "line 28 :|             |             |             |             |             |             |             |             |             fi            \n" +
                "line 28 :|             |             |             |             |             |             |             StmMore       ;             \n" +
                "line 29 :|             |             |             |             |             |             |             |             StmList       Stm           ss            \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             AssCall       CallStmRest   (             \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             |             |             ActParamList  Exp           Term          Factor        Variable      s2            \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             |             |             |             ActParamMore  \n" +
                "line 29 :|             |             |             |             |             |             |             |             |             |             |             |             )             \n" +
                "line 30 :|             |             |             |             |             |             |             |             |             StmMore       \n" +
                "line 30 :|             |             |             |             |             |             end           \n" +
                "line 31 :|             |             |             |             ProDecMore    \n" +
                "line 31 :|             ProgramBody   begin         \n" +
                "line 32 :|             |             StmList       Stm           InputStm      read          \n" +
                "line 32 :|             |             |             |             |             (             \n" +
                "line 32 :|             |             |             |             |             Invar         a             \n" +
                "line 32 :|             |             |             |             |             )             \n" +
                "line 32 :|             |             |             StmMore       ;             \n" +
                "line 33 :|             |             |             |             StmList       Stm           sd            \n" +
                "line 33 :|             |             |             |             |             |             AssCall       CallStmRest   (             \n" +
                "line 33 :|             |             |             |             |             |             |             |             ActParamList  Exp           Term          Factor        Variable      a             \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             ActParamMore  ,             \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             ActParamList  Exp           Term          Factor        Variable      b             \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 33 :|             |             |             |             |             |             |             |             |             |             |             ActParamMore  \n" +
                "line 33 :|             |             |             |             |             |             |             |             )             \n" +
                "line 33 :|             |             |             |             |             StmMore       ;             \n" +
                "line 34 :|             |             |             |             |             |             StmList       Stm           OutputStm     write         \n" +
                "line 34 :|             |             |             |             |             |             |             |             |             (             \n" +
                "line 34 :|             |             |             |             |             |             |             |             |             Exp           Term          Factor        Variable      b             \n" +
                "line 34 :|             |             |             |             |             |             |             |             |             |             |             |             |             VariMore      \n" +
                "line 34 :|             |             |             |             |             |             |             |             |             |             |             OtherFactor   \n" +
                "line 34 :|             |             |             |             |             |             |             |             |             |             OtherTerm     \n" +
                "line 34 :|             |             |             |             |             |             |             |             |             )             \n" +
                "line 35 :|             |             |             |             |             |             |             StmMore       \n" +
                "line 35 :|             |             end           ");*/
        return this;
    }

    public Syntaxer printSyntaxTreeGraph() {
        syntaxerImpl.printSyntaxTreeGraph();
        return this;
    }

    public Syntaxer writeTree(File targetFile) {
        syntaxerImpl.writeTree(targetFile);
        return this;
    }

    public Syntaxer writeSyntaxTreeText() {
        return writeSyntaxTreeText(new File(ProjectConstant.DEFAULT_SYNTAX_TREE_TEXT_FILEPATH));
    }

    public Syntaxer writeSyntaxTreeText(File targetFile) {
        syntaxerImpl.writeSyntaxTreeText(targetFile);
        return this;
    }

    public Syntaxer writeSyntaxTreeGraph() {
        return writeSyntaxTreeGraph(new File(ProjectConstant.DEFAULT_SYNTAX_TREE_GRAPH_FILEPATH));
    }

    public Syntaxer writeSyntaxTreeGraph(File targetFile) {
        syntaxerImpl.writeSyntaxTreeGraph(targetFile);
        return this;
    }

    public boolean hasError() {
        return syntaxerImpl.hasError();
    }

    public void filp(SyntaxerConfig config) {
        setCore(config.getCore());
        setSyntaxerImpl(config.getSyntaxerImpl());
    }
}
