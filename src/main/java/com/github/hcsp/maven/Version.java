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
    public static void main(String[] args) {
        compare("0.0", "0.0.1");
    }

    public static int compare(String version1, String version2) {


        String[] version1Array = getVersion(version1).split("\\.");
        String[] version2Array = getVersion(version2).split("\\.");

        int i = compareInt(Integer.parseInt(version1Array[0]), Integer.parseInt(version2Array[0]));
        if (i != 0) {
            return i;
        }
        i = compareInt(Integer.parseInt(version1Array[1]), Integer.parseInt(version2Array[1]));
        if (i != 0) {
            return i;
        }
        i = compareInt(Integer.parseInt(version1Array[2]), Integer.parseInt(version2Array[2]));
        return i;

    }

    private static String getVersion(String version) {
        String[] str = version.split("\\.");
        String s = str[0];
        for (int i = 1; i <= 2; i++) {
            if (str.length > i) {
                s += "." + str[i];
            } else {
                s += ".0";
            }

        }
        return s;

    }

    private static int compareInt(int version1, int version2) {
        if (version1 < version2) {
            return -1;
        } else if (version1 > version2) {
            return 1;
        } else {
            return 0;
        }


    }
}
