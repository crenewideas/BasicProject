package cn.pxl.leetCode.demo03Stack;

import java.util.HashMap;
import java.util.Stack;

//https://leetcode.cn/problems/valid-parentheses/
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//有效字符串需满足：
//左括号必须用相同类型的右括号闭合。
//左括号必须以正确的顺序闭合。
//每个右括号都有一个对应的相同类型的左括号。
public class Xlp020 {

    public static void main(String[] args) {
        String str1 = "[[{{[]}}]][]";
        System.out.println(isValid(str1));

        System.out.println(isValid2(str1));
    }

    private static final String ONE_STR = "()";
    private static final String TWO_STR = "[]";
    private static final String THREE_STR = "{}";

    public static boolean isValid(String s) {
        int length = s.length();
        if(length <= 0 || (length % 2) != 0) return false;
        while (s.contains(ONE_STR) || s.contains(TWO_STR) || s.contains(THREE_STR)){
            s = s.replace(ONE_STR,"");
            s = s.replace(TWO_STR,"");
            s = s.replace(THREE_STR,"");
        }
        return s.isEmpty();
    }

    public static boolean isValid2(String s) {
        int length = s.length();
        if(length <= 0 || (length % 2) != 0) return false;
        Stack<Object> leftStack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char oneChar = s.charAt(i);
            if(oneChar == '(' || oneChar == '[' || oneChar == '{'){
                leftStack.push(oneChar);
                continue;
            }
            if(leftStack.isEmpty()){
                return false;
            }
            char popChar = (char) leftStack.pop();
            switch (popChar){
                case '(':
                    if(oneChar != ')') return false;
                    break;
                case '[':
                    if(oneChar != ']') return false;
                    break;
                case '{':
                    if(oneChar != '}') return false;
                    break;
            }
        }
        return leftStack.isEmpty();
    }

    public static boolean isValid3(String s) {

        int length = s.length();
        if(length <= 0 || (length % 2) != 0) return false;
        HashMap<Character, Character> characterCharacterHashMap = new HashMap<>();
        characterCharacterHashMap.put('(',')');
        characterCharacterHashMap.put('[',']');
        characterCharacterHashMap.put('{','}');
        Stack<Object> leftStack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char oneChar = s.charAt(i);
            if(characterCharacterHashMap.containsKey(oneChar)){
                leftStack.push(oneChar);
                continue;
            }
            if(leftStack.isEmpty()){
                return false;
            }
            char popChar = (char) leftStack.pop();
            if (characterCharacterHashMap.get(popChar) != oneChar) return false;
        }
        return leftStack.isEmpty();
    }

}
