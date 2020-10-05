package com.github.hcsp.maven;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        List<String> version1List = Arrays.asList(version1.split("\\."));
        List<String> version2List = Arrays.asList(version2.split("\\."));

        Queue<String> queue1 = getQueue(version1List);
        Queue<String> queue2 = getQueue(version2List);

        int size = Math.max(queue1.size(), queue2.size());

        for (; size > 0; size--) {
            int val1 = queue1.size() > 0 ? Integer.parseInt(queue1.poll()) : 0;
            int val2 = queue2.size() > 0 ? Integer.parseInt(queue2.poll()) : 0;

            if (val2 > val1) {
                return -1;
            } else if (val1 > val2) {
                return 1;
            }
        }

        return 0;
    }

    private static Queue<String> getQueue(List<String> versionList) {
        Queue<String> queue = new LinkedList<String>();

        for (String versionKey : versionList) {
            queue.offer(versionKey);
        }

        return queue;
    }

    public static void main(String[] args) {
        System.out.println(compare("1.1.0", "1.1"));
    }

}
