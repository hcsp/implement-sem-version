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
        return new Ver(version1).compareTo(new Ver(version2));
    }

    private static class Ver implements Comparable<Ver> {
        int x;
        int y;
        int z;

        private Ver(String str) {
            //可以假定传入的字符串一定是合法的语义化版本
            String[] strings = str.split("\\.");
            x = Integer.parseInt(strings[0]);
            if (strings.length == 2) {
                y = Integer.parseInt(strings[1]);
            }
            if (strings.length == 3) {
                y = Integer.parseInt(strings[1]);
                z = Integer.parseInt(strings[2]);
            }
        }

        @Override
        public int compareTo(Ver ver) {
            int rex = Integer.compare(this.x, ver.x);
            if (rex != 0) {
                return rex;
            }
            int rey = Integer.compare(this.y, ver.y);
            if (rey != 0) {
                return rey;
            }
            return Integer.compare(this.z, ver.z);
        }
    }


}
