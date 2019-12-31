package com.github.hcsp.maven;

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
        int[] ver1Arr = verStr2IntArr(version1);
        int[] ver2Arr = verStr2IntArr(version2);

        for (int i = 0; ; i++) {
            int ver1 = ver1Arr[i];
            int ver2 = ver2Arr[i];
            if (ver1 == ver2 && i != 2) {
                continue;
            }
            return Integer.compare(ver1, ver2);
        }
    }


    private static final String SPILT_REGEX = "\\.";

    /**
     * <p>
     * 语义化版本字符串转 int 数组方法。
     * 以 <code>.</code> 分割字符串成数组，数组长度恒为 <strong>3</strong>。
     * 结果数组内的非<strong>正整数</strong>字符串将会转换为数字 <strong>0</strong>。
     * </p>
     * @param version 语义化版本字符串
     * @return 转换结果字符串
     */
    private static int[] verStr2IntArr(String version) {
        // 预定义结果数组
        int[] result = {0, 0, 0};
        // 字符串转数组
        if (!isEmpty(version)) { // 字符串判空
            String[] split = version.split(SPILT_REGEX);
            if (split.length <= 0) {
                return result;
            }
            int len = Math.min(result.length, split.length);
            for (int i = 0; i < len; i++) {
                result[i] = string2int(split[i]);
            }
        }
        return result;
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() < 1;
    }

    private static int string2int(String s) {
        if (isPositiveInt(s)) {
            return Integer.parseInt(s);
        }
        return 0;
    }


    private static boolean isPositiveInt(String s) {
        if (isEmpty(s)) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(s).matches();
    }


}
