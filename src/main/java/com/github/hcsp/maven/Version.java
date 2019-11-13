package com.github.hcsp.maven;

import org.apache.commons.lang3.StringUtils;

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
        version1 = convertVersionCompleted(version1);
        version2 = convertVersionCompleted(version2);
        int[] version1Arr = strArrToIntArr(StringUtils.split(version1, '.'));
        int[] version2Arr = strArrToIntArr(StringUtils.split(version2, '.'));
        int result = 0;
        for (int i = 0; i < version1Arr.length; i++) {
            if (version1Arr[i] > version2Arr[i]) {
                result = 1;
                break;
            }
            if (version1Arr[i] < version2Arr[i]) {
                result = -1;
                break;
            }
        }
        return result;
    }

    // 将传入的版本号转换成完整的格式
    private static String convertVersionCompleted(String version) {
        int numOfPoint = StringUtils.countMatches(version, '.');
        String resultVersion = null;
        switch (numOfPoint) {
            case 0:
                resultVersion = version + ".0.0";
                break;
            case 1:
                resultVersion = version + ".0";
                break;
            default:
                resultVersion = version;
        }
        return resultVersion;
    }

    // 将字符串数组转换成int数组
    private static int[] strArrToIntArr(String[] strs) {
        int[] resultArr = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            resultArr[i] = Integer.valueOf(strs[i]);
        }
        return resultArr;
    }

    public static void main(String[] args) {
        System.out.println(compare("1.1", "1.1.1"));
    }
}
