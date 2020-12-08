package com.zy.client;

import java.io.File;

public class LicenseUtils {

    public static final String FILE_SUFFIX = "java";// kt

    public static void main(String[] args) {

        //String javaFilesDir = "C:\\fastwork\\Project\\android\\zy_client_android\\app\\src\\main\\java";
        String javaFilesDir = "C:\\fastwork\\Project\\android\\zy_client_android\\lib_player\\src\\main\\java";

        String licenseStr = "/**\n" +
                " * Copyright 2020 javakam\n" +
                " * <p>\n" +
                " * Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                " * you may not use this file except in compliance with the License.\n" +
                " * You may obtain a copy of the License at\n" +
                " *\n" +
                " *     http://www.apache.org/licenses/LICENSE-2.0\n" +
                " *\n" +
                " * Unless required by applicable law or agreed to in writing, software\n" +
                " * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                " * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                " * See the License for the specific language governing permissions and\n" +
                " * limitations under the License.\n" +
                " */";
        String licensePath = "/Users/jiangwenzhong/android/workspace/github/JLicenseForJavaFileUtil/app/src/main/java/com/jwz/jlicenseforjavafileutil/file/license";

        iterativeHandleFiles(javaFilesDir, FILE_SUFFIX, new AddLicenseWithStrFileAction(licenseStr));
//        iterativeHandleFiles(javaFilesDir, FILE_SUFFIX, new AddLicenseWithFileFileAction(licensePath));
    }

    private static void iterativeHandleFiles(String javaFilesDir, String fileSuffix, FileAction... action) {

        if (isEmpty(javaFilesDir) || action == null) {
            return;
        }

        try {
            File file = new File(javaFilesDir);
            if (file == null || !file.exists()) {
                return;
            }
            iterativeHandleFiles(file, fileSuffix, action);
        } catch (Exception e) {
            System.out.println("iterativeHandleFiles: " + e.getMessage());
        }

    }

    /**
     * 判断字符串是否为空
     */
    private static boolean isEmpty(String javaFilesDir) {

        return (javaFilesDir == null || javaFilesDir.equals(""));
    }

    private static void iterativeHandleFiles(File file, String fileSuffix, FileAction... action) {

        if (file == null || !file.exists() || action == null) {
            return;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) {
                return;
            }
            for (int i = 0; i < files.length; i++) {
                iterativeHandleFiles(files[i], fileSuffix, action);
            }
        } else {
            String name = file.getName();
            // 不是目标文件则直接返回，不处理
            if (isEmpty(name) || !name.endsWith(FILE_SUFFIX)) {
                return;
            }
            for (int i = 0; i < action.length; i++) {
                action[i].handleFile(file);
            }
        }
    }

}