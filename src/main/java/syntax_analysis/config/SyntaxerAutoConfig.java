package syntax_analysis.config;

import syntax_analysis.facade.AbstractSyntaxerCore;
import syntax_analysis.facade.AbstractSyntaxerImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SyntaxerAutoConfig {
    Class<? extends AbstractSyntaxerCore>[] cores();

    Class<? extends AbstractSyntaxerImpl>[] impls();
}
