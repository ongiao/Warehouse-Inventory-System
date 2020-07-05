package com.ongiao.wis.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class UploadHelper {
    public static File upload(byte[] buffer, String savePath, String fileName) {
        File uploadFile = null;

        try {
            uploadFile = new File(savePath + fileName);
            String prefix = fileName.substring(0, fileName.indexOf("."));
            String suffix = fileName.substring(fileName.indexOf(".") + 1);
            uploadFile.createTempFile(prefix, suffix);

            OutputStream outputStream = new FileOutputStream(uploadFile);
            outputStream.write(buffer);

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uploadFile;
    }
}
