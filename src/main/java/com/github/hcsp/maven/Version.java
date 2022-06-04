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
        //填充为完整版本号并分解
        String[] version1Arr = getAllVersion(version1).split("\\.");
        String[] version2Arr = getAllVersion(version2).split("\\.");

        //对比大版本
        //1.0.0 可以直接比较
        int res;
        res = compareInt(Integer.parseInt(version1Arr[0]), Integer.parseInt(version2Arr[0]));
        if (res != 0) {
            return res;
        }
        res = compareInt(Integer.parseInt(version1Arr[1]), Integer.parseInt(version2Arr[1]));
        if (res != 0) {
            return res;
        }
        res = compareInt(Integer.parseInt(version1Arr[2]), Integer.parseInt(version2Arr[2]));
        return res;
    }

    //对比两个数字大小
    private static int compareInt(int version1, int version2) {
        if (version1 < version2) {
            return -1;
        } else if (version1 > version2) {
            return 1;
        } else {
            return 0;
        }
    }

    //填充不完整的version，例如1扩充成1.0.0,1.1扩充为1.1.0
    private static String getAllVersion(String version) {
        //versionArr.length = 1 or 2 or 3
        String[] versionArr = version.split("\\.");
        StringBuilder newVersion = new StringBuilder(versionArr[0]);
        //1 -> 1 + (.0)-> 1.0 + (.0)
        for (int i = 1; i <= 2; i++) {
            if (versionArr.length > i) {
                newVersion.append(".").append(versionArr[i]);
            } else {
                newVersion.append(".0");
            }
        }
        return newVersion.toString();
    }
}

