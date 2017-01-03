package com.jpyl.music.api.controller;

import com.jpyl.music.api.service.def.SysService;
import com.jpyl.music.api.util.UploadSomething;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@Controller
@RequestMapping("sys")
public class SysController {
    @Resource
    private SysService sysService;

    //管理员登录
    @ResponseBody
    @RequestMapping(value = "/login/{sysname}/{syspass}",method = RequestMethod.POST)
    public Object sysLogin(@PathVariable(value = "sysname")String sysname,
                           @PathVariable(value = "syspass")String syspass,
                           HttpServletRequest request){
        Map map = new HashMap();
        try{
            //管理员登录成功过后,将管理员的个人资料返回给前台,同时包括管理员id,其他接口需要管理员id的由前台统一传值,这里就不做session和token的判断了
            Object result = sysService.sysLogin(sysname, syspass);
            map.put("code",0);
            map.put("msg","登录成功");
            map.put("result",result);

        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","登录失败,服务器异常");
        }
        return map;
    }

    //管理员上传歌曲
    @ResponseBody
    @RequestMapping(value = "/upload/music",method = RequestMethod.POST)
    public Object sysUploadMusic(@RequestParam(value = "m_name",required = true)String m_name,//音乐名
                                 @RequestParam(value = "m_singerid",required = true)String m_singerid,//歌手id
                                 @RequestParam(value = "m_language",required = true)String m_language,//歌曲语言
                                 @RequestParam(value = "m_albumid",required = false)String m_albumid,//专辑id
                                 @RequestParam(value = "m_typeid",required = true)String m_typeid,//歌曲分类id
                                 @RequestParam(value = "m_puqingurl",required = true)MultipartFile m_puqingurl,//普清格式
                                 @RequestParam(value = "m_gaoqingurl",required = false)MultipartFile m_gaoqingurl,//高清格式
                                 @RequestParam(value = "m_wusunurl",required = false)MultipartFile m_wusunurl,//无损格式
                                 @RequestParam(value = "m_yuanchuang",required = true)String m_yuanchuang,//是否原创
                                 @RequestParam(value = "m_lingsheng",required = false)MultipartFile m_lingsheng,//铃声地址
                                 @RequestParam(value = "m_is_k",required = false)MultipartFile m_is_k,//k歌地址
                                 @RequestParam(value = "m_time_size",required = true)String m_time_size,//音乐时长
                                 @RequestParam(value = "l_url",required = false)MultipartFile l_url,//歌词地址
                                 @RequestParam(value = "v_url",required = false)MultipartFile v_url,//mv地址
                                 @RequestParam(value = "v_sum",required = false)String v_sum,//mv简介
                                 @RequestParam(value = "v_typeid",required = false)String v_typeid,//mv分类id
                                 @RequestParam(value = "v_cover",required = false)MultipartFile v_cover,//mv封面
                                 @RequestParam(value = "v_title",required = false)String v_title,//mv名
                                 @RequestParam(value = "v_time_size",required = false)String v_time_size,//mv时长
                                 @RequestParam(value = "v_singer",required = false)String v_singer,
                                 HttpServletRequest request)//mv的歌手
    {
        Map map = new HashMap();
        Map parameters = new HashMap();
        String gaoqing = "";
        String wusun = "";
        String lingsheng = "";
        String mk = "";
        String lurl = "";
        String vurl = "";
        String vcover = "";
        String puqing = "";
        Integer mid = 0;
        Integer m_lrcid = 0;
        Integer m_mvid = 0;
        //判断参数
        if (m_albumid.equals("") || m_albumid == null || m_albumid.equals("(null)")) {
            m_albumid = "0";
        }
        if (v_typeid.equals("") || v_typeid == null || v_typeid.equals("(null)")) {
            v_typeid = "0";
        }
        if (v_title.equals("") || v_title == null || v_title.equals("(null)")) {
            v_title = "未知";
        }
        if (v_time_size.equals("") || v_time_size == null || v_time_size.equals("(null)")) {
            v_time_size = "0";
        }
        if (v_singer.equals("") || v_singer == null || v_singer.equals("(null)")) {
            v_singer = "未知";
        }
        if (v_sum.equals("") || v_sum == null || v_sum.equals("(null)")) {
            v_sum = "该mv还没有简介!";
        }
        try {
            //最先上传的是音乐的普清格式
            String dirP = "/music/";
            String pathP = request.getServletContext().getRealPath("/music");
            puqing = UploadSomething.uploadImg(pathP, m_puqingurl, dirP).toString();


            //将参数全部放在map里面吗
            parameters.put("mid",mid);
            parameters.put("m_lrcid",m_lrcid);
            parameters.put("m_mvid",m_mvid);
            parameters.put("m_name", m_name);
            parameters.put("m_singerid", m_singerid);
            parameters.put("m_name", m_name);
            parameters.put("m_language", m_language);
            parameters.put("m_albumid", m_albumid);
            parameters.put("m_typeid", m_typeid);
            parameters.put("m_puqingurl", puqing);
            parameters.put("m_yuanchuang", m_yuanchuang);
            parameters.put("m_time_size", m_time_size);
            parameters.put("v_sum", v_sum);
            parameters.put("v_typeid", v_typeid);
            parameters.put("v_title", v_title);
            parameters.put("v_time_size", v_time_size);
            parameters.put("v_singer", v_singer);

            //下面是一系列的判断,判断管理员有没有传相关的文件

            if (m_gaoqingurl == null) {//没上传高清音乐格式
                //m_puqingurl=""
            } else {
                String dir = "/music/";
                String path = request.getServletContext().getRealPath("/music");
                gaoqing = UploadSomething.uploadMusic(path, m_gaoqingurl, dir).toString();
                if (!gaoqing.equals("文件太大了") && !gaoqing.equals("文件格式不对")) {
                    //上传高清音乐格式成功
                    parameters.put("m_gaoqingurl", gaoqing);
                } else {
                    //上传高清格式失败
                }
            }
            if (m_wusunurl == null) {//没上传无损音乐格式
                //m_puqingurl=""
            } else {
                String dir = "/music/";
                String path = request.getServletContext().getRealPath("/music");
                wusun = UploadSomething.uploadMusic(path, m_gaoqingurl, dir).toString();
                if (!gaoqing.equals("文件太大了") && !gaoqing.equals("文件格式不对")) {
                    //上传无损音乐格式成功
                    parameters.put("m_wusunurl", wusun);
                } else {
                    //上传无损格式失败
                }
            }
            if (m_lingsheng == null) {//没上传铃声
                //m_puqingurl=""
            } else {
                String dir = "/lingsheng/";
                String path = request.getServletContext().getRealPath("/lingsheng");
                lingsheng = UploadSomething.uploadMusic(path, m_lingsheng, dir).toString();
                if (!gaoqing.equals("文件太大了") && !gaoqing.equals("文件格式不对")) {
                    //上传铃声成功
                    parameters.put("m_lingsheng", lingsheng);
                } else {
                    //上传铃声失败
                }
            }
            if (vcover == null) {//没上传mv封面
                //m_puqingurl=""
            } else {
                String dir = "/mvcover/";
                String path = request.getServletContext().getRealPath("/mvcover");
                vcover = UploadSomething.uploadImg(path, v_cover, dir).toString();
                if (!gaoqing.equals("文件太大了") && !gaoqing.equals("文件格式不对")) {
                    //上传mv封面成功
                    parameters.put("v_cover", vcover);
                } else {
                    //上传mv封面失败
                }
            }
            if (vurl == null) {//没上传mv
                //m_puqingurl=""
            } else {
                String dir = "/mv/";
                String path = request.getServletContext().getRealPath("/mv");
                vurl = UploadSomething.uploadVideo(path, v_url, dir).toString();
                if (!vurl.equals("文件太大了") && !vurl.equals("文件格式不对")) {
                    //上传mv成功
                    parameters.put("v_url", vurl);
                } else {
                    //上传mv失败
                }
            }
            if (lurl == null) {//没上传歌词
                //m_puqingurl=""
            } else {
                String dir = "/lrc/";
                String path = request.getServletContext().getRealPath("/lrc");
                lurl = UploadSomething.uploadLrc(path, l_url, dir).toString();
                if (!lurl.equals("文件太大了") && !lurl.equals("文件格式不对")) {
                    //上传铃声成功
                    parameters.put("l_url", lurl);
                } else {
                    //上传铃声失败
                }
            }
            if (mk == null) {//没上k歌的伴奏
                //m_puqingurl=""
            } else {
                String dir = "/banzhou/";
                String path = request.getServletContext().getRealPath("/banzhou");
                mk = UploadSomething.uploadMusic(path, m_is_k, dir).toString();
                if (!mk.equals("文件太大了") && !mk.equals("文件格式不对")) {
                    //上传伴奏成功
                    parameters.put("m_is_k", mk);
                } else {
                    //上传伴奏失败
                }
            }
            //参数准备完毕
            String result = sysService.sysUploaMusic(parameters).toString();
            if(result.equals("success")){
                map.put("code",0);
                map.put("msg","上传成功");
            }else {
                map.put("code",2);
                map.put("msg","未知原因上传失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "上传失败,服务器异常");
        }
        return map;
    }

    //管理员
}
















