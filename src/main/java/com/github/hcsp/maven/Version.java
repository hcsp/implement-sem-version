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
        for (int i = 0; i < 3; i++) {
            int v1 = Integer.parseInt(fixVersion(version1).get(i));
            int v2 = Integer.parseInt(fixVersion(version2).get(i));
            if (v1 < v2) {
                return -1;
            } else if (v1 > v2) {
                return 1;
            }

        }
        return 0;
    }

    public static List<String> fixVersion(String version) {
        List<String> fixList = Arrays.asList(version.split("\\."));
        while ((fixList.size() < 3)) {
            fixList.add("0");
        }
        return fixList;
    }
}
