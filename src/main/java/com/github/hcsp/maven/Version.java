package com.github.hcsp.maven;

public class Version {
    private static final int VERSION_EQUAL_LEVEL = 0;
    private static final int VERSION_HIGH_LEVEL = 1;
    private static final int VERSION_LOW_LEVEL = -1;

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
        version1 = suffixVersion(version1);
        version2 = suffixVersion(version2);
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");

        int minVersionLength = Math.min(version1Array.length, version2Array.length);
        for (int i = 0; i < minVersionLength; i++) {
            if (version1Array[i].equals(version2Array[i])) {
                continue;
            }
            return Integer.parseInt(version1Array[i]) > Integer.parseInt(version2Array[i])
                    ? VERSION_HIGH_LEVEL : VERSION_LOW_LEVEL;
        }
        return VERSION_EQUAL_LEVEL;
    }

    public static String suffixVersion(String version) {
        if (version.length() == 1) {
            version += ".0.0";
        }
        if (version.length() == 3) {
            version += ".0";
        }
        return version;
    }

    public static void main(String[] args) {
        Version.compare("1.0.0", "1");
    }
}
