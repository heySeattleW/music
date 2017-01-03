package com.jpyl.music.api.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/7.
 */
public class UploadSomething {

    //上传单张图片
    public static String uploadImg(String path,MultipartFile img,String dir)throws Exception{
        File dirs = new File(path);
        String name = "";
        if(!dirs.exists()){
            dirs.mkdirs();
        }
            if (img.getSize() > 1024 * 1024) {
                name = "文件太大了";
            } else if (!img.getContentType().equals("image/jpeg") && !img.getContentType().equals("image/bmp")
                    && !img.getContentType().equals("image/png") && !img.getContentType().equals("image/gif")) {
                name = "文件格式不对";
            }
            else {
                String imgName = img.getOriginalFilename();
                String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
                String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
                File file = new File(path + "/" + imgMD5 + suffix);
                file.exists();
                try {
                    img.transferTo(file);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }

                name = dir + imgMD5 + suffix;
        }
        return name;//返回图片存放的相对路径
    }

    //上传多张图片,返回一个图片相对路径的数组
    public static String[] uploadImgs(String path,MultipartFile[] imgs,String dir)throws Exception {
        File dirs = new File(path);
        String name="";
        String[] names = new String[100];//最多只能100连传。。。
        if (!dirs.exists()) {
            dirs.mkdirs();

        }
        for (MultipartFile img : imgs) {
            int i=0;
            if (img.getSize() > 1024 * 1024) {
                name="文件太大了";
                names[i]=name;
                i++;
            } else if (!img.getContentType().equals("image/jpeg") && !img.getContentType().equals("image/bmp")
                    && !img.getContentType().equals("image/png") && !img.getContentType().equals("image/gif")) {
                name="文件格式不对";
                names[i]=name;
                i++;
            }else {
                String imgName = img.getOriginalFilename();
                String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
                String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
                File file = new File(path + "/" + imgMD5 + suffix);
                file.exists();
                try {
                    img.transferTo(file);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
                name = dir + imgMD5 + suffix;
                names[i] = name;
                i++;
            }
        }
        return names;//返回图片存放的相对路径
    }

    //上传视频(目前只支持上传MP4)
    public static String uploadVideo(String path,MultipartFile video,String dir)throws Exception{
        File dirs = new File(path);
        String name="";
        if(!dirs.exists()){
            dirs.mkdirs();
        }
        if (video.getSize() > 102400 * 102400) {
            name="文件太大了";
        } else if (!video.getContentType().equals("video/mp4")) {
            name="文件格式不对";
        }else {
            String imgName = video.getOriginalFilename();
            String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = video.getOriginalFilename().substring(video.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            file.exists();
            try {
                video.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            name = dir + imgMD5 + suffix;
        }
        return name;//返回视频存放的相对路径
    }


    //上传单首歌或者录音
    public static String uploadMusic(String path,MultipartFile img,String dir)throws Exception{
        File dirs = new File(path);
        String name = "";
        if(!dirs.exists()){
            dirs.mkdirs();
        }
        if (img.getSize() > 1024 * 1024) {
            name = "文件太大了";
        } else if (!img.getContentType().equals("image/jpeg") && !img.getContentType().equals("image/bmp")
                && !img.getContentType().equals("image/png") && !img.getContentType().equals("image/gif")) {
            name = "文件格式不对";
        }
        else {
            String imgName = img.getOriginalFilename();
            String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            file.exists();
            try {
                img.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            name = dir + imgMD5 + suffix;
        }
        return name;//返回图片存放的相对路径
    }

    //上传歌词文件
    public static String uploadLrc(String path,MultipartFile img,String dir)throws Exception{
        File dirs = new File(path);
        String name = "";
        if(!dirs.exists()){
            dirs.mkdirs();
        }
        if (img.getSize() > 1024 * 1024) {
            name = "文件太大了";
        } else if (!img.getContentType().equals("image/jpeg") && !img.getContentType().equals("image/bmp")
                && !img.getContentType().equals("image/png") && !img.getContentType().equals("image/gif")) {
            name = "文件格式不对";
        }
        else {
            String imgName = img.getOriginalFilename();
            String imgMD5 = Md5Util.MD5(imgName + UUIDUtil.uuid());
            String suffix = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
            File file = new File(path + "/" + imgMD5 + suffix);
            file.exists();
            try {
                img.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }

            name = dir + imgMD5 + suffix;
        }
        return name;//返回图片存放的相对路径
    }

}
