package com.github.hcsp.maven;

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
        int result = 0;
        int[] version1Arr = fillVersionStr(version1);
        int[] version2Arr = fillVersionStr(version2);
        for (int i = 0; i < version1Arr.length; i++) {
            if (version2Arr[i] != version1Arr[i]) {
                result = version1Arr[i] > version2Arr[i] ? 1 : -1;
                break;
            }
        }
        return result;
    }

    private static int[] fillVersionStr(String versionStr) {
        String[] arr = versionStr.split("\\.");
        int majorVersionNo = Integer.parseInt(arr[0]);
        int minorVersionNo = 0;
        int revisionNo = 0;
        if (arr.length == 2) {
            minorVersionNo = Integer.parseInt(arr[1]);
        } else if (arr.length == 3) {
            minorVersionNo = Integer.parseInt(arr[1]);
            revisionNo = Integer.parseInt(arr[2]);
        }
        return new int[]{majorVersionNo, minorVersionNo, revisionNo};
    }
}
