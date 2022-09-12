package cn.pxl.capture05.Subsection06;

public class Subsection06Enum {
    public static void main(String[] args) {
        ComputerEnum dell = ComputerEnum.Dell;
        //所有的枚举类型的类，都继承了Enum类。
        String toString = dell.toString();//Enum类中的方法，返回当前枚举的名称
        ComputerEnum valueOfComp = Enum.valueOf(ComputerEnum.class, "Dell");//与同String方法相反，给定一个字符串名，将它转变为枚举类型。
        ComputerEnum[] values = ComputerEnum.values();//获取枚举数组。
        int ordinal = ComputerEnum.Dell.ordinal();//返回Dell枚举常量的位置。
        System.out.println(ordinal);//0;
    }
}
