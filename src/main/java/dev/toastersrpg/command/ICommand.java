package dev.toastersrpg.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ICommand {
    String name() default "";
    String permission() default "";
    int cooldown() default 1;
    boolean noArgs() default false;
}
