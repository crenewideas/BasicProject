package cn.pxl.createPattern.singleton.entity;

import java.util.Objects;

//单例模式创建示例二：饿汉式，在类加载到内存时候，就进行创建。
public class PersonHungry {
    private String name;
    //二：提供一个唯一实例的存储对象。饿汉式，直接创建对象，保证唯一。
    private static PersonHungry singletonPersonInstance = new PersonHungry();

    //一：私有化构造方法
    private PersonHungry(){
    }

    //三：提供获取当前单例对象的方法。
    public static PersonHungry getInstance(){
        //singletonPersonInstance 是类变量 多线程时会产生线程安全问题，为保证线程安全，需要处理。
        //如果为空，才会去创建。
        if(Objects.isNull(singletonPersonInstance)){ // 这里判断，如果已经有了单例对象，那么直接获取就可以了，这里没有进行线程操作，所以效率很高。
            //对线程加锁
            //多个线程同时调用newInstance时， Objects.isNull(singletonPersonInstance) 判断可能都为true，所以多个线程都会进来。所以需双重校验。
            synchronized (PersonHungry.class){
                if(Objects.isNull(singletonPersonInstance)){
                    //单例对象的创建，并赋值给Person类的变量。
                    singletonPersonInstance = new PersonHungry();
                }
            }
        }
        return singletonPersonInstance;
    }

}
