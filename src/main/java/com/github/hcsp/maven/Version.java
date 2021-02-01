package com.github.hcsp.maven;

import java.util.stream.Stream;

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
        int[] array1 = Stream.of(version1.split("\\."))
                .mapToInt(Integer::parseInt).toArray();
        int[] array2 = Stream.of(version2.split("\\."))
                .mapToInt(Integer::parseInt).toArray();
        return recursiveCompare(array1, array2, 0, 0);
    }

    private static int recursiveCompare(int[] array1, int[] array2, int index1, int index2) {
        if (index1 == array1.length && index2 == array2.length) {
            return 0;
        } else if (index1 == array1.length) {
            return Integer.compare(0, array2[index2]);
        } else if (index2 == array2.length) {
            return Integer.compare(array1[index1], 0);
        }

        int result = Integer.compare(array1[index1], array2[index2]);
        if (result != 0) {
            return result;
        }

        return recursiveCompare(array1, array2, index1 + 1, index2 + 1);
    }

}
