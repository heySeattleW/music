package com.jpyl.music.api.service.impl;

import com.jpyl.music.api.dao.def.UserDao;
import com.jpyl.music.api.service.def.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //获取用户信息
    public Object getUserInfo(String userid)throws Exception{
        return userDao.getUserInfo(userid);
    }

    //用户注册
    public Object userRegister(Map parameters)throws Exception{
        return userDao.userRegister(parameters);
    }

    //用户注册的时候判断用户名是否存在
    public Object userIsRegister(String username)throws Exception{
        return userDao.userIsRegister(username);
    }

    //用户注册的时候判断昵称是否存在
    public Object nickIsExist(String nickname)throws Exception{
        return userDao.nickIsExist(nickname);
    }

    //用户登录
    public Object userLogin(String username,String password)throws Exception{
        return userDao.userLogin(username, password);
    }

    //需要验证用户token的接口需要验证token
    public Object checkToken(String uid,String token)throws Exception{
        return userDao.checkToken(uid,token);
    }

    //用户修改个人信息
    public Object changeUserInfo(Map parameters)throws Exception{
        return userDao.changeUserInfo(parameters);
    }

    //用户点击K歌
    public Object K(String mid)throws Exception{
        return userDao.K(mid);
    }

    //用户上传自己的K歌
    public Object userKMusic(Map parameters)throws Exception{
        return userDao.userKMusic(parameters);
    }

    //用户评论歌曲，这里只能是一级评论
    public Object userPingLunMusic(Map parameters)throws Exception{
        return userDao.userPingLunMusic(parameters);
    }

    //用户收藏或是喜欢歌曲
    public Object userSaveMusic(Map parameters)throws Exception{
        return userDao.userSaveMusic(parameters);
    }

    //用户创建歌单
    public Object userCreatSongsList(Map parameters)throws Exception{
        return userDao.userCreatSongsList(parameters);
    }

    //用户编辑歌单之前获取相关数据
    public Object getDataBeforeUserEdit(String id)throws Exception{
        return userDao.getDataBeforeUserEdit(id);
    }

    //用户编辑歌单
    public Object userEditSongsList(Map parameters)throws Exception{
        return userDao.userEditSongsList(parameters);
    }

    //用户向歌单里面添加歌曲
    public Object userAddSongToSongsList(String musicids,String id)throws Exception{
        return userDao.userAddSongToSongsList(musicids,id);
    }


}
