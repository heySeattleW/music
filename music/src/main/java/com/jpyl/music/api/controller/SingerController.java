package com.jpyl.music.api.controller;

import com.jpyl.music.api.service.def.SingerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@Controller
@RequestMapping("singer")
public class SingerController {

    @Resource
    private SingerService singerService;

    //一级页面获取热门歌手列表
    @ResponseBody
    @RequestMapping(value = "/hot/list",method = RequestMethod.GET)
    public Object getFenLeiSingerList(@RequestParam(value = "type1",required = false)Integer type1,
                                      @RequestParam(value = "type2",required = false)Integer type2,
                                      @RequestParam(value = "page",required = false)Integer page){
        Map map = new HashMap();
        if(type1==null){type1=0;}
        if(type2==null){type2=0;}
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)singerService.getFenLeiSingerList(type1,type2,page);
            if(result.size()<=0||result==null){
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
}
