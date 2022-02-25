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
        String[] version1Numbers = version1.split("\\.");
        String[] version2Numbers = version2.split("\\.");
        for (int i = 0; i < 3; i++) {
            int version1Number = 0;
            int version2Number = 0;
            if (i <= version1Numbers.length - 1) {
                version1Number = Integer.parseInt(version1Numbers[i]);
            }
            if (i <= version2Numbers.length - 1) {
                version2Number = Integer.parseInt(version2Numbers[i]);
            }
            if (version1Number < version2Number) {
                return -1;
            }
            if (version1Number > version2Number) {
                return 1;
            }
        }
        return 0;
    }
}
