package cn.pxl.niuke;

//HJ24 合唱队
//N 位同学站成一排，音乐老师要请最少的同学出列，使得剩下的 K 位同学排成合唱队形。
//通俗来说，能找到一个同学，他的两边的同学身高都依次严格降低的队形就是合唱队形。
//例子：
//123 124 125 123 121 是一个合唱队形
//123 123 124 122不是合唱队形，因为前两名同学身高相等，不符合要求
//123 122 121 122不是合唱队形，因为找不到一个同学，他的两侧同学身高递减。
//你的任务是，已知所有N位同学的身高，计算最少需要几位同学出列，可以使得剩下的同学排成合唱队形。
//输入描述：用例两行数据，第一行是同学的总数 N ，第二行是 N 位同学的身高，以空格隔开
//输出描述：最少需要几位同学出列

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

//自己实现
public class HJ24 {

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

        int count = Integer.parseInt(arrayList.get(0));
        String highData = arrayList.get(1);
        List<Integer> intList = Arrays.stream(highData.split(" ")).map(Integer::parseInt).collect(Collectors.toList());

        int[] leftCount = new int[count];   //存储索引位置为n的地方，左侧最大递增子序列的长度。
        int[] rightCount = new int[count];  //存储索引位置为n的地方，右侧最大递增减序列的长度。

        //计算左侧最大递增子序列。
        for (int i = 0; i < count; i++) {
            //  位置为 i 的元素，初始左侧最大递增子序列为1（只有自己）。同时，下方的j次循环，每一次都会更新这个值，
            //  表示 前 j 个位置的元素与 i 的最大递增子序列长度。当j次循环完成之后，表示第i个位置实际的最大递增序列的长度。
            leftCount[i] = 1;
            //第i个位置的元素 ，作为要比较的元素
            Integer compareInt = intList.get(i);
            for (int j = 0; j <= i; j++) {
                //逐个比较i位置之前的所有元素的值。
                if(compareInt > intList.get(j)){
                    //第i个位置的值比第j个位置的值要大，说明递增效果成立，第i个位置与前j个位置的左侧最大递增子序列 = 1 + leftCount[j]。
                    //因为left[j]就是前j个元素的左侧最大递增子序列
                    int leftJAndI = leftCount[j] +1;
                    //  比较 leftJAndI 与 leftCount[i] 的值，二者取最大，作为最终 leftCount[i] 的值。因为 leftJAndI 只是前 j 个位置的元素与 i 的最大递增子序列；
                    //  有可能第 j+1 位置的递增子序列比 j 位置的还要长 ，那么 理所当然，要将 leftCount[i] 的值更新为 leftJAddOneAndI;
                    leftCount[i] = Math.max(leftCount[i],leftJAndI);
                }
            }
        }

        //计算右侧最大递增子序列。
        for (int i = count - 1; i > 0; i--) {
            //  位置为 i 的元素，初始右侧最大递减子序列为1（只有自己）。同时，下方的j次循环，每一次都会更新这个值，
            //  表示 后 j 个位置的元素与 i 的最大递减子序列长度。当 j 次循环完成之后，表示第 i 个位置实际的最大递减序列的长度。
            rightCount[i] = 1;
            //第 i 个位置的元素 ，作为要比较的元素
            Integer compareInt = intList.get(i);
            for (int j = count -1 ; j >= i ; j--) {
                //逐个比较 i 位置之后有元素的值。
                if(compareInt > intList.get(j)){
                    //第 i 个位置的值比第 j 个位置的值要大，说明递减效果成立，第 i 个位置与前 j 个位置的右侧最大递减子序列 = 1 + rightCount[j]。
                    //因为left[j]就是前j个元素的左侧最大递增子序列
                    int rightJAndI = rightCount[j] +1;
                    //  比较 rightJAndI 与 rightCount[i] 的值，二者取最大，作为最终 rightCount[i] 的值。因为 rightJAndI 只是前 j 个位置的元素与 i 的最大递减子序列；
                    //  有可能第 j - 1 位置的递减子序列比 j 位置的还要长 ，那么 理所当然，要将 rightCount[i] 的值更新为 rightJAddOneAndI;
                    rightCount[i] = Math.max(rightCount[i],rightJAndI);
                }
            }
        }

        //循环结束，将二者子序列长度相加 - 1 (二者 i 位置的元素重复了，需要减去 1 )，作为最终长度，并比较即可。
        int finalResult = 0;
        for (int i = 0; i < count; i++) {
            int maxLongCount = leftCount[i] + rightCount[i] - 1;
            finalResult = Math.max(finalResult,maxLongCount);
        }

        System.out.println(count - finalResult);

    }
}



