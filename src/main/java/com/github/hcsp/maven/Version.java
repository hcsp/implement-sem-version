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
        int[] v1 = parseToIntArray(version1);
        int[] v2 = parseToIntArray(version2);
        if (v1[0] != v2[0]) {
            return Integer.compare(v1[0], v2[0]);
        } else if (v1[1] != v2[1]) {
            return Integer.compare(v1[1], v2[1]);
        } else if (v1[2] != v2[2]) {
            return Integer.compare(v1[2], v2[2]);
        }
        return 0;
    }

    public static int[] parseToIntArray(String s) {
        int[] intArray = {0, 0, 0};
        int flag1 = -1, flag2 = -1;
        for (int i = 0;  i < s.length();  i++) {
            if (s.charAt(i) == '.') {
                if (flag1 == -1) {
                    flag1 = i;
                    continue;
                }
                flag2 = i;
            }
        }
        if (flag1 == -1) {
            intArray[0] = Integer.parseInt(s);
            return intArray;
        } else if (flag2 == -1) {
            intArray[0] = indexToInt(0, flag1, s);
            intArray[1] = indexToInt(flag1 + 1, s.length(), s);
            return intArray;
        } else {
            intArray[0] = indexToInt(0, flag1, s);
            intArray[1] = indexToInt(flag1 + 1, flag2, s);
            intArray[2] = indexToInt(flag2 + 1, s.length(), s);
            return intArray;
        }
    }

    public static int indexToInt(int a, int b, String s) {
        if (a - b != 0) {
            return Integer.parseInt(s.substring(a, b));
        }
        return 0;
    }
}
