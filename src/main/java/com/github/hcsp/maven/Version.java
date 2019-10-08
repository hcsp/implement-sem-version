package com.github.hcsp.maven;

public class Version {
    private static final String SPLIT_POINT = "\\.";

    private static final String ADD_ZERO = ".0";

    private static final int NORMAL_VERSION_LENGTH = 3;

    /**
     * 请根据语义化版本的要求 https://semver.org/lang/zh-CN/ ，比较两个"语义化版本"
     * <p>
     * <p>传入两个形如x.y.z的字符串，比较两个语义化版本的大小。如果version1小于version2，返回-1；如果version1大于
     * version2，返回1。如果二者相等，返回0。
     * <p>
     * <p>注意，如果传入的字符串形如x，则其等价于x.0.0。 如果传入的字符串形如x.y，则其等价于x.y.0。
     *
     * @param version1 传入的版本字符串1，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @param version2 传入的版本字符串2，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @return -1/0/1 当version1 小于/等于/大于 version2时
     */
    public static int compare(String version1, String version2) {
        version1 = addZeroWhenVersionLengthLessThree(version1);
        version2 = addZeroWhenVersionLengthLessThree(version2);
        com.github.zafarkhaja.semver.Version v1 = com.github.zafarkhaja.semver.Version.valueOf(version1);
        com.github.zafarkhaja.semver.Version v2 = com.github.zafarkhaja.semver.Version.valueOf(version2);
        return v1.compareTo(v2);
    }

    private static String addZeroWhenVersionLengthLessThree(String version) {
        String[] versionArray = version.split(SPLIT_POINT);
        if (!(NORMAL_VERSION_LENGTH == versionArray.length)) {
            StringBuffer buffer = new StringBuffer(version);
            for (int i = 0; i < (NORMAL_VERSION_LENGTH - versionArray.length); i++) {
                buffer.append(ADD_ZERO);
            }
            return buffer.toString();
        }
        return version;
    }
}
