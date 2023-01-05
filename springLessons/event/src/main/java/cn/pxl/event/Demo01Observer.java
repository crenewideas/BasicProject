package cn.pxl.event;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

//事件监听示例
public class Demo01Observer {
    public static void main(String[] args) {
        MyObservable observable = new MyObservable();
        //添加监听者
        observable.addObserver(new EventObserver());
        observable.addObserver(new EventObserver2());
        observable.notifyObservers("notify message !");
    }

    //监听者
    private static class EventObserver implements Observer{
        @Override
        public void update(Observable o, Object msg) {
            EventObject eventObject = (EventObject) msg;
            System.out.println("EventObserver1 : " + eventObject);
        }
    }

    private static class EventObserver2 implements Observer{
        @Override
        public void update(Observable o, Object msg) {
            EventObject eventObject = (EventObject) msg;
            System.out.println("EventObserver2 : " + eventObject);
        }
    }

    private static class MyObservable extends Observable{
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }
}
