package com.jpyl.music.api.controller;

import com.jpyl.music.api.service.def.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
@Controller
@RequestMapping("music")
public class MusicController {

    @Resource
    private MusicService musicService;

    //获取音乐排行榜
    @ResponseBody
    @RequestMapping(value = "/top/{type}/{page}",method = RequestMethod.GET)
    public Object getTop(@PathVariable(value = "type")Integer type,
                         @PathVariable(value = "page")Integer page){
        Map map = new HashMap();
        List result;
        if(page==null||page==0){
            page=1;
        }
        try {
            switch (type){
                case 0:result = (ArrayList)musicService.getTop500(page);//top500
                case 1:result = (ArrayList)musicService.getMinGe(page);//民歌榜
                case 2:result = (ArrayList)musicService.getPopular(page);//流行榜
                case 3:result = (ArrayList)musicService.getNewSongs(page);//新歌榜
                case 4:result = (ArrayList)musicService.getYuanChuang(page);//原创榜
                case 5:result = (ArrayList)musicService.getKTop(page);//k歌翻唱榜
                    default:result = (ArrayList)musicService.getTop500(page);//默认排行榜是top500
            }
            if(result.size()<=0){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }else {
                map.put("code",0);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败，服务器异常");
        }
        return map;
    }

    //音乐个性化推荐
    //按权重比较，这里的权重有下载和喜欢两种，下载优先（权重高于喜欢）
    //根据下载和喜欢的音乐的类型进行点击量从高到低的排序
    @ResponseBody
    @RequestMapping(value = "/gexing",method = RequestMethod.GET)
    public Object getGeXing(@RequestParam(value = "uid",required = false)String uid,
                            @RequestParam(value = "page",required = false)Integer page){
        Map map = new HashMap();
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)musicService.getGeXing(uid,page);
            if(result.size()<=0||result==null){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }
            else {
                map.put("code",1);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败,服务器异常");
        }
        return map;
    }

    //用户点击，分享要进行的一系列操作
    //type1表示用户的操作，0是点击，1是分享
    //type2表示用户对什么进行操作的，0表示音乐，1表示专辑，2表示歌手，3表示mv，4表示歌单
    //id表示标识音乐，mv等东西的唯一id
    @ResponseBody
    @RequestMapping(value = "/action",method = RequestMethod.GET)
    public Object userAction(@RequestParam(value = "type1",required = true)Integer type1,
                             @RequestParam(value = "type2",required = true)Integer type2,
                             @RequestParam(value = "id",required = true)Integer id){
        Map map = new HashMap();
        Map parMap = new HashMap();
        parMap.put("type1",type1);
        parMap.put("type2",type2);
        parMap.put("id",id);
        try{
            musicService.userAction(parMap);
            map.put("code",0);
            map.put("msg","更新成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","更新失败，服务器异常");
        }
        return map;
    }

    //推荐页面的热销专辑
    @ResponseBody
    @RequestMapping(value = "/hot/sale/list",method = RequestMethod.GET)
    public Object getHotSaleAlbum(@RequestParam(value ="page",required = false)Integer page){
        Map map = new HashMap();
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)musicService.getHotSaleList(page);
            if(result.size()<=0||result==null){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }
            else {
                map.put("code",0);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败，服务器异常");
        }
        return map;
    }

    //推荐页面的歌单推荐
    @ResponseBody
    @RequestMapping(value = "/recom/songlist",method = RequestMethod.GET)
    public Object getRecommSongsList(@RequestParam(value ="page",required = false)Integer page){
        Map map = new HashMap();
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)musicService.getRecommSongsList(page);
            if(result.size()<=0||result==null){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }
            else {
                map.put("code",0);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败，服务器异常");
        }
        return map;
    }

    //推荐页面的新碟上架
    @ResponseBody
    @RequestMapping(value = "/album/news/list",method = RequestMethod.GET)
    public Object getNewAlbumList(@RequestParam(value ="page",required = false)Integer page){
        Map map = new HashMap();
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)musicService.getNewAlbumList(page);
            if(result.size()<=0||result==null){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }
            else {
                map.put("code",0);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败，服务器异常");
        }
        return map;
    }

    //根据音乐id获取音乐详情
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public Object getMusicDetail(@RequestParam(value = "mids",required = true)String mids,
                                 @RequestParam(value = "uid",required = false)String uid){
        Map map = new HashMap();
        if(uid==null||uid.equals("")||uid.equals("(null)")||uid.equals("null")){
            uid=null;
        }
        try{
            List result = (ArrayList)musicService.getMusicDetail(mids,uid);
            map.put("result",result);
            map.put("code",0);
            map.put("msg","获取成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败，服务器异常");
        }
        return map;
    }

    //查找相关
    @ResponseBody
    @RequestMapping(value = "/find/{name}",method = RequestMethod.GET)
    public Object finSomething(@RequestParam(value ="type",required = false)Integer type,
                               @PathVariable(value = "name")String name){
        Map map = new HashMap();
        if(type==null||type==0){type=1;}
        try{
            List result = (ArrayList)musicService.finSomething(name,type);
            if(result.size()<=0||result==null){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }
            else {
                map.put("code",0);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败，服务器异常");
        }
        return map;
    }

    //附近热唱
    @ResponseBody
    @RequestMapping(value = "/hot/k",method = RequestMethod.GET)
    public Object nearHotK(@RequestParam(value = "page",required = false)Integer page){
        Map map = new HashMap();
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)musicService.nearHotK(page);
            if(result.size()<=0||result==null){
                map.put("code",3);
                map.put("msg","获取成功，但是没数据了");
            }
            else {
                map.put("code",1);
                map.put("msg","获取成功");
                map.put("result",result);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","获取失败,服务器异常");
        }
        return map;
    }

    //


}
