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
        version1 = convert(version1);
        version2 = convert(version2);
        final String[] version1Arr = version1.split("\\.");
        final String[] version2Arr = version2.split("\\.");
        for (int i = 0; i < version1Arr.length; i++) {
            if (Integer.parseInt(version1Arr[i]) > Integer.parseInt(version2Arr[i])) {
                return 1;
            } else if (Integer.parseInt(version1Arr[i]) < Integer.parseInt(version2Arr[i])) {
                return -1;
            }
        }
        return 0;
    }

    private static String convert(String version) {
        if (!version.contains(".")) {
            return version + ".0.0";
        }
        if (version.indexOf(".") == version.lastIndexOf(".")) {
            return version + ".0";
        }
        return version;
    }
}

