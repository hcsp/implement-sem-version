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
        //分割字符串
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        //取分割数多的
        int length = Math.max(arr1.length, arr2.length);
        //进行循环比较，高到低只要有一位高则整体高
        for (int i = 0; i < length; i++) {
            int one = 0, two = 0;

            if (arr1.length > i) {
                one = Integer.parseInt(arr1[i]);
            }
            if (arr2.length > i) {
                two = Integer.parseInt(arr2[i]);
            }

            if (one > two) {
                return 1;
            } else if (one < two) {
                return -1;
            }
        }
        return 0;
    }
}
