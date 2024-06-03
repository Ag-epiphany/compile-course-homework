package syntax_analysis.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import syntax_analysis.facade.AbstractSyntaxerCore;
import syntax_analysis.facade.AbstractSyntaxerImpl;

@Getter
@Setter
// todo 测试结束后删除默认构造函数
@NoArgsConstructor
@AllArgsConstructor
public class SyntaxerConfig {
    private AbstractSyntaxerCore core;
    private AbstractSyntaxerImpl syntaxerImpl;
}
