package cn.pxl.niuke;

import java.util.Scanner;

//HJ17 坐标移动
//开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
//输入：
//合法坐标为A(或者D或者W或者S) + 数字（两位以内）
//坐标之间以;分隔。
//非法坐标点需要进行丢弃。如AA10;  A1A;  $%$;  YAD; 等。
//下面是一个简单的例子 如：
//A10;S20;W10;D30;X;A1A;B10A11;;A10;
//输出描述：最终坐标，以逗号分隔
public class HJ17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String scan = "";
        if (scanner.hasNextLine()) {
            scan = scanner.nextLine();
        }
        scanner.close();
        if(scan.isEmpty()) return;
        String[] splits = scan.split(";");
        long x = 0L;
        long y = 0L;
        for (String split : splits) {
            if(split.length() < 2) continue;
            String value = split.substring(1);
            Long longValue = 0L;
            try{
                longValue = Long.valueOf(value);
            }catch (Exception e){
                continue;
            }
            switch (split.substring(0, 1)){
                case "A":
                    x = x - longValue;
                    break;
                case "W":
                    y = y + longValue;
                    break;
                case "S":
                    y = y - longValue;
                    break;
                case "D":
                    x = x + longValue;
                    break;
            }
        }
        System.out.println(x + "," + y);
    }

}
