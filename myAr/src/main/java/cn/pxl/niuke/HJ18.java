package cn.pxl.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TODO 写法错误！！！
//HJ18 识别有效的IP地址和掩码并进行分类统计
//请解析IP地址和对应的掩码，进行分类识别。要求按照 A/B/C/D/E 类地址归类，不合法的地址和掩码单独归类。

//所有的IP地址划分为 A,B,C,D,E 五类
//A类地址从 1.0.0.0   到 126.255.255.255;
//B类地址从 128.0.0.0 到 191.255.255.255;
//C类地址从 192.0.0.0 到 223.255.255.255;
//D类地址从 224.0.0.0 到 239.255.255.255；
//E类地址从 240.0.0.0 到 255.255.255.255

//私网IP范围是：
//从10.0.0.0到10.255.255.255
//从172.16.0.0到172.31.255.255
//从192.168.0.0到192.168.255.255

//输入描述：多行字符串。每行一个IP地址和掩码，用~隔开。
//输出描述：统计A、B、C、D、E、错误IP地址或错误掩码、私有IP的个数，之间以空格隔开。

public class HJ18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Entity entity = new Entity();
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            String[] splits = next.split("~");
            String[] ipStrs = splits[0].split("\\.");
            String[] mockStrs = splits[1].split("\\.");
            calCount(ipStrs,entity,false);
            calCount(mockStrs,entity,true);
        }
        System.out.println(entity.aCount+ " " + entity.bCount+ " "  + entity.cCount+ " " + entity.dCount+ " " + entity.eCount+ " " + entity.privateIpCount + " " + entity.errorIpOrMaskCount);
        scanner.close();
    }

    static class Entity{
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int dCount = 0;
        int eCount = 0;
        int errorIpOrMaskCount = 0;
        int privateIpCount = 0;
        int privateInt = -1;
        boolean mayBePrivate = false;
    }

    private static void calCount(String[] strs,Entity entity,boolean isMask){

        boolean firstValue = true;
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int strInt;
            try{
                strInt = Integer.parseInt(str);
            } catch (Exception e){
                entity.errorIpOrMaskCount ++;
                return;
            }

            List<String> strings = Arrays.asList(strs);
            int resultValue;
            if(firstValue){
                resultValue = tailIsValid(str,1);
                //如果 为 -1 没有必要继续分类。
                if(resultValue == -1) {
                    entity.errorIpOrMaskCount ++;
                    return;
                }
                firstValue = false;
                //分类
                if(!isMask) collectResult(str,entity);
            } else {
                if(entity.mayBePrivate) {
                    if(172 == entity.privateInt && strInt >= 16 && strInt <= 31){
                        entity.privateIpCount ++;
                    }else if(192 == entity.privateInt && strInt == 168){
                        entity.privateIpCount ++;
                    }
                    entity.mayBePrivate = false;
                }
                resultValue = tailIsValid(str);
            }
            //如果 为 -1 没有必要继续分类。
            if(resultValue == -1) {
                entity.errorIpOrMaskCount ++;
                return;
            }else if(resultValue == -2) continue;
            //子网掩码是否是合法的
            if(isMask){
                maskIsValid(strings,entity.errorIpOrMaskCount);
            }

        }
    }

    //分类
    private static void collectResult(String str, Entity entity){
        int firstInt = Integer.parseInt(str);
        if(1 <= firstInt && firstInt <= 126) {
            entity.aCount ++;
            if(firstInt == 10) entity.privateIpCount ++;
        }else if(128 <= firstInt && firstInt <= 191){
            entity.bCount ++;
            if(firstInt == 172){
                entity.privateInt = 172;
                entity.mayBePrivate = true;
            }
        } else if(192 <= firstInt && firstInt <= 223){
            entity.cCount ++;
            if(firstInt == 192) {
                entity.privateInt = 192;
                entity.mayBePrivate = true;
            }
        }else if(224 <= firstInt && firstInt <= 239){
            entity.dCount ++;
        }else if(240 <= firstInt && firstInt <= 255){
            entity.eCount ++;
        }
    }

    private static void maskIsValid(List<String> maskStrs, int errorIpOrMaskCount){
        if(!maskStrs.isEmpty()) {
            ArrayList<Integer> integers = new ArrayList<>();
            for (String maskStr : maskStrs) {
                int maskInt = Integer.parseInt(maskStr);
                integers.add(maskCheck(maskInt));
            }
            if(integers.contains(-1)) {
                errorIpOrMaskCount ++;
                return;
            }
            for (int i = 0; i < integers.size()-1 ; i++) {
                if(integers.get(i) < integers.get(i + 1)) errorIpOrMaskCount ++;
            }
        }
    }

    //2: 表示255
    //1:表示非255的1开头的二进制值。
    //0: 表示 0；
    //-1:非1开头的二进制值。
    private static int maskCheck(int maskInt){
        for (int j = 1; j <= 8; j++) {
            if(maskInt == (int)(Math.pow(2,j) -1)){
                if(maskInt == 255){
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        if(maskInt == 0) return 0;
        return -1;
    }

    //-2:输入非数字
    //-1:输入数字，但不在0 - 255之间
    private static int tailIsValid(String tailStr){
        return tailIsValid(tailStr,0);
    }

    //-1:输入数字，但不在begin - 255之间
    private static int tailIsValid(String tailStr,int begin){
        int intVal;
        try{
            intVal = Integer.parseInt(tailStr);
        } catch (Exception e){
            return -2;
        }
        if(intVal >= begin && intVal <= 255){
            return intVal;
        }
        return -1;
    }



}
