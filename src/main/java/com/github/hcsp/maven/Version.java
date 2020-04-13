package com.github.hcsp.maven;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
        //如果是null报异常
        if (version1 == null || version2 == null) {
            throw new IllegalArgumentException("argument may not be null !");
        }
        //如果相等返回0
        if (version1.equals(version2)) {
            return 0;
        }

        //如果碰到非0-9和点的字符就转为-1，以点为分割放入数组
        String[] VersionArra = version1.replaceAll("[^0-9.]", "-1").split("[.]");
        String[] Version2Arb = version2.replaceAll("[^0-9.]", "-1").split("[.]");

        //数组转list，因为Arrays.asList()生产的List没有重写add方法会报错
        //需要重新放入java.util.ArrayList类
        List lista = Arrays.asList(VersionArra);
        List list1 = new ArrayList(lista);
        List listb = Arrays.asList(Version2Arb);
        List list2 = new ArrayList(listb);

        //使用List的Add方法补0
        if (list1.size() == 1) {
            list1.add("0");
            list1.add("0");
        } else if (list1.size() == 2) {
            list1.add("0");
        }
        if (list2.size() == 1) {
            list2.add("0");
            list2.add("0");
        } else if (list2.size() == 2) {
            list2.add("0");
        }

        //转回数组格式再比较
        String[] VersionArr1 = new String[list1.size()];
        String[] VersionArr2 = new String[list2.size()];
        for (int i = 0; i < list1.size(); i++) {
            VersionArr1[i] = (String) list1.get(i);
        }
        for (int i = 0; i < list2.size(); i++) {
            VersionArr2[i] = (String) list2.get(i);
        }

        //循环比较两个数组中同下标的数值
        for (int i = 0; i < VersionArr1.length; i++) {
            if (Integer.parseInt(VersionArr1[i]) < Integer.parseInt(VersionArr2[i])) {
                return -1;
            } else if (Integer.parseInt(VersionArr1[i]) > Integer.parseInt(VersionArr2[i])) {
                return 1;
            }
        }
        return 0;

    }

}