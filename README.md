# README

## 1. 项目说明 

> JLU编译原理课设——SNL编译器

## 1.2 项目结构

### 1.2.1 词法分析器模块

> lexical_analysis

#### 1.2.1.1 说明

1. `lexical_analysis.lexer.Core`为词法分析器核心。
2. `lexical_analysis.lexer.Lexer`为词法分析器，负责将源文件解析为Token序列。

#### 1.2.1.2 使用方法

```Java
Lexer lexer = new Lexer();    //	获取Lexer

String srcFilePath = "源文件路径";
List<Token> tokenList = lexer.generateTokenList(srcFilePath);    //	获取srcFile的Token序列

String targetPath = "存储Token序列的文件路径（目前仅支持.xls，.xlsx）";
lexer.write(tokenList, targetPath);    //	持久化解析出来的Token序列
```

### 1.2.2 语法分析器模块

## 2. 项目架构

