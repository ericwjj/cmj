//package com.soft2146.crm.api.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
///**
// * @Description:
// * @Author: Tao
// * @Date: 2021-12-19 10:48
// **/
//public class DateTest {
//    public static void main(String[] args) {
//        File file = new File("D:\\HelloWorld.txt");
//        file.createNewFile();
//        File dir = new File("D:\\IOTest");
//        dir.mkdirs();
//        File newFile = new File(dir + "\\HelloWorld.txt");
//        newFile.createNewFile();
//        try (FileInputStream fis = new FileInputStream(file);
//             FileOutputStream fos = new FileOutputStream(newFile)) {
//            int len;
//            byte[] buffer = new byte[4096];
//            while ((len = fis.read(buffer)) > 0) {
//                fos.write(buffer, 0, len);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (File temp : dir.listFiles()) {
//            System.out.println(temp.getName());
//        }
//    }
//}
