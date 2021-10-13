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
        if (version1.equals(version2)) {
            return 0;
        }
        if (!version1.contains(".") && !version2.contains(".")) {
            return compareString(version1, version2);
        }
        String[] subVersion1 = version1.split("\\.");
        String[] subVersion2 = version2.split("\\.");
        int size = Math.max(subVersion1.length, subVersion2.length);
        int rst = 0;
        for (int i = 0; i < size; i++) {
            if (i >= subVersion1.length) {
                rst = compareString("0", subVersion2[i]);
            } else if (i >= subVersion2.length) {
                rst = compareString(subVersion1[i], "0");
            } else {
                rst = compareString(subVersion1[i], subVersion2[i]);
            }
            if (rst != 0) {
                return rst;
            }
        }
        return rst;
    }

    private static int compareString(String s, String s1) {
        if (s.equals(s1)) {
            return 0;
        }
        while (s != null && s.length() > 1 && s.charAt(0) == '0') {
            s = s.substring(1);
        }

        while (s1 != null && s1.length() > 1 && s1.charAt(0) == '0') {
            s1 = s1.substring(1);
        }

        if (s.length() != s1.length()) {
            return s.length() < s1.length() ? -1 : 1;
        }
        for (int i = 0; i < s.length(); i++) {
            int digit1 = s.charAt(i) - '0';
            int digit2 = s1.charAt(i) - '0';
            if (digit1 < digit2) {
                return -1;
            } else if (digit1 > digit2) {
                return 1;
            }
        }
        return 0;
    }
}
