package com.fanzs.basic;

public class StringInternDemo {

    /**
     *
     * 参考
     *     https://juejin.im/entry/6844903544999395342
     *     https://blog.csdn.net/javazejian/article/details/51192130
     *
     * @param args
     */
    public static void main(String[] args) {
        String str1 = "java";
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1.intern() == str1);  // true
        System.out.println();

        String str2 = new StringBuilder("meituan").append("dianping").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2.intern() == str2);  // true
        System.out.println();

        String str3 = new StringBuilder("ja").append("va").toString();
        System.out.println(str3);
        System.out.println(str3.intern());
        System.out.println(str3.intern() == str3);  // false
        System.out.println();

        String str4 = "alibaba";
        String str5 = "alibaba";
        System.out.println(str4 == str5); // true
        System.out.println();

        String str6 = new String("tengxun");
        String str7 = new String("tengxun");
        System.out.println(str6 == str7);  // false
        System.out.println();
    }
}
