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
        ArrayList<String> version1Arr = new ArrayList<>(Arrays.asList(version1.split("\\.")));
        ArrayList<String> version2Arr = new ArrayList<>(Arrays.asList(version2.split("\\.")));
        solveVersionString(version1Arr);
        solveVersionString(version2Arr);
        for (int i = 0; i < 3; i++) {
            int version1Num = Integer.parseInt(version1Arr.get(i));
            int version2Num = Integer.parseInt(version2Arr.get(i));
            int result = Integer.compare(version1Num, version2Num);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    private static void solveVersionString(ArrayList versionArr) {
        int versionLength = 3;
        int versionArrSize = versionArr.size();
        if (versionArrSize < versionLength) {
            for (int i = 0; i < versionLength - versionArrSize; i++) {
                versionArr.add("0");
            }
        }
    }
}
