package cn.pxl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

    }

    public boolean isSubsequence(String s, String t) {
        if(t == null || s == null || "".equals(t.trim()) || "".equals(s.trim()) || s.length() >= t.length()){
            return false;
        }

        int beforeValue = 0;
        int afterValue = 0;
        char[] chars = t.toCharArray();
        int length = s.length();
        int[][] existIndexs = new int[length][t.length()];
        int existCount = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < chars.length; j++) {
                if (c == chars[j]){
                    existIndexs[i][existCount] = j;
                    existCount ++;
                };
            }
            if(existCount == 0){
                return false;
            }
            existCount = 0;
        }

        for (int i = 0; i < length; i++) {
            if(i == length-1){
                return true;
            }
            int[] existIndexBeforeArray = existIndexs[i];
            int[] existIndexAfterArray = existIndexs[i + 1];

        }

        return false;
    }

}
