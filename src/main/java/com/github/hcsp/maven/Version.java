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
    private static final String POSTFIX = ".0";
    private static final int STANDARD_LENGTH = 3;

    public static int compare(String version1, String version2) {
        version1 = versionNormalization(version1);
        version2 = versionNormalization(version2);
        String[] splitVersion1 = version1.split("\\.");
        String[] splitVersion2 = version2.split("\\.");
        for (int i = 0; i < splitVersion1.length; i++) {
            int ver1 = Integer.parseInt(splitVersion1[i]);
            int ver2 = Integer.parseInt(splitVersion2[i]);
            if (ver1 < ver2) {
                return -1;
            } else if (ver2 < ver1) {
                return 1;
            }
        }

        return 0;
    }

    // 版本号补全
    // Input 1 Output 1.0.0
    // Input 1.1 Output 1.1.0
    private static String versionNormalization(String version) {
        int splitVersionLength = version.split("\\.").length;
        String postfix = getPostfix(STANDARD_LENGTH - splitVersionLength);
        return version + postfix;
    }

    private static String getPostfix(int n) {
        if (n == 0) {
            return "";
        } else {
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < n; i++) {
                temp.append(POSTFIX);
            }
            return temp.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(Version.compare("1.0", "1.1"));
    }
}
