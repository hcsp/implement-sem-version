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
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0, j = 0; i < v1.length || j < v2.length; i++, j++) {
            // 如果i、j大于v1、v2的length，取0
            int c1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int c2 = j < v2.length ? Integer.parseInt(v2[j]) : 0;

            if (c1 == c2) {
                continue;
            } else {
                return c1 > c2 ? 1 : -1;    // c1 大，说明第一个版本大 ,否则反之。
            }
        }
        return 0;
    }
}
