package com.jpyl.music.api.controller;

import com.jpyl.music.api.service.def.UserService;
import com.jpyl.music.api.util.UploadSomething;
import org.aspectj.apache.bcel.verifier.VerifierAppFrame;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    SqlSessionTemplate sqlSessions;

    //坚决使用restful api   不要再动摇了啊啊啊啊啊啊！！！！！！！


    //这个是测试框架的接口
    @ResponseBody
    @RequestMapping(value = "/api/user/{userid}",method = RequestMethod.GET)
    public Object getUserInfo(HttpServletRequest request, @PathVariable(value = "userid")String userid){
        Map map = new HashMap();
        try {
            Object result = userService.getUserInfo(userid);
            map.put("result",result);
            map.put("code",0);
            map.put("msg","获取成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败");
        }
        return map;
    }

    //用户登录
    @ResponseBody
    @RequestMapping(value = "/login/username/{username}/userpass/{userpass}",method = RequestMethod.POST)
    public Object userLogin(@PathVariable(value = "username")String username,
                            @PathVariable(value = "userpass")String userpass,
                            HttpServletRequest request){
        Map map = new HashMap();
        try{
            Map data = (HashMap)userService.userLogin(username,userpass);
            if((Integer)data.get("code")==1){
                map.put("code",2);
                map.put("msg","登录失败，数据出错");
            }
            else {
                map.put("data",data);
                map.put("code",0);
                map.put("msg","登录成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","登录失败，服务器异常");
        }
        return map;
    }

    //用户修改个人资料
    @ResponseBody
    @RequestMapping(value = "/me/{userid}/update",method = RequestMethod.PUT)
    public Object changeUserInfo(@RequestParam(value = "token")String token,
                                 @RequestParam(value = "gender")String gender,
                                 @RequestParam(value = "city")String city,
                                 @RequestParam(value = "age")String age,
                                 @RequestParam(value = "realname")String realname,
                                 @RequestParam(value = "sum")String sum,
                                 @RequestParam(value = "img",required = false)MultipartFile img,
                                 @RequestParam(value = "userpass")String userpass,
                                 @RequestParam(value = "phonenum")String phonenum,
                                 @RequestParam(value = "nickname")String nickname,
                                 @PathVariable(value = "userid")String userid,
                                 HttpServletRequest request){
        Map map = new HashMap();
        String name="";
        Map parMap = new HashMap();
        try{
            Integer flag = (Integer) userService.checkToken(userid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }
            else {
                //token验证成功
                if(img==null){
                    //用户没上传头像的情况
                    name="用户未上传头像";
                }else{
                    String dir = "/userimg/";
                    String path = request.getServletContext().getRealPath("/userimg");
                    name = UploadSomething.uploadImg(path,img,dir).toString();
                }
                if(!name.equals("文件太大了")&&!name.equals("文件格式不对")){
                    parMap.put("img",name);
                    parMap.put("gender",gender);
                    parMap.put("city",city);
                    parMap.put("age",age);
                    parMap.put("realname",realname);
                    parMap.put("sum",sum);
                    parMap.put("userpass",userpass);
                    parMap.put("phonenum",phonenum);
                    parMap.put("nickname",nickname);
                    parMap.put("userid",userid);
                    userService.changeUserInfo(parMap);
                    map.put("code",0);
                    map.put("msg","修改成功");
                }
                else {
                    map.put("code",3);
                    map.put("msg","文件上传失败");
                }
            }
        }catch (Exception e){
            map.put("code",1);
            map.put("msg","服务器异常");
        }
        return map;
    }

    //注册，这里的注册逻辑是这样的：
    //用户输入手机号验证短信验证码，验证通过后将手机号作为用户名，然后用户需要填写自己的昵称和密码方可进入app
    @ResponseBody
    @RequestMapping(value = "/register/username/{username}/nickname/{nickname}/userpass/{userpass}",method = RequestMethod.POST)
    public Object userRegister(@PathVariable(value = "username")String username,
                               @PathVariable(value = "nickname")String nickname,
                               @PathVariable(value = "userpass")String userpass){//这里的密码得是MD5加密的
        Map map = new HashMap();
        Map parMap = new HashMap();
        try{
            parMap.put("username",username);
            parMap.put("nickname",nickname);
            parMap.put("userpass",userpass);
            userService.userRegister(parMap);
            map.put("code",0);
            map.put("msg","注册成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","注册失败，服务器异常");
        }
        return map;
    }

    //用户注册的时候判断用户名是否存在
    @ResponseBody
    @RequestMapping(value = "/userIsRegister/{username}",method = RequestMethod.GET)
    public Integer userIsRegister(@PathVariable(value = "username")String username){
        Integer flag;
        try{
            flag = (Integer)userService.userIsRegister(username);
            //用户名不存在
            if(flag<=0||flag==null){
                flag=0;
            }else {
                //用户名已存在
                flag=1;
            }
        }catch (Exception e){
            e.printStackTrace();
            flag=2;//服务器出错的情况
        }
        return flag;
    }

    //用户注册的时候判断昵称是不是重复
    @ResponseBody
    @RequestMapping(value = "/nickIsExist/{nickname}",method = RequestMethod.GET)
    public Integer nickIsExist(@PathVariable(value = "nickname")String nickname){
        Integer flag;
        try{
            flag = (Integer)userService.nickIsExist(nickname);
            //昵称不存在
            if(flag<=0||flag==null){
                flag=0;
            }else {
                //昵称已存在
                flag=1;
            }
        }catch (Exception e){
            e.printStackTrace();
            flag=2;//服务器出错的情况
        }
        return flag;
    }

    //用户点击K歌
    @ResponseBody
    @RequestMapping(value = "/k/{mid}",method = RequestMethod.GET)
    public Object K(@PathVariable(value = "mid")String mid){
        Map map = new HashMap();
        try{
            userService.K(mid);
            map.put("code",0);
            map.put("msg","操作成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","服务器异常");
        }
        return map;
    }

    //用户上传自己的K歌
    @ResponseBody
    @RequestMapping(value = "/kmusic/me/{uid}/upload",method = RequestMethod.POST)
    public Object userKMusic(@PathVariable(value = "uid")String uid,
                             @RequestParam(value = "music")MultipartFile music,
                             @RequestParam(value = "token")String token,
                             @RequestParam(value = "mid")String mid,
                             @RequestParam(value = "singerid")String singerid,
                             HttpServletRequest request){
        Map map = new HashMap();
        try {
            Integer flag = (Integer) userService.checkToken(uid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }else {
                //token验证成功
                String dir = "/kmusic/";
                String path = request.getServletContext().getRealPath("/kmusic");
                String name = UploadSomething.uploadMusic(path,music,dir).toString();//这里需要改成上传歌的方法
                Map parMap = new HashMap();
                if(!name.equals("文件太大了")&&!name.equals("文件格式不对")){
                    parMap.put("uid",uid);
                    parMap.put("mid",mid);
                    parMap.put("url",name);
                    parMap.put("singerid",singerid);
                    userService.userKMusic(parMap);
                    map.put("code",0);
                    map.put("msg","上传成功");
                }else {
                    map.put("code",3);
                    map.put("msg","上传失败，请检查文件");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","上传失败，服务器异常");
        }
        return map;
    }

    //用户评论歌曲，这里只能是一级评论，并且不提供上传图片评论
    @ResponseBody
    @RequestMapping(value = "/music/common/{uid}/{mid}",method = RequestMethod.POST)
    public Object userPingLunMusic(@PathVariable(value = "uid")String uid,
                                   @PathVariable(value = "mid")String mid,
                                   @RequestParam(value = "content")String content,
                                   @RequestParam(value = "token")String token){
        Map map = new HashMap();
        Map parMap = new HashMap();
        try {
            Integer flag = (Integer) userService.checkToken(uid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }else {
                //token验证成功
                parMap.put("uid",uid);
                parMap.put("mid",mid);
                parMap.put("content",content);
                userService.userPingLunMusic(parMap);
                map.put("code",0);
                map.put("msg","评论成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","评论失败，服务器异常");
        }
        return map;
    }

    //用户收藏或是喜欢音乐
    @ResponseBody
    @RequestMapping(value="/save/me/{uid}",method = RequestMethod.GET)
    public Object userSaveMusic(@RequestParam(value = "mid",required = true)String mid,
                                @PathVariable(value="uid")String uid,
                                @RequestParam(value = "token")String token,
                                @RequestParam(value = "sid",required = true)String sid,
                                @RequestParam(value = "m_typeid",required = true)String m_typeid,
                                @RequestParam(value = "s_typeid",required = true)String s_typeid){
        Map map = new HashMap();
        Map parMap = new HashMap();
        try{
            Integer flag = (Integer) userService.checkToken(uid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }else {
                //token验证成功
                parMap.put("uid",uid);
                parMap.put("mid",mid);
                parMap.put("m_typeid",m_typeid);
                parMap.put("s_typeid",s_typeid);
                parMap.put("sid",sid);
                userService.userSaveMusic(parMap);
                map.put("code",0);
                map.put("msg","操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","操作失败");
        }
        return map;
    }

    //用户创建歌单
    @ResponseBody
    @RequestMapping(value="/create/songslist/{uid}",method = RequestMethod.GET)
    public Object userSaveMusic(@RequestParam(value = "s_cover",required = true)String s_cover,
                                @PathVariable(value="uid")String uid,
                                @RequestParam(value = "token")String token,
                                @RequestParam(value = "s_title",required = true)String s_title,
                                @RequestParam(value = "s_describe",required = true)String s_describe){
        Map map = new HashMap();
        Map parMap = new HashMap();
        try{
            Integer flag = (Integer) userService.checkToken(uid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }else {
                //token验证成功
                parMap.put("uid",uid);
                parMap.put("mid",s_title);
                parMap.put("m_typeid",s_describe);
                parMap.put("s_typeid",s_cover);
                userService.userCreatSongsList(parMap);
                map.put("code",0);
                map.put("msg","操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","操作失败");
        }
        return map;
    }

    //用户编辑歌单之前获取相关信息
    @ResponseBody
    @RequestMapping(value="/edit/songslist/getinfo/{uid}",method = RequestMethod.GET)
    public Object getDataBeforeUserEdit(@PathVariable(value="uid")String uid,
                                @RequestParam(value = "token")String token,
                                @RequestParam(value = "id")String id){
        Map map = new HashMap();
        try {
            Integer flag = (Integer) userService.checkToken(uid, token);
            if (flag == null || flag <= 0) {
                //token验证失败
                map.put("code", 2);
                map.put("msg", "账号登录异常或在别处登录或身份验证失败，请重新登录！");
            } else {
                //token验证成功
                map.put("result",userService.getDataBeforeUserEdit(id));
                map.put("code",0);
                map.put("msg","操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","操作失败");
        }
        return map;
    }



    //用户编辑歌单
    @ResponseBody
    @RequestMapping(value="/edit/songslist/{uid}",method = RequestMethod.GET)
    public Object userSaveMusic(@RequestParam(value = "s_cover",required = true)MultipartFile s_cover,
                                @PathVariable(value="uid")String uid,
                                @RequestParam(value = "token")String token,
                                @RequestParam(value = "s_title",required = true)String s_title,
                                @RequestParam(value = "s_describe",required = true)String s_describe,
                                @RequestParam(value = "s_typeid",required = true)String s_typeid,
                                HttpServletRequest request){
        Map map = new HashMap();
        Map parMap = new HashMap();
        String name = "";
        try{
            Integer flag = (Integer) userService.checkToken(uid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }else {
                //token验证成功
                if (s_cover == null) {
                    //用户没上传头像的情况
                    name = "用户未上传头像";
                } else {
                    String dir = "/songlistimg/";
                    String path = request.getServletContext().getRealPath("/songlistimg");
                    name = UploadSomething.uploadImg(path, s_cover, dir).toString();
                }
                if (!name.equals("文件太大了") && !name.equals("文件格式不对")) {
                    parMap.put("s_title", s_title);
                    parMap.put("s_describe", s_describe);
                    parMap.put("s_cover", s_cover);
                    parMap.put("s_typeid", s_typeid);
                    userService.userEditSongsList(parMap);
                    map.put("code", 0);
                    map.put("msg", "操作成功");
                } else {
                    map.put("code",3);
                    map.put("msg","文件上传失败");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","操作失败");
        }
        return map;
    }

    //用户向歌单里面添加歌曲
    @ResponseBody
    @RequestMapping(value="/add/songslist/{uid}",method = RequestMethod.GET)
    public Object userSaveMusic(@RequestParam(value = "musicids",required = true)String musicids,
                                @PathVariable(value="uid")String uid,
                                @RequestParam(value = "token")String token){
        Map map = new HashMap();
        try{
            Integer flag = (Integer) userService.checkToken(uid,token);
            if(flag==null||flag<=0){
                //token验证失败
                map.put("code",2);
                map.put("msg","账号登录异常或在别处登录或身份验证失败，请重新登录！");
            }else {
                //token验证成功
                userService.userAddSongToSongsList(musicids,uid);
                map.put("code",0);
                map.put("msg","操作成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","操作失败");
        }
        return map;
    }

    //

}
