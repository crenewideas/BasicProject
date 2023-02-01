package cn.pxl.niuke;

import java.util.*;

//运行通过！！！

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

public class HJ18_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Entity entity = new Entity();
        HashMap<Integer, ArrayList<Integer>> ipMap = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> maskMap = new HashMap<>();
        int mapKey = 1;
        ArrayList<Integer> invalideKeys = new ArrayList<>() ;
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            String[] splits = next.split("~");
            String[] ipStrs = splits[0].split("\\.");
            String[] maskStrs = splits[1].split("\\.");
            //读取到符合格式条件的数据，封装到map中
            isValid(ipStrs,mapKey,invalideKeys,true,ipMap,entity);
            isValid(maskStrs,mapKey,invalideKeys,false,maskMap,entity);
            mapKey ++;
        }
        //删除非法的 key 。保证相同 key 的 ip 和 mask 都是合法数据。
        for (Integer oneKey : invalideKeys) {
            ipMap.remove(oneKey);
            maskMap.remove(oneKey);
        }
        invalideKeys.clear();
        //子网掩码格式进行校验，ip地址格式进行校验，不符合的计数变量需要加一
        ipValueIsValid(invalideKeys,ipMap,entity);
        maskValueIsValid(invalideKeys,maskMap,entity);
        //删除非法的 key 。保证相同 key 的 ip 和 mask 都是合法数据。
        for (Integer invalideKey : invalideKeys) {
            ipMap.remove(invalideKey);
            maskMap.remove(invalideKey);
        }
        //最后计算分组。
        collectResult(ipMap,entity);
        System.out.println(entity.aCount+ " " + entity.bCount+ " "  + entity.cCount+ " " + entity.dCount+ " " + entity.eCount + " " + entity.errorIpOrMaskCount + " " + entity.privateIpCount);
    }

    static class Entity{
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        int dCount = 0;
        int eCount = 0;
        int errorIpOrMaskCount = 0;
        int privateIpCount = 0;
    }

    //判断格式是否正确。
    private static void isValid(String[] array,int mapKey,ArrayList<Integer> invalideKey,boolean isIp,HashMap<Integer,ArrayList<Integer>> map,Entity entity){
        ArrayList<Integer> convertInt = new ArrayList<>();
        boolean firstInt = true;
        for (String oneStr : array) {
            try {
                int oneInt = Integer.parseInt(oneStr);
                //类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
                if(firstInt){
                    if (isIp && (oneInt == 0 || oneInt == 127)){
                        invalideKey.add(mapKey);
                        return;
                    }
                    firstInt = false;
                }
                convertInt.add(oneInt);
            //转换失败，说明是非法数据，需要跳过。
            } catch (Exception e){
                invalideKey.add(mapKey);
                //空串需要统计错误数据，而像 * 这种的不需要统计错误次数
                if(oneStr.isEmpty()){
                    entity.errorIpOrMaskCount ++;
                }
                return;
            }
        }
        map.put(mapKey,convertInt);
    }

    //判断子网掩码值是否合法
    private static void maskValueIsValid(ArrayList<Integer> removeKeys,HashMap<Integer,ArrayList<Integer>> maskMap,Entity entity){
        for (Map.Entry<Integer, ArrayList<Integer>> entry : maskMap.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();
            StringBuilder binaryStrBuilder = new StringBuilder();
            for (Integer integer : value) {
                String binaryStr = Integer.toBinaryString(integer);
                while (binaryStr.length() < 8){
                    binaryStr = "0".concat(binaryStr);
                }
                binaryStrBuilder.append(binaryStr);
            }
            String finalBinaryStr = binaryStrBuilder.toString();
            //32位中没有"01"，符合子网掩码条件
            if(finalBinaryStr.contains("0")&&finalBinaryStr.contains("1")&&!finalBinaryStr.contains("01")){
                //doNothing.
            }else {
                //不符合子网掩码条件，将该条记录删除。最后统计数量时，拿初始计数变量beforeMaskSize 减去 maskMap.size()即是不符合条件的。
                removeKeys.add(key);
                //总计的错误个数
                entity.errorIpOrMaskCount ++;
            }
        }
    }

    //判断ip地址值是否合法
    private static void ipValueIsValid(ArrayList<Integer> removeKeys,HashMap<Integer,ArrayList<Integer>> ipMap,Entity entity){
        for (Map.Entry<Integer, ArrayList<Integer>> entry : ipMap.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();
            for (int i = 0; i < value.size(); i++) {
                Integer ipValue = value.get(i);
                if(ipValue < 0 || ipValue > 255) {
                    //总计的错误个数
                    entity.errorIpOrMaskCount ++;
                    removeKeys.add(key);
                }
            }
        }
    }

    //分类
    private static void collectResult(HashMap<Integer, ArrayList<Integer>> ipMap, Entity entity){
        for (Map.Entry<Integer, ArrayList<Integer>> entry : ipMap.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();
            Integer firstInt = value.get(0);
            Integer secondInt = value.get(1);
            if(1 <= firstInt && firstInt <= 126) {
                entity.aCount ++;
                if(10 == firstInt) entity.privateIpCount ++;
            }else if(128 <= firstInt && firstInt <= 191){
                entity.bCount ++;
            } else if(192 <= firstInt && firstInt <= 223){
                entity.cCount ++;
            }else if(224 <= firstInt && firstInt <= 239){
                entity.dCount ++;
            }else if(240 <= firstInt && firstInt <= 255){
                entity.eCount ++;
            }
            if(firstInt == 172){
                if(secondInt >= 16 && secondInt <=31) entity.privateIpCount ++;
            }else if(firstInt == 192) {
                if(secondInt == 168) entity.privateIpCount ++;
            }
        }
    }
}
