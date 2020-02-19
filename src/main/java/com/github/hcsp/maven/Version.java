package com.github.hcsp.maven;

import java.util.Arrays;
import java.util.List;

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
        List<String> verArray1 = parseVersion(version1);
        List<String> verArray2 = parseVersion(version2);
        int len = Math.max(verArray1.size(), verArray2.size());
        Integer[] arr1 = new Integer[len];
        Integer[] arr2 = new Integer[len];
        int i = 0;
        int result = 0;
        while (i < len) {
            arr1[i] = (i < verArray1.size()) ? Integer.parseInt(verArray1.get(i)) : 0;
            arr2[i] = (i < verArray2.size()) ? Integer.parseInt(verArray2.get(i)) : 0;
            result = arr1[i].compareTo(arr2[i]);
            if (result == 0 && i < (len - 1)) {
                i++;
            } else {
                break;
            }
        }
        return result;
    }

    public static List<String> parseVersion(String version) {
        return Arrays.asList(version.split("\\."));
    }
}
