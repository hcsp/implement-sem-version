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
        ArrayList<String> versionList1 = new ArrayList<>(Arrays.asList(version1.split("\\.")));
        ArrayList<String> versionList2 = new ArrayList<>(Arrays.asList(version2.split("\\.")));
        fixVersionNum(versionList1);
        fixVersionNum(versionList2);
        for (int i = 0; i < versionList1.size(); i++) {
            int compareResult = compareTwoVersionNum(versionList1.get(i), versionList2.get(i));
            if (compareResult != 0) {
                return compareResult;
            }
        }
        return 0;
    }

    public static void fixVersionNum(ArrayList<String> version) {
        while (version.size() < 3) {
            version.add("0");
        }
    }

    public static int compareTwoVersionNum(String versionNum1, String versionNum2) {
        Integer v1 = Integer.parseInt(versionNum1);
        Integer v2 = Integer.parseInt(versionNum2);
        return v1.compareTo(v2);
    }
}
