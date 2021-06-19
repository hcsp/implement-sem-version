package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<Integer> version1Array = getFullVersionArray(version1);
        List<Integer> version2Array = getFullVersionArray(version2);
        int result = 0;

        for (int i = 0; i < version1Array.size(); i++) {
            if (version1Array.get(i) > version2Array.get(i)) {
                result = 1;
                break;
            } else if (version1Array.get(i) < version2Array.get(i)) {
                result = -1;
                break;
            }
        }

        return result;
    }

    public static List<Integer> getFullVersionArray(String version) {
        List<Integer> versionList = new ArrayList<>();
        for (String s : version.split("\\.")) {
            versionList.add(Integer.valueOf(s));
        }
        int length = versionList.size();
        if (length == 0) {
            throw new Error("This is Error Version");
        } else if (length == 1) {
            List<Integer> appendList = Arrays.asList(0, 0);
            versionList.addAll(appendList);
        } else if (length == 2) {
            versionList.add(0);
        }
        return versionList;
    }
}
