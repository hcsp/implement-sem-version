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
        //将x/x.y转换成x.0.0/x.y.0
        String[] v1 = change2Stand(version1).split("\\.");
        String[] v2 = change2Stand(version2).split("\\.");

        int l = Integer.parseInt(v1[0]) - Integer.parseInt(v2[0]);
        int m = Integer.parseInt(v1[1]) - Integer.parseInt(v2[1]);
        int r = Integer.parseInt(v1[2]) - Integer.parseInt(v2[2]);
        if (l > 0) {
            return 1;
        } else if (l < 0) {
            return -1;
        } else {
            if (m > 0) {
                return 1;
            } else if (m < 0) {
                return -1;
            } else {
                if (r > 0) {
                    return 1;
                } else if (r < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static String change2Stand(String version) {
        StringBuilder standard = new StringBuilder(version);
        String[] splits = version.split("\\.");
        if (splits.length == 1) {
            standard.append(".0.0");
        }
        if (splits.length == 2) {
            standard.append(".0");
        }
        return standard.toString();
    }
}
