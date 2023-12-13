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
        int[] v1 = splitVersion(version1);
        int[] v2 = splitVersion(version2);

        return compareVersion(v1, v2);
    }

    private static int[] splitVersion(String version) {
        String[] parts = version.split("\\.");
        int[] versionArray = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            versionArray[i] = Integer.parseInt(parts[i]);
        }

        return versionArray;
    }

    private static int compareVersion(int[] v1, int[] v2) {
        int maxLength = Math.max(v1.length, v2.length);

        for (int i = 0; i < maxLength; i++) {
            int v1Number = (i < v1.length) ? v1[i] : 0;
            int v2Number = (i < v2.length) ? v2[i] : 0;

            if (v1Number < v2Number) {
                return -1;
            } else if (v1Number > v2Number) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        // 测试
        System.out.println(compare("1.0.0", "1.0.0"));
        System.out.println(compare("1.0.0", "1.0.1"));
        System.out.println(compare("1.0.0", "1.1.0"));
        System.out.println(compare("1.0.0", "2.0.0"));
    }
}
