package com.liantuo.baselib.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    /**
     * sd卡的根目录
     */
    private final static String ROOT_PATH = Environment
            .getExternalStorageDirectory().getPath();

    /**
     * 应用程序缓存目录
     */
    private static String getCacheDir(Context context) {
        return context.getCacheDir().getPath();
    }

    /**
     * 保存File的目录
     */
    public final static String FOLDER_PATH = "/myselfCache";


    /**
     * 获取储存File的目录
     *
     * @return
     */
    public static String getStorageDirectory(Context context) {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) ? ROOT_PATH + FOLDER_PATH
                : getCacheDir(context) + FOLDER_PATH;
    }

    /**
     * 保存Bitmap的方法，有sd卡存储到sd卡，没有就存储到手机目录
     *
     * @param fileName
     * @param bitmap
     * @throws IOException
     */
    public static void saveBitmap(Context context, String fileName, Bitmap bitmap) throws IOException {
        if (bitmap == null) {
            return;
        }
        String path = getStorageDirectory(context);
        File folderFile = new File(path);
        if (!folderFile.exists()) {
            folderFile.mkdir();
        }
        File file = new File(path + File.separator + fileName);
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        bitmap.compress(CompressFormat.JPEG, 100, fos);
        fos.flush();
        fos.close();
    }

    /**
     * 从手机或者sd卡获取Bitmap
     *
     * @param fileName
     * @return
     */
    public Bitmap getBitmap(Context context, String fileName) {
        return BitmapFactory.decodeFile(getStorageDirectory(context) + File.separator
                + fileName);
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public boolean isFileExists(Context context, String fileName) {
        return new File(getStorageDirectory(context) + File.separator + fileName)
                .exists();
    }

    /**
     * 根据路径获取File的名称
     *
     * @param filePath
     * @return
     */
    public String getFileName(String filePath) {
        int start = filePath.lastIndexOf("/");
        int end = filePath.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return filePath.substring(start + 1, end);
        } else {
            return null;
        }
    }

    /**
     * 获取文件的大小
     *
     * @param fileName
     * @return
     */
    public long getFileSize(Context context, String fileName) {
        return new File(getStorageDirectory(context) + File.separator + fileName)
                .length();
    }

    /**
     * 删除SD卡或者手机的缓存文件
     */
    public void deleteFile(Context context) {
        File dirFile = new File(getStorageDirectory(context));
        if (!dirFile.exists()) {
            return;
        }
        if (dirFile.isDirectory()) {
            String[] children = dirFile.list();
            for (int i = 0; i < children.length; i++) {
                new File(dirFile, children[i]).delete();
            }
        }
        dirFile.delete();
    }

    /**
     * 删除文件和文件夹
     *
     * @param file 要删除的根目录
     */
    public static void deleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            for (File f : childFile) {
                deleteFile(f);
            }
        }
    }

    public File getFile(Context context, String fileName) {
        File dirFile = new File(getStorageDirectory(context));
        File file = null;
        if (!dirFile.exists()) {
            return null;
        }
        if (dirFile.isDirectory()) {
            String[] children = dirFile.list();
            for (int i = 0; i < children.length; i++) {
                file = new File(dirFile, children[i]);
                if (file.getName().startsWith(fileName)) {
                    break;
                }
            }
        }
        return file;
    }
}
