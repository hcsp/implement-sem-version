package com.github.hcsp.maven;

public class Version {
    // 语义化版本最大长度
    private static final int MAX_LENGTH = 3;

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
        String[] v1Arr = version1.split("\\.");
        String[] v2Arr = version2.split("\\.");

        // 长度不够补0, 否则转成int
        for (int i = 0; i < MAX_LENGTH; i++) {
            int v1 = v1Arr.length > i ? Integer.parseInt(v1Arr[i]) : 0;
            int v2 = v2Arr.length > i ? Integer.parseInt(v2Arr[i]) : 0;

            // 不相等则直接比较，返回结果
            if (v1 != v2) {
                return Integer.compare(v1, v2);
            }
        }
        // 完全相等返回0
        return 0;
    }



}
