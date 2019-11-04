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
    private static final int EQUAL = 0;
    private static final int SMALLER = -1;
    private static final int BIGGER = 1;
    private static final String SPLIT_CHAR = "\\.";

    public static int compare(String version1, String version2) {
        if (version1.equals(version2)) {
            return EQUAL;
        } else {
            List version1Splits = autoCompleteZero(version1.split(SPLIT_CHAR));
            List version2Splits = autoCompleteZero(version2.split(SPLIT_CHAR));
            for (int i = 0; i < 3; i++) {
                if (Integer.valueOf((String) version1Splits.get(i)) > Integer.valueOf((String) version2Splits.get(i))) {
                    return BIGGER;
                }

                if (Integer.valueOf((String) version1Splits.get(i)) < Integer.valueOf((String) version2Splits.get(i))) {
                    return SMALLER;
                }
            }
            return EQUAL;
        }
    }

    public static List autoCompleteZero(String[] strings) {
        ArrayList arrayList = new ArrayList();

        arrayList.addAll(Arrays.asList(strings));

        for (int i = strings.length; i < 3; i++) {
            arrayList.add("0");
        }
        return arrayList;
    }
}
