package cn.pxl.capture05.Subsection02;

import cn.pxl.capture05.common.Manager;

public class Subsection02Object {

    public static void main(String[] args) {
        demo01();
    }

    private static void demo01(){
        Manager manager = new Manager(100, 200);
        Manager manager2 = new Manager(100, 200);
        int hc1 = manager.hashCode();
        int hc2 = manager2.hashCode();
        // 没有重写 hashCode ，会导致 hashCode 的产生算法 依据地址值产生。
        System.out.println(hc1 +" -- "+ hc2); // 1360875712 -- 1625635731
        System.out.println(manager.equals(manager2));// false 没有重写equals方法，比较的是地址值。

        //String 重写了hashCode方法，算法是 h = h*31 + charAt(i)
        String a = "Ok";
        //线程安全的
        StringBuilder StringBuilderA = new StringBuilder(a);
        // String 重写了 hashCode ，是由内容导出 hashCode 的值。
        // StringBuilder 没有重写 hashCode ，是由地址导出 hashCode 的值。
        // 因此 即使内容相同的 StringBuilder ，如果地址不相同，那么 hashCode 的值也很有可能不同。
        System.out.println(a.hashCode() + " -- " + StringBuilderA.hashCode());   //2556 -- 1360875712
        String ok = new String("Ok");
        StringBuilder StringBuilderOk = new StringBuilder(ok);
        System.out.println(ok.hashCode() + " -- " + StringBuilderOk.hashCode()); //2556 -- 1625635731

        // equals 与 hashCode 的定义内必须相容。 如 比较返回 true 那么哈希值也应该返回相同的结果。
    }

}
