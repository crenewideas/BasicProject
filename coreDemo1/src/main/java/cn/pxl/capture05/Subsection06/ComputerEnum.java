package cn.pxl.capture05.Subsection06;
//所有的枚举类，都默认继承了Enum类。
public enum ComputerEnum {

    Dell(200),
    Vivo(300),
    HuaWei(400);

    private int price;

    //枚举的构造器总是私有的。
    private ComputerEnum(int price){
        this.price = price;
    }

}
