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
//    public static int compare(String version1, String version2) {}
    public static int compare(String version1, String version2) {
        ArrayList<Integer> versionList1 = versionStringConvertArray(version1);
        ArrayList<Integer> versionList2 = versionStringConvertArray(version2);
        int result;
        for (int i = 0; true; i++) {
            result = versionList1.get(i).compareTo(versionList2.get(i));
            if (result != 0) {
                return result;
            } else if (i == 2) {
                return result;
            }
        }
    }

    private static ArrayList<Integer> versionStringConvertArray(String version) {
        ArrayList<String> stringList = new ArrayList<>();
        if (!version.contains(".")) {
            stringList.add(version);
        } else {
            stringList.addAll(Arrays.asList(version.split("\\.")));
        }
        ArrayList<Integer> intList = new ArrayList<>();
        for (String string : stringList) {
            intList.add(Integer.valueOf(string));
        }
        if (intList.size() < 3) {
            int difference = 3 - intList.size();
            for (int i = 0; i < difference; i++) {
                intList.add(0);
            }
        }
        return intList;
    }
}
