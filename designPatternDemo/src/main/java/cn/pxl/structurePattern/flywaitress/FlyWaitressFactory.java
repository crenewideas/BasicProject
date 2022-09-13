package cn.pxl.structurePattern.flywaitress;

import java.util.HashMap;
import java.util.Map;

public class FlyWaitressFactory {

    //共享池
    private static Map<String,FlyWaitressImpl> waitressCache = new HashMap<>();
    static {
        waitressCache.put("1",new FlyWaitressImpl("1","name1"));
        waitressCache.put("2",new FlyWaitressImpl("2","name2"));
    }
    public static void addWaitress(FlyWaitressImpl flyWaitress){
        if (!waitressCache.containsKey(flyWaitress.getId())) {
            synchronized (FlyWaitressInterface.class){
                if(!waitressCache.containsKey(flyWaitress.getId())){
                    waitressCache.put(flyWaitress.getId(),flyWaitress);
                }
            }
        }
    }

    public static void getWaitress(String id){
        if(waitressCache.containsKey(id)){
            FlyWaitressImpl flyWaitress = waitressCache.get(id);
            flyWaitress.doSome();
            flyWaitress.doOther();
        }
    }

}
