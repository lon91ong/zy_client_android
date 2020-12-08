package com.zy.client;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author jiang wenzhong
 * @date 2019-07-23
 */
public class AddLicenseWithStrFileAction implements FileAction {

    /**
     * 2M
     */
    public static final int MAX_FILE_SIZE = 2 * 1024 * 1024;
    private String mLicenseStr;

    public AddLicenseWithStrFileAction(String licenseStr) {

        this.mLicenseStr = licenseStr;
    }

    @Override
    public void handleFile(File file) {

        RandomAccessFile targetRandomAccessFile = null;
        try {
            targetRandomAccessFile = new RandomAccessFile(file, "rw");

            if (targetRandomAccessFile.length() > MAX_FILE_SIZE) {
                System.out.println("file size is too long!" + file.getName());
                return;
            }

            // 读取license文本内容
            byte[] contentBytes = new byte[(int) targetRandomAccessFile.length()];
            targetRandomAccessFile.readFully(contentBytes);
            String contentStr = new String(contentBytes);

            int indexOfPackage = contentStr.indexOf("package");
            // 拼接最终的文件内容
            contentStr = mLicenseStr + "\n" + contentStr.substring(indexOfPackage);
            targetRandomAccessFile.seek(0);
            targetRandomAccessFile.setLength(contentStr.length());
            targetRandomAccessFile.write(contentStr.getBytes("UTF-8"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (targetRandomAccessFile != null) {
                    targetRandomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}