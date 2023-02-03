package cn.pxl.niuke;

//HJ25 信息社会，有海量的数据需要分析处理，比如公安局分析身份证号码、 QQ 用户、手机号码、银行帐号等信息及活动记录。
//采集输入大数据和分类规则，通过大数据分类处理程序，将大数据分类输出。

//输入描述：一组输入整数序列I和一组规则整数序列R，I和R序列的第一个整数为序列的个数（个数不包含第一个整数）；整数范围为0~(2^31)-1，序列个数不限
//输出描述：从R依次中取出R<i>，对I进行处理，找到满足条件的I：
//I整数对应的数字需要连续包含R<i>对应的数字。比如R<i>为23，I为231，那么I包含了R<i>，条件满足 。
//按R<i>从小到大的顺序:
//(1)先输出R<i>；
//(2)再输出满足条件的I的个数；
//(3)然后输出满足条件的I在I序列中的位置索引(从0开始)；
//(4)最后再输出I。
//附加条件：
//(1)R<i>需要从小到大排序。相同的R<i>只需要输出索引小的以及满足条件的I，索引大的需要过滤掉
//(2)如果没有满足条件的I，对应的R<i>不用输出
//(3)最后需要在输出序列的第一个整数位置记录后续整数序列的个数(不包含“个数”本身)
//序列I：15,123,456,786,453,46,7,5,3,665,453456,745,456,786,453,123（第一个15表明后续有15个整数）
//序列R：5,6,3,6,3,0（第一个5表明后续有5个整数）
//输出：30, 3,6,0,123,3,453,7,3,9,453456,13,453,14,123,6,7,1,456,2,786,4,46,8,665,9,453456,11,456,12,786

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//自己实现
public class HJ25 {

    public static void main(String[] args) throws IOException {

//        ArrayList<String> arrayList = new ArrayList<>();
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/pengxiaoliang/Downloads/string.txt"));
//        String str;
//        while ((str = bufferedReader.readLine()) != null){
//            arrayList.add(str);
//        }
//        bufferedReader.close();
//        System.out.println(arrayList);

        ArrayList<String> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            arrayList.add(nextLine);
        }

        //i序列中的每一个串需，如果包含r，那么认为是满足条件的。
        String[] iSplitSerial = arrayList.get(0).split(" ");
        String[] rSplitSerial = arrayList.get(1).split(" ");
        //第一位是计数变量
        int iCount = Integer.parseInt(iSplitSerial[0]);


        //i序列
        List<Integer> iSerialList = Arrays.stream(iSplitSerial).skip(1).map(Integer::parseInt).collect(Collectors.toList());
        //去重并且升序排序后的序列。
        List<Integer> rSerialList = Arrays.stream(rSplitSerial).skip(1).map(Integer::parseInt).distinct().sorted().collect(Collectors.toList());
        //key：r 值
        //value: i 序列索引位置 + i 序列值。
        HashMap<Integer, HashMap<Integer, Integer>> resultMap = new HashMap<>();
        //第一位是计数变量
        int rCount = rSerialList.size();
        //循环遍历 r 序列
        for (int r = 0; r < rCount; r++) {
            Integer rInt = rSerialList.get(r);
            // i 序列索引位置 + i 序列值。
            HashMap<Integer, Integer> rMap = new HashMap<>();
            //循环遍历 i 序列
            for (int i = 0; i < iCount; i++) {
                Integer iInt = iSerialList.get(i);
                //如果i序列包含r序列，那么放入map中。
                if(String.valueOf(iInt).contains(String.valueOf(rInt))){
                    rMap.put(i,iInt);
                }
            }
            if(!rMap.isEmpty()){
                resultMap.put(rInt,rMap);
            }
        }
        //处理最终结果
        StringBuilder builder = new StringBuilder();
        int finalCount = 0;
        for (Integer rResult : rSerialList) {
            if(resultMap.containsKey(rResult)){
                builder.append(rResult + " ");
                finalCount ++;
                //顺序性需要保证
                HashMap<Integer, Integer> value = resultMap.get(rResult);
                List<Integer> collectedKeys = value.keySet().stream().sorted().collect(Collectors.toList());
                int iSize = value.size();
                builder.append(iSize + " ");
                finalCount ++;
                for (Integer collectedKey : collectedKeys) {
                    Integer collectedValue = value.get(collectedKey);
                    builder.append(collectedKey + " " + collectedValue + " ");
                    finalCount = finalCount + 2;
                }
            }
        }
        String finalResult = finalCount + " " + builder.toString().trim();
        System.out.println(finalResult);
    }
}



