package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.List;

public class Version {

    private static final int NUM_OF_DIGIT = 3; //可限定版本号位数

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
    /*
    1.根据正则表达式按照.进行分割，存到String类型的数组里
    2.将数组内的值存到ArrayList中
    3.补全版本号，均变成NUM_OF_DIGIT位
    4.对这两个list的每一项分别进行比对
    */

    public static int compare(String version1, String version2) {
        String[] version1Array = version1.split("[.]");
        String[] version2Array = version2.split("[.]");

        int len1 = version1Array.length;
        int len2 = version2Array.length;

        List<String> version1List = new ArrayList<>();
        List<String> version2List = new ArrayList<>();

        for (int index = 0; index < len1; index++){
            version1List.add(index, version1Array[index]);
        }
        for (int index = 0; index < len2; index++){
            version2List.add(index, version2Array[index]);
        }

        if (len1 < NUM_OF_DIGIT) {
            for (int i = len1; i < NUM_OF_DIGIT; i++) {
                version1List.add(i, "0");
            }
        }
        if (len2 < NUM_OF_DIGIT) {
            for (int j = len2; j < NUM_OF_DIGIT; j++) {
                version2List.add(j, "0");
            }
        }
        if (version1List.equals(version2List)) {
            return 0;
        }
        for (int i = 0; i < NUM_OF_DIGIT; i++) {
            if (version1List.get(i).equals(version2List.get(i))) {
                continue;
            }
            if (Long.parseLong(version1List.get(i)) > Long.parseLong(version2List.get(i))) {
                return 1;
            }
                return -1;
        }
        return 0;
    }
}
