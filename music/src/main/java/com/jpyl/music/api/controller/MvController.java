package com.jpyl.music.api.controller;

import com.jpyl.music.api.service.def.MvService;
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
@RequestMapping("mv")
public class MvController {
    @Resource
    private MvService mvService;


    //获取热门mv列表
    @ResponseBody
    @RequestMapping(value = "/hot/list",method = RequestMethod.GET)
    public Object getHotMvList(@RequestParam(value = "page",required = false)Integer page){
        Map map = new HashMap();
        if(page==null||page==0){page=1;}
        try{
            List result = (ArrayList)mvService.getHotMvList(page);
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
