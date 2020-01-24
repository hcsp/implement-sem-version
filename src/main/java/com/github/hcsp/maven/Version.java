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
//    public static void main(String[] args){
//        System.out.println(compare("1.1.1","1.1.0"));
//    }
    public static int compare(String version1, String version2) {
        String[] lists1;
        String[] lists2;
        int result = 0;
        lists1 = version1.split("\\.");
        lists2 = version2.split("\\.");
        int length = Math.max(lists1.length, lists2.length);
        for (int i = 0; i <= length; ++i) {
            int list1 = i < lists1.length ? Integer.parseInt(lists1[i]) : 0;
            int list2 = i < lists2.length ? Integer.parseInt(lists2[i]) : 0;
            if (lists1.equals(lists2)) {
                result = 0;
                break;
            } else if (list1 > list2) {
                result = 1;
                break;
            } else if (list1 < list2) {
                result = -1;
                break;
            } else {
                continue;
            }
        }
        return result;
    }

}
