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
    private static final int VERSION_STRING_LENGTH = 3;

    public static int compare(String version1, String version2) {
        Integer[] version1Split = stringSplitConvert2IntegerSplit(version1.split("\\."));
        Integer[] version2Split = stringSplitConvert2IntegerSplit(version2.split("\\."));
        int result = 0;
        for (int i = 0; i < VERSION_STRING_LENGTH; i++) {
            result = Integer.compare(version1Split[i], version2Split[i]);
            if (result != 0) {
                return result;
            }
        }
        return result;
    }

    private static Integer[] stringSplitConvert2IntegerSplit(String[] stringSplit) {
        Integer[] integerSplit = new Integer[VERSION_STRING_LENGTH];
        int length = stringSplit.length;
        for (int i = 0; i < length; i++) {
            integerSplit[i] = Integer.parseInt(stringSplit[i]);
        }
        for (int i = length; i < VERSION_STRING_LENGTH; i++) {
            integerSplit[i] = 0;
        }
        return integerSplit;
    }

}
