package cn.pxl.createPattern.singleton.entity;

import java.util.Objects;

//单例模式创建示例一：懒汉式，第一次用到了实例对象，才进行创建。
public class PersonLasy {
    private String name;
    //二：提供一个唯一实例的存储对象。由于单例，
    private volatile static PersonLasy singletonPersonInstance;

    //一：私有化构造方法
    private PersonLasy(){
    }

    //三：提供获取当前单例对象的方法。
    public static PersonLasy getInstance(){
        //singletonPersonInstance 是类变量 多线程时会产生线程安全问题，为保证线程安全，需要处理。
        //如果为空，才会去创建。
        if(Objects.isNull(singletonPersonInstance)){ // 这里判断，如果已经有了单例对象，那么直接获取就可以了，这里没有进行线程操作，所以效率很高。
            //对线程加锁
            //多个线程同时调用newInstance时， Objects.isNull(singletonPersonInstance) 判断可能都为true，所以多个线程都会进来。所以需双重校验。
            synchronized (PersonLasy.class){
                if(Objects.isNull(singletonPersonInstance)){
                    //单例对象的创建，并赋值给Person类的变量。
                    //指令重拍问题，还需要对 singletonPersonInstance 加内存可见性 volatile 关键字。
                    singletonPersonInstance = new PersonLasy();
                }
            }
        }
        return singletonPersonInstance;
    }

}
