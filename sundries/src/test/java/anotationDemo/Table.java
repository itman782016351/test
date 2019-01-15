package anotationDemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 数据库表的注解
 * @author liaot
 *  */
@Target({ElementType.TYPE}) //设置作用域为类 接口
@Retention(RetentionPolicy.RUNTIME) //设置生命周期为运行时
public @interface Table {
    String value();  //表名
}