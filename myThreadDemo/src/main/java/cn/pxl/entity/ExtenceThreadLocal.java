package cn.pxl.entity;

import lombok.Data;

import java.lang.ref.WeakReference;

public class ExtenceThreadLocal {

    public static void main(String[] args) {
        ThreadLocalMap threadLocalMap = new ThreadLocalMap("1", "1");
        ThreadLocalMap threadLocalMap1 = new ThreadLocalMap(threadLocalMap);
        ThreadLocalMap.MyEntry[] table = threadLocalMap.getTable();
        table[0].setValue("2");
        System.out.println(threadLocalMap1.getTable()[0].getValue());

    }

    @Data
    static class ThreadLocalMap {

        private MyEntry[] table;

        @Data
        static class MyEntry {
            /** The value associated with this ThreadLocal. */
            String key;
            Object value;
            MyEntry(String k, Object v) {
                key = k;
                value = v;
            }

        }

        ThreadLocalMap(String firstKey, Object firstValue) {
            table = new MyEntry[16];
            table[0] = new MyEntry(firstKey, firstValue);
        }

        private ThreadLocalMap(ThreadLocalMap parentMap) {
            MyEntry[] parentTable = parentMap.table;
            int len = parentTable.length;
            table = new MyEntry[len];
            for (int j = 0; j < len; j++) {
                MyEntry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    String key = e.getKey();
                    if (key != null) {
                        MyEntry c = new MyEntry(key, e.getValue());
                        table[0] = c;
                    }
                }
            }
        }
    }

}
