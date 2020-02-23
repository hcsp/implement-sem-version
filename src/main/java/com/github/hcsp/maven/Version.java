package com.github.hcsp.maven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Version {
    public static void main(String[] args) {
        compare("1.2.1", "1.2.1");

    }
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
        /**
         * 处理字符串
         * x -> x.0.0; x.y -> x.y.0
         * 把字符串split成数组，再依次比较
         */
        ArrayList<Integer> v1 = convertStrtoInt(version1);
        ArrayList<Integer> v2 = convertStrtoInt(version2);
        if (v1.get(0) > v2.get(0)) {
            return 1;
        } else if (v1.get(0) < v2.get(0)){
            return -1;
        } else {
            if (v1.get(1) > v2.get(1)) {
                return 1;
            } else if (v1.get(1) < v2.get(1)) {
                return -1;
            } else {
                return v1.get(2).compareTo(v2.get(2));
            }
        }


    }

    private static ArrayList<Integer> convertStrtoInt(String str){
        ArrayList<Integer> new_list = new ArrayList<Integer>() {{
            add(0);
            add(0);
            add(0);
        } };
        if (str.length() == 1) {
            new_list.set(0, Integer.parseInt(str));
        } else  {
            List<String> versions = new ArrayList<>(Arrays.asList(str.split("\\.")));
            if (versions.size() == 2){
                new_list.set(0, Integer.parseInt(versions.get(0)));
                new_list.set(1, Integer.parseInt(versions.get(1)));
            }
            if (versions.size() == 3){
                new_list.set(0, Integer.parseInt(versions.get(0)));
                new_list.set(1, Integer.parseInt(versions.get(1)));
                new_list.set(2, Integer.parseInt(versions.get(2)));
            }

        }
        return new_list;
    }
}
