package com.github.hcsp.maven;

import java.util.regex.*;

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
        String standardV1 = standardize(version1);  // convert to x.y.z format
        String standardV2 = standardize(version2);

        // handle invalid inputs
        if (standardV1.equals("") || standardV2.equals("")) {
            throw new IllegalArgumentException("Input should be 'x' or 'x.y' or 'x.y.z' with numerical characters.");
        }

        // same version
        if (standardV1.equals(standardV2)) {
            return 0;
        }

        // different versions, split them into a string array
        String[] v1 = standardV1.split("\\.", 3);
        String[] v2 = standardV2.split("\\.", 3);

        // compare the major version
        int result = subCompare(Integer.parseInt(v1[0]), Integer.parseInt(v2[0]));

        // compare the minor version
        if (result == 0) {
            result = subCompare(Integer.parseInt(v1[1]), Integer.parseInt(v2[1]));
        }

        // compare the patch version
        if (result == 0) {
            result = subCompare(Integer.parseInt(v1[2]), Integer.parseInt(v2[2]));
        }

        return result;
    }

    /**
     * version string standardization
     * convert "x/x.y/x.y.z" to "x.y.z"
     *
     * @param version version number string
     * @return a version string in "x.y.z" format
     * "" if the input is not in x/x.y/x.y.z format or string contains non-numeric characters
     */
    public static String standardize(String version) {
        String pattern1 = "^\\d+";                  // match x
        String pattern2 = "^\\d+\\.\\d+";           // match x.y
        String pattern3 = "^\\d+\\.\\d+\\.\\d+";    // match x.y.z

        if (Pattern.matches(pattern3, version)) {
            return version;
        } else if (Pattern.matches(pattern2, version)) {
            return version + ".0";
        } else if (Pattern.matches(pattern1, version)) {
            return version + ".0.0";
        } else {
            return "";
        }
    }

    /**
     * compare the part of two versions numbers
     *
     * @param sub1 sub version number from version 1
     * @param sub2 sub version number from version 2
     * @return -1/0/1 if sub version 1 is less/equal/greater than sub version 2
     */
    public static int subCompare(Integer sub1, Integer sub2) {
        if (sub1 > sub2) {
            return 1;
        } else if (sub1.equals(sub2)) {
            return 0;
        } else {
            return -1;
        }
    }
}
