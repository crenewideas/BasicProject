package cn.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

@Configuration
@ComponentScan("cn.aop")
//是否开启LTW支持
//ENABLED : 开启LTW：开启类加载期植入切面。
//DISABLED 不开启LTW
//AUTODETECT 如果类路径下能读取到META‐INF/aop.xml文件,则开启LTW,否则关闭
//在Java 语言中，从织入切面的方式上来看，存在三种织入方式：编译期织入、类加载期织入和运行期织入。
// 编译期织入是指在Java编译期，采用特殊的编译器，将切面织入到Java类中；
// 而类加载期织入则指通过特殊的类加载器，在类字节码加载到JVM时，织入切面；
// 运行期织入则是采用CGLib工具或JDK动态代理进行切面的织入。
//AspectJ提供了两种切面织入方式，第一种通过特殊编译器，在编译期，将AspectJ语言编写的切面类织入到Java类中，可以通过一个Ant或Maven任务来完成这个操作；
//第二种方式是类加载期织入，也简称为LTW（Load Time Weaving）
//@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
@EnableAspectJAutoProxy
public class AopConfig {

}
