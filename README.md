# README

## 1. 项目说明 

> JLU编译原理课设——SNL编译器

### 1.2 项目结构

#### 1.2.1 词法分析器模块

> lexical_analysis

##### 1.2.1.1 说明

1. `lexical_analysis.lexer.Core`为词法分析器核心。
2. `lexical_analysis.lexer.Lexer`为词法分析器，负责将源文件解析为Token序列。

##### 1.2.1.2 使用方法

```Java
Lexer lexer = new Lexer();    //	获取Lexer

String srcFilePath = "源文件路径";
List<Token> tokenList = lexer.generateTokenList(srcFilePath);    //	获取srcFile的Token序列

String targetPath = "存储Token序列的文件路径（目前仅支持.xls，.xlsx）";
lexer.write(tokenList, targetPath);    //	持久化解析出来的Token序列
```

#### 1.2.2 语法分析器模块

## 2. 项目架构

![SNL架构.drawio](https://raw.githubusercontent.com/Ag-epiphany/typora_Pictures/main/SNL架构.drawio.png)

### 2.1 词法分析器

![SNL编译器——词法分析器.drawio](https://raw.githubusercontent.com/Ag-epiphany/typora_Pictures/main/SNL编译器——词法分析器.drawio.png)

#### 2.1.1 DFA

> 确定有限自动机。

1. 当从源文件读入一个字符时，根据输入和当前状态，将当前状态转移到下一个状态。

#### 2.1.2 ReadAndWriter

> 读写器。

1. 读取源文件，为`DFA`提供字符流；
2. 持久化Token序列，保存为.xlsx文件。

#### 2.1.3 HandlerChain

> 处理器链。

##### 2.1.3.1 CharTypeHandler

> 字符类型处理器。

1. 将字符转换为相应的字符类型。

##### 2.1.3.2 LexTypeHandler

> 词法类型处理器。

1. `DFA`到达终止状态时，解析缓存的字符串，得到其所对应的词法类型。

#### 2.1.4 Dictionary

> 字典。

1. 为处理器提供查询服务。

#### 2.1.5 Converter

> 转换器。

1. 持久化Token时，将自定义类型字段转换为.xlsx文件能识别的类型。
