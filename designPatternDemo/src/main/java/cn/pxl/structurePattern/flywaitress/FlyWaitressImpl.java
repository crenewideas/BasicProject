package cn.pxl.structurePattern.flywaitress;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.TreeMap;

@Data
@NoArgsConstructor
public class FlyWaitressImpl implements FlyWaitressInterface{

    private String id;
    private String name;
    private boolean serviceFlag = false;

    public FlyWaitressImpl(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void doSome() {
        System.out.println("doSome");
        this.serviceFlag = false;
    }

    @Override
    public void doOther() {
        System.out.println("doOther");
        this.serviceFlag = true;
    }
}
