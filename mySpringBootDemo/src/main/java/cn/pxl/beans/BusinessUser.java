package cn.pxl.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//@Component
public class BusinessUser implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private AnimalBean animalBean;

    public AnimalBean getAnimalBean() {
        return animalBean;
    }

    @Autowired
    @Qualifier("animalBean")
    public void setAnimalBean(AnimalBean animalBean) {
        this.animalBean = animalBean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 BeanFactoryAware 的 setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 BeanNameAware 的 setBeanName");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 DisposableBean 的 destroy");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 InitializingBean 的 afterPropertiesSet");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 ApplicationContextAware 的 setApplicationContext");
    }

    @PostConstruct
    public void init()  {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 @PostConstruct 定义的自定义初始化方法");
    }

    @PreDestroy
    public void preDestroy()  {
        System.out.println("{"+this.getClass().getSimpleName()+"} 调用 @PreDestroy 定义的自定义销毁方法");
    }

}
