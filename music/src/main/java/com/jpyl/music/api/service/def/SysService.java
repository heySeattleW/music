package com.jpyl.music.api.service.def;

import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface SysService {


    //管理员登录
    public Object sysLogin(String sysname,String syspass)throws Exception;

    //管理员上传音乐
    public Object sysUploaMusic(Map parameters)throws Exception;
}
