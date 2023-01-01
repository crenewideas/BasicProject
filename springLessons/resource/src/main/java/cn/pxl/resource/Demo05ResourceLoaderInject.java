package cn.pxl.resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

//注入 ResourceLoader
public class Demo05ResourceLoaderInject implements ResourceLoaderAware {

    //一：通过Autowired注解自动注入
    @Autowired
    private ResourceLoader resourceLoader;

    //二：通过回调的方式注入
    private ResourceLoader awareResourceLoader;


    //三：注入ApplicationContext作为ResourceLoader
    private AbstractApplicationContext abstractApplicationContext;

    @PostConstruct
    private void init(){
        System.out.println("resourceLoader == awareResourceLoader ? : " + (resourceLoader == awareResourceLoader));
        System.out.println("resourceLoader == abstractApplicationContext ? : " + (resourceLoader == abstractApplicationContext));
        System.out.println("resourceLoader : " + resourceLoader);
        System.out.println("awareResourceLoader : " + awareResourceLoader);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Demo05ResourceLoaderInject.class);
        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.awareResourceLoader = resourceLoader;
    }
}
