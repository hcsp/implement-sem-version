package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<Integer> version1List = toIntArray(version1);
        ArrayList<Integer> version2List = toIntArray(version2);
        int maxLength = version1List.size() - version2List.size() > 0 ? version1List.size() : version2List.size();
        for (int i = 0; i < maxLength; i++) {
            int version1Num = getVersionNumber(version1List, i);
            int version2Num = getVersionNumber(version2List, i);
            if (version1Num > version2Num) {
                return 1;
            } else if (version1Num < version2Num) {
                return -1;
            }
        }
        return 0;
    }

    private static ArrayList<Integer> toIntArray(String version) {
        String[] stringArray = version.split("\\.");
        ArrayList<Integer> intList = new ArrayList<>();
        for (String s : stringArray) {
            intList.add(Integer.valueOf(s));
        }
        return intList;
    }

    private static int getVersionNumber(ArrayList<Integer> arrayList, int index) {
        if (index > arrayList.size() - 1) {
            return 0;
        } else {
            return arrayList.get(index);
        }
    }

}
