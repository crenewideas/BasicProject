package cn.pxl.capture03.subsection01;

import java.util.ArrayList;
import java.util.List;

public class Cap3Demo01 {
    //保证线程之间 变量的可见性
    private volatile List<String> list = new ArrayList<>();

    public void addToList(){
        list.add("PXL");
    }

    public int getSize(){
        return list.size();
    }

}
