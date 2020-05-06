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
        VersionObj firstVersionObj = new VersionObj(version1);
        VersionObj secVersionObj = new VersionObj(version2);
        return firstVersionObj.compareTo(secVersionObj);
    }

    static class VersionObj implements Comparable<VersionObj> {
        private int headerVer;
        private int midVer;
        private int lastVer;

        public int getHeaderVer() {
            return headerVer;
        }

        public void setHeaderVer(int headerVer) {
            this.headerVer = headerVer;
        }

        public int getMidVer() {
            return midVer;
        }

        public void setMidVer(int midVer) {
            this.midVer = midVer;
        }

        public int getLastVer() {
            return lastVer;
        }

        public void setLastVer(int lastVer) {
            this.lastVer = lastVer;
        }

        int getVersion(String[] list, int index) {
            int size = list.length;
            if (index + 1 > size) {
                return 0;
            }
            return Integer.parseInt(list[index], 10);
        }

        VersionObj(String version) {
            String[] list = version.split("\\.");
            this.setHeaderVer(getVersion(list, 0));
            this.setMidVer(getVersion(list, 1));
            this.setLastVer(getVersion(list, 2));
        }

        @Override
        public int compareTo(VersionObj o) {
            if (this.getHeaderVer() == o.getHeaderVer()) {
                if (this.getMidVer() == o.getMidVer()) {
                    if (this.getLastVer() == o.getLastVer()) {
                      return 0;
                    } else {
                        return this.getLastVer() > o.getLastVer() ? 1 : -1;
                    }
                } else {
                    return this.getMidVer() > o.getMidVer() ? 1 : -1;
                }
            } else {
                return this.getHeaderVer() > o.getHeaderVer() ? 1 : -1;
            }
        }
    }
}
