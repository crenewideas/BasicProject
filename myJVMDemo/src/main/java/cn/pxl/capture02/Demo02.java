package cn.pxl.capture02;

import java.util.ArrayList;

public class Demo02 {
   private static int stackLength = 1;

   public static void addOne(){
       stackLength ++;
       addOne();
   }

}
