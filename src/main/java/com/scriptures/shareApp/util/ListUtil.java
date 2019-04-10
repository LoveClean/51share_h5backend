package com.scriptures.shareApp.util;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static boolean checkLabel(String memberLabel, String testLabel){
        List<String> mL=stringOfList(memberLabel);
        List<String> tL=stringOfList(testLabel);
        for(String ml:mL){
            if(tL.contains(ml)){
                return true;
            };
        }
        return false;
    }
    public static List<String> stringOfList(String str){
        List<String> list=new ArrayList();
        if(!StringUtil.isEmpty(str)){
            String[] temp=str.split(",");
            for(int i=0;i<temp.length;i++){
                list.add(temp[i]);
            }
        }
        return list;
    }

//    public static boolean checkLabel(String memberLabel, String testLabel){
//       //List<String> m=stringOfList(memberLabel);
//        String[] mL=memberLabel.split(",");
//        //List<String> tL=stringOfList(testLabel);
//        String[] tL=testLabel.split(",");
//        for(String ml:mL){
//            if(tL.contains(ml)){
//                return true;
//            };
//        }
//        return false;
//    }
//    public static List<String> stringOfList(String str){
//        List<String> list=new ArrayList();
//        if(!StringUtil.isEmpty(str)){
//            String[] temp=str.split(",");
//            for(int i=0;i<temp.length;i++){
//                list.add(temp[i]);
//            }
//        }
//        return list;
//    }
}
