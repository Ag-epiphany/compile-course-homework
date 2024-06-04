package application;

import compiler.SnlCompiler;
import org.jetbrains.annotations.NotNull;
import syntax_analysis.config.SyntaxerConfig;

import java.io.File;

// 三个参数 [源文件] [输出目录] [语法分析器模式]
public class SnlCompilerApplication {
    public static void main(String @NotNull [] args) {
        StringBuilder errorInfo = new StringBuilder();
        boolean isVarCntTrue = true;

        if (args.length < 1 || args.length > 3) {
            errorInfo.append("参数数量不正确").append('\n');
            isVarCntTrue = false;
        }

        File src = new File(args[0]);
        if (!src.exists()) {
            isVarCntTrue = false;
        }

        File resultDirectory = new File(args[1]);
        if (!resultDirectory.isDirectory() || !resultDirectory.exists()) {
            errorInfo.append("输出目录不存在").append('\n');
            isVarCntTrue = false;
        }

        int configNumber = Integer.parseInt(args[2]);
        if (configNumber != 1 && configNumber != 2) {
            errorInfo.append("模式不存在").append('\n');
            isVarCntTrue = false;
        }

        if (!isVarCntTrue) {
            System.err.println(errorInfo);
            System.exit(1);
        }

//        参数校验之后，正式开始编译
//        记录编译时间
        long startTime = System.nanoTime();

        SnlCompiler snlCompiler = new SnlCompiler();
        snlCompiler.setCompiledResultPathDirectory(resultDirectory);
        snlCompiler.compile(src, configNumber);

        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("本次编译耗时：" + System.console().format("%.3f", durationSeconds));
    }
}
