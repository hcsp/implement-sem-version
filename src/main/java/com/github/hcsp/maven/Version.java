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
     * @return int 如果version1小于version2，返回-1；如果version1大于version2，返回1。如果二者相等，返回0。
     *
     */
    // https://stackoverflow.com/questions/198431/how-do-you-compare-two-version-strings-in-java
    public static int compare(String version1, String version2) {
        String[] version1Parts = version1.split("\\.");
        String[] version2Parts = version2.split("\\.");
        int length = Math.max(version1Parts.length, version2Parts.length);
        for (int i = 0; i < length; i++) {
            int version1Part = i < version1Parts.length ?
                    Integer.parseInt(version1Parts[i]) : 0;
            int version2Part = i < version2Parts.length ?
                    Integer.parseInt(version2Parts[i]) : 0;
            if (version1Part < version2Part) {
                return -1;
            }
            if (version1Part > version2Part) {
                return 1;
            }
        }
        return 0;
    }
}
