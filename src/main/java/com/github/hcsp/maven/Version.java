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
        /*
            要点：
                1. 需要准确的“分割”出每个版本号
                2. 要处理好版本号长度不一致的情况，如 x 与 x.y 等情况
                3. 转换成 int 进行比较（容易实现）
         */
        String[] versions1 = version1.split("\\.");
        String[] versions2 = version2.split("\\.");


        for (int i = 0; i < 3; i++) {
            int a = 0, b = 0;

            if (i < versions1.length) {
                a = Integer.parseInt(versions1[i]);
            }
            if (i < versions2.length) {
                b = Integer.parseInt(versions2[i]);
            }

            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
        }

        return 0;
    }
}
