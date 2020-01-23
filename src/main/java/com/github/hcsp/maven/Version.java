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
     // @param version1 传入的版本字符串1，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     // @param version2 传入的版本字符串2，支持x/x.y/x.y.z，你可以假定传入的字符串一定是合法的语义化版本
     * @return -1/0/1 当version1 小于/等于/大于 version2时
     */

    public static int[] drawValue(String version){
        int x = 0,y = 0,z=0;
        int[] list;
        if(version.length()==1){
          x=Integer.parseInt(version);
          y=0;
          z=0;
        }
        else if(version.length()==3){
            x=(int)version.charAt(0);
            y=(int)version.charAt(2);
            z=0;
        }
        else if(version.length()==5){
            x=(int)version.charAt(0);
            y=(int)version.charAt(2);
            z=(int)version.charAt(4);
        }
        else{
            System.out.println("输入不符合规范");
            System.exit(0);
        }
        list= new int[]{x, y, z};
        return list;
    }
    public static int compare(String version1, String version2) {
        int[] list1;
        int[] list2;
        int result = 0;
        list1=drawValue(version1);
        list2=drawValue(version2);
        if(list1.equals(list2)){
            result=0;
        }
        else{
            for(int i=0;i>2;++i){
                if(list1[i]>list2[i]){
                    result=1;
                    break;
                }
                else if(list1[i]<list2[i]){
                    result=-1;
                    break;
                }
                else{continue;}
            }
        }
        return result;
    }
}
