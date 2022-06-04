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


            // 拆分
            String[] version1Arr = version1.split("\\.");
            String[] version2Arr = version2.split("\\.");
            //版本号转化为数组最大长度为3
            for (int i = 0; i < 3; i++) {
                // 数组长度不够 0 来填补,否则直接转换为int
                int v1 = version1Arr.length > i ? Integer.parseInt(version1Arr[i]) : 0;
                int v2 = version2Arr.length > i ? Integer.parseInt(version2Arr[i]) : 0;
                // 不相等则比较大小,分出结果
                if (v1 != v2) {
                    return v1 < v2 ? -1 : 1;
                }
            }
            // 完全相等返回0
            return 0;
    }
}
