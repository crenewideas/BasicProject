package cn.pxl.niuke;

//HJ19 简单错误记录
//开发一个简单错误记录功能小模块，能够记录出错的代码所在的文件名称和行号。

//1、 记录最多8条错误记录，循环记录，最后只用输出最后出现的八条错误记录。对相同的错误记录只记录一条，但是错误计数增加。最后一个斜杠后面的带后缀名的部分（保留最后16位）和行号完全匹配的记录才做算是“相同”的错误记录。
//2、 超过16个字符的文件名称，只记录文件的最后有效16个字符；
//3、 输入的文件可能带路径，记录文件名称不能带路径。也就是说，哪怕不同路径下的文件，如果它们的名字的后16个字符相同，也被视为相同的错误记录
//4、循环记录时，只以第一次出现的顺序为准，后面重复的不会更新它的出现时间，仍以第一次为准
//输入描述：每组只包含一个测试用例。一个测试用例包含一行或多行字符串。每行包括带路径文件名称，行号，以空格隔开
//输出描述：将所有的记录统计并将结果输出，格式：文件名 代码行数 数目，一个空格隔开

import java.util.*;

public class HJ19 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> arrayList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            arrayList.add(nextLine);
        }
        //先后顺序一定的key集合
        LinkedList<String> sortedKeys = new LinkedList<>();
        //存放已经被循环删除的key，之后再有相同的数据，也不会再记录了
        ArrayList<String> removedKeys = new ArrayList<>();
        HashMap<String, Integer> fullNameAndNumber = new HashMap<>();
        HashMap<String, Integer> nameAndNumber = new HashMap<>();
//        String a = "D:\\zwtymj\\xccb\\ljj\\cqzlyaszjvlsjmkwoqijggmybr 645";
//        String b = "E:\\je\\rzuwnjvnuz 633";
//        String c = "C:\\km\\tgjwpb\\gy\\atl 637";
//        String d = "F:\\weioj\\hadd\\connsh\\rwyfvzsopsuiqjnr 647";
//        String e = "E:\\ns\\mfwj\\wqkoki\\eez 648";
//        String f = "D:\\cfmwafhhgeyawnool 649";
//        String g = "E:\\czt\\opwip\\osnll\\c 637";
//        String h = "G:\\nt\\f 633";
//        String i = "F:\\fop\\ywzqaop 631";
//        String j = "F:\\yay\\jc\\ywzqaop 631";
//        String k = "D:\\zwtymj\\xccb\\ljj\\cqzlyaszjvlsjmkwoqijggmybr 645";
//        ArrayList<String> arrayList = new ArrayList<>();
//        arrayList.add(a);
//        arrayList.add(b);
//        arrayList.add(c);
//        arrayList.add(d);
//        arrayList.add(e);
//        arrayList.add(f);
//        arrayList.add(g);
//        arrayList.add(h);
//        arrayList.add(i);
//        arrayList.add(j);
//        arrayList.add(k);
        for (String oneStr : arrayList) {
            String[] splits = oneStr.split(" ");
            String fullName = splits[0];
            String numberStr = splits[1];
            //对全名称截取
            String[] nameStr = fullName.split("\\\\");
            String name =  nameStr[nameStr.length - 1];
            if(name.length() > 16){
                name = name.substring(name.length() - 16);
            }
            String mapKey = name + "-" + numberStr;
            if(removedKeys.contains(mapKey)) continue;
            if(!fullNameAndNumber.containsKey(mapKey)){
                fullNameAndNumber.put(mapKey,1);
                sortedKeys.add(mapKey);
            } else {
                Integer integer = fullNameAndNumber.get(mapKey);
                fullNameAndNumber.put(mapKey,++integer);
            }
            if (fullNameAndNumber.size() > 8) {
                String removeStr = sortedKeys.remove(0);
                fullNameAndNumber.remove(removeStr);
                removedKeys.add(removeStr);
            }
        }

        for (String sortedKey : sortedKeys) {
            Integer count = fullNameAndNumber.get(sortedKey);
            String[] split = sortedKey.split("-");
            String name = split[0];
            String lineNumber = split[1];
            System.out.println(name + " " + lineNumber + " " + count);
        }
    }
}


