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
        if (version1 == null || version2 == null || "".equals(version1) || "".equals(version2)) {
            throw new NullPointerException("版本号不能为空");
        }
        if (version1.equals(version2)) {
            return 0;
        }
        String[] versions1 = version1.split("\\.");
        String[] versions2 = version2.split("\\.");

        int maxLength = Math.max(versions1.length, versions2.length);

        for (int i = 0; i < maxLength; i++) {
            int part1 = i < versions1.length ? Integer.parseInt(versions1[i]) : 0;
            int part2 = i < versions2.length ? Integer.parseInt(versions2[i]) : 0;
            if (part1 > part2) {
                return 1;
            } else if (part1 < part2) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int compare = Version.compare("1", "1.0");
        System.out.println(compare);
    }
}
