package com.github.hcsp.maven;

import java.util.Arrays;

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
        // 将俩个版本号转换成 int 数组
        int[] v1 = transferIntVersion(version1);
        int[] v2 = transferIntVersion(version2);

        if (v1[0] < v2[0]) {
            return -1;
        } else if (v1[0] > v2[0]) {
            return 1;
        } else {
            if (v1[1] < v2[1]) {
                return -1;
            } else if (v1[1] > v2[1]) {
                return 1;
            } else {
                if (v1[2] < v2[2]) {
                    return -1;
                } else if (v1[2] > v2[2]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * 将字符串参数的语义化版本分割成int数组
     *
     * @param version
     * @return
     */
    private static int[] transferIntVersion(String version) {
        String[] versions = version.split("\\.");
        // todo 这里假设传的version都是符合要求的
        // 将其补成三个数字
        if (versions.length == 1) {
            version = version + ".0.0";
        } else if (versions.length == 2) {
            version = version + ".0";
        }
        versions = version.split("\\.");
        return Arrays.stream(versions).mapToInt(s -> Integer.parseInt(s)).toArray();
    }
}
