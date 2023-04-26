package com.github.hcsp.maven;

import io.github.gravitation1.SemanticVersion;

import java.util.ArrayList;
import java.util.Arrays;

public class Version {

    private static final String SEPARATOR_REGEX = "\\.";

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

        SemanticVersion semanticVersionA = SemanticVersion.from(uniformlength(version1));
        SemanticVersion semanticVersionB = SemanticVersion.from(uniformlength(version2));
        return Integer.compare(semanticVersionA.compareTo(semanticVersionB), 0);
    }

    /**
     * 将传入的不标准长度的 version 号变成标准的版本格式，以防止 SemanticVersion$InvalidBaseFormatException 报错
     * @param versionnum 传入的版本号
     * @return 返回一个 x.y.z 格式的数字
     */
    public static String uniformlength(String versionnum) {
        ArrayList<String> tempver1 = new ArrayList<>(Arrays.asList(versionnum.split(SEPARATOR_REGEX)));
        int len = tempver1.size();

        for (; len < 3; len++) {
            tempver1.add("0");
        }

        String res = tempver1.get(0);
        for (int i = 0; i < 2; i++) {
            res += ".";
            res += tempver1.get(i + 1);
        }
        return res;
    }
}
