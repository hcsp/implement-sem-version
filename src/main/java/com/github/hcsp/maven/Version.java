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
        String v1 = Uniform(version1);
        String v2 = Uniform(version2);
        if (getMajorVersion(v1) > getMajorVersion(v2)) {
            return 1;
        } else if (getMajorVersion(v1) < getMajorVersion(v2)) {
            return -1;
        } else {
            if (getMinorVersion(v1) > getMinorVersion(v2)) {
                return 1;
            } else if (getMinorVersion(v1) < getMinorVersion(v2)) {
                return -1;
            } else {
                if (getPatchVersion(v1) > getPatchVersion(v2)) {
                    return 1;
                } else if (getPatchVersion(v1) < getPatchVersion(v2)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    public static String Uniform(String version) {      //将版本格式统一成x.y.z
        int count = 0;                                  //count:记录版本字符串中出现的"."的个数
        char[] v = version.toCharArray();
        for (char a : v) {
            if (a == '.') {
                count++;
            }
        }
        if (count == 2) {
            return version;
        } else if (count == 1) {
            return new StringBuilder(version).append(".0").toString();
        } else if (count == 0) {
            return new StringBuilder(version).append(".0.0").toString();
        }
        return "InputIllegal,Please input the version of the format x or x.y or x.y.z";
    }

    public static int getMajorVersion(String version) {     //获取主版本号，并转化为int型
        char[] v = version.toCharArray();
        char[] m = new char[1];                             //假设主版本字符串长度为1
        for (int i = 0; i < v.length; i++) {
            if (v[i] == '.') {
                for (int j = 0; j < i; j++) {
                    m[j] = v[j];
                }
                break;
            }
        }
        return Integer.parseInt(new String(m));
    }

    public static int getMinorVersion(String version) {   //获取次版本号，并转化为int型
        char[] v = version.toCharArray();
        char[] m = new char[3];                           //将字符数组转化为字符串再转化为int型，需要把控好字符数组长度
        int count1 = 0;                                   //假设次本版号字符串最长为3，这里用count1记录数组m的空间使用情况
        for (int i = 0; i < v.length; i++) {
            if (v[i] == '.') {
                for (int j = i + 1; j < v.length; j++) {
                    if (v[j] == '.') {
                        break;
                    }
                    m[j - (i + 1)] = v[j];
                    count1++;
                }
                break;
            }
        }
        char[] s = new char[count1];                            //创建一个与版本号长度相同的字符数组s，并将数组m中的元素导入
        for (int i = 0; i < count1; i++) {
            s[i] = m[i];
        }
        return Integer.parseInt(new String(s));
    }

    public static int getPatchVersion(String version) {         //获取修订号，并转化为int型
        char[] v = version.toCharArray();
        char[] m = new char[3];
        int count2 = 0;                                         //假设次本版号字符串最长为3，用count2记录数组m的空间使用情况
        for (int i = v.length; i > 0; i--) {
            if (v[i - 1] == '.') {
                for (int j = i; j < v.length; j++) {
                    m[j - i] = v[j];
                    count2++;
                }
                break;
            }
        }
        char[] s = new char[count2];                           //创建一个版本号长度相同的字符数组s，并将数组m中的元素导入
        for (int i = 0; i < count2; i++) {
            s[i] = m[i];
        }
        return Integer.parseInt(new String(s));
    }
}
