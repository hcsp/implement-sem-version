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
        //造轮子开始！

        String[] split3 = new String[3];
        String[] split4 = new String[3];

        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");

        System.arraycopy(split1, 0, split3, 0, split1.length);
        System.arraycopy(split2, 0, split4, 0, split2.length);

        if (split1.length < 3) {
            for (int i = split1.length; i <= 2; i++) {
                split3[i] = "0";
            }
        }

        if (split2.length < 3) {
            for (int i = split2.length; i <= 2; i++) {
                split4[i] = "0";
            }
        }


        if (Integer.valueOf(split3[0]) > Integer.valueOf(split4[0])) {
            return 1;
        } else if (Integer.valueOf(split3[0]) < Integer.valueOf(split4[0])) {
            return -1;
        } else {
            if (Integer.valueOf(split3[1]) > Integer.valueOf(split4[1])) {
                return 1;
            } else if (Integer.valueOf(split3[1]) < Integer.valueOf(split4[1])) {
                return -1;
            } else {
                if (Integer.valueOf(split3[2]) > Integer.valueOf(split4[2])) {
                    return 1;
                } else if (Integer.valueOf(split3[2]) < Integer.valueOf(split4[2])) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }
}
