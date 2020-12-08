package com.zy.client;

import androidx.annotation.NonNull;

import java.io.File;

public interface FileAction {

    /**
     * @param file 待处理的文件
     */
    void handleFile(@NonNull File file);
}