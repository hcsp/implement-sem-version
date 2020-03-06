package com.github.hcsp.maven;

public class Version {
    /**
     * 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，比较两个"语义化版本"
     *
     * <p>传入两个形如x.y.z的字符串，比较两个语义化版本的大小。如果version1小于version2，返回-1；如果version1大于
     * version2，返回1。如果二者相等，返回0。
     *
     * <p>注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * @param version1 传入的版本字符串1，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @param version2 传入的版本字符串2，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @return -1/0/1 当version1 小于/等于/大于 version2时
     */
    public static int compare(String version1, String version2) {
        String[] a = version1.trim().split("\\.");
        String[] b = version2.trim().split("\\.");
        /*
        小知识：
        如果用“.”作为分隔的话,必须是String.split("\\."),这样才能正确的分隔开,不能用String.split(".")
        如果用“|”作为分隔的话,必须是如下写法,String.split("\\|"),这样才能正确的分隔开,不能用String.split("|")，
        “.”和“|”都是转义字符,必须得加"\\"，嘿嘿。
         */
        int n = Math.min(a.length, b.length);
        /*
        对于两个长度不等字符串或是链表，数组等值的比较，这里一般都两种做法
        若是选长串的长度来作为遍历的范围，只需给短的那个串超出部分的值赋0即可
        若是选短串的长度来作为遍历的范围，只需末尾再加层判断，将长串未处理部分处理完即可
         */
        int i;
        for (i = 0; i < n; i++) {
            if (Integer.parseInt(a[i]) > Integer.parseInt(b[i])) {
                return 1;
            } else if (Integer.parseInt(a[i]) < Integer.parseInt(b[i])) {
                return -1;
            } else {//先比较高位，高位不同直接得结果，高位相同什么也不做转入下一位

            }
        }
        while (i < a.length) {//此时b串走完了，a串没完，此时a串后面只要有大于0的数，必然大于b串
            if (Integer.parseInt(a[i]) > 0) {
                return 1;
            } else {
                i++;
            }
            return 0;
        }
        while (i < b.length) {//此时a串走完了，b串没完，此时b串后面只要有大于0的数，必然大于a串
            if (Integer.parseInt(b[i]) > 0) {
                return -1;
            } else {
                i++;
            }
            return 0;
        }
        return 0;
    }

}

