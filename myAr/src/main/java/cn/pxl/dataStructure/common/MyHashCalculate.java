package cn.pxl.dataStructure.common;

public class MyHashCalculate {

    //int hash 返回本身
    public int intHash(int intKey){
        return intKey;
    }

    //返回浮点数对应的二进制数的整数表示。
    public int floatHash(float floatKey){
        return Float.floatToIntBits(floatKey);
    }

    //long类型，对前后32位数字做异或运算。(不同为1，相同为0)
    public int doubleHash(double doubleKey){
        long longKey = Double.doubleToLongBits(doubleKey);
        return longHash(longKey);
    }

    //long类型，对前后32位数字做异或运算。(不同为1，相同为0)
    public int longHash(long longKey){
        return (int)(longKey ^ (longKey >>> 32));
    }

    //字符串
    public int stringHash(String strHash){
        int hashCode = 0;
        for (int i = 0; i < strHash.length(); i++) {
            char c = strHash.charAt(i);
//            hashCode = hashCode * 31 + c;
            hashCode = hashCode << 5 - hashCode + c;
            // 等价于：每个 char * 31 的对应次方。
            //hashCode << 5 - hashCode + c == hashCode * 31 + c
        }
        return hashCode;
    }
}
