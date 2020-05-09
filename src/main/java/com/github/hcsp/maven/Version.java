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
        version1 = bq(version1);
        version2 = bq(version2);
        System.out.println("version1 = " + version1);
        System.out.println("version2 = " + version2);
        int x1 = Integer.parseInt(version1.substring(0, version1.indexOf('.')));
        int y1 = Integer.parseInt(version1.substring(version1.indexOf('.') + 1, version1.lastIndexOf('.')));
        int z1 = Integer.parseInt(version1.substring(version1.lastIndexOf('.') + 1));
        int x2 = Integer.parseInt(version2.substring(0, version2.indexOf('.')));
        int y2 = Integer.parseInt(version2.substring(version2.indexOf('.') + 1, version2.lastIndexOf('.')));
        int z2 = Integer.parseInt(version2.substring(version2.lastIndexOf('.') + 1));
        if (x1 > x2) {
            return 1;
        } else if (x1 == x2) {
            if (y1 > y2) {
                return 1;
            } else if (y1 == y2) {
                if (z1 > z2) {
                    return 1;
                } else if (z1 == z2) {
                    return 0;
                }else {
                    return -1;
                }
            }else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    public static String bq(String v) {
        int i = v.indexOf('.');
        if (i > 0) {
            if (v.indexOf('.', (i + 1)) > 0) {
                return v;
            } else {
                return v + ".0";
            }
        } else {
            return v + ".0.0";
        }
    }


    public static void main(String[] args) {
        System.out.println(compare("1.1", "1.0"));
    }
    }

