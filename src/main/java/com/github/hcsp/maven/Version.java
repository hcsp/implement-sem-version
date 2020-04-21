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

        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        // 比对相应的子串
        for (; i < v1.length && i < v2.length; i++) {
            int val1 = Integer.parseInt(v1[i]);
            int val2 = Integer.parseInt(v2[i]);
            if (val1 < val2) return -1;
            if (val1 > val2) return 1;
        }

        if (v2.length > v1.length) {
            for (; i < v2.length; i++) {
                int val = Integer.parseInt(v2[i]);
                if (val != 0) return -1;
            }
        } else if (v1.length > v2.length) {
            for (; i < v1.length; i++) {
                int val = Integer.parseInt(v1[i]);
                if (val != 0) return 1;
            }
        }
        return 0;
    }
}


