package com.jpyl.music.api.dao.impl;

import com.jpyl.music.api.dao.def.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/11/16.
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSessionTemplate sqlSession;

    //获取用户信息
    public Object getUserInfo(String userid)throws Exception{
        return sqlSession.selectOne("getUserInfo",userid);
    }

    //用户注册
    public Object userRegister(Map parameters)throws Exception{
        return sqlSession.insert("userRegister",parameters);
    }

    //用户注册的时候判断用户名是否存在
    public Object userIsRegister(String username)throws Exception{
        return sqlSession.selectOne("userIsRegister",username);
    }

    //用户注册的时候判断昵称是否存在
    public Object nickIsExist(String nickname)throws Exception{
        return sqlSession.selectOne("nickIsExist",nickname);
    }

    //用户登录
    @Transactional
    public Object userLogin(String username,String password)throws Exception{
        //第一步，判断用户表里是否存在
        Map parMap = new HashMap();
        Map retMap = new HashMap();
        parMap.put("username",username);
        parMap.put("userpass",password);
        Map login=sqlSession.selectOne("userLogin",parMap);
        if(login==null||login.size()<=0){
            //登录失败，用户名密码不存在
            retMap.put("code",1);
            retMap.put("msg","登录失败，用户名密码不存在！");
        }else {
            //登录成功检查用户token
            //1.判断用户是不是有token
            String uid = login.get("id").toString();
            parMap.put("uid",uid);
            //这里生成token
            String token = UUID.randomUUID().toString();
            token = token.replace("-","");
            parMap.put("token",token);
            Integer flag=sqlSession.selectOne("hasToken",uid);
            if(flag==null||flag<=0){
                //不存在token,插入token
                sqlSession.insert("insertToken",parMap);
            }else {
                //更新token
                sqlSession.update("changeToken",parMap);
            }
            //返回个人信息和最新的token
            retMap.put("rssult",login);
            retMap.put("token",sqlSession.selectOne("getToken",uid));
            retMap.put("code",0);
            retMap.put("msg","登录成功");
        }
        return retMap;
    }

    //需要验证用户token的接口需要验证token
    public Object checkToken(String uid,String token)throws Exception{
        Map map = new HashMap();
        map.put("uid",uid);
        map.put("token",token);
        return sqlSession.selectOne("checkToken",map);
    }

    //用户修改个人信息
    public Object changeUserInfo(Map parameters)throws Exception{
        return sqlSession.update("changeUserInfo",parameters);
    }

    //用户点击K歌
    public Object K(String mid)throws Exception{
        return sqlSession.update("updateKCounts",mid);
    }

    //用户上传自己的K歌
    public Object userKMusic(Map parameters)throws Exception{
        return sqlSession.insert("userKMusic",parameters);
    }

    //用户评论歌曲，这里只能是一级评论
    public Object userPingLunMusic(Map parameters)throws Exception{
        return sqlSession.insert("userPingLunMusic",parameters);
    }

    //用户收藏或是喜欢歌曲
    public Object userSaveMusic(Map parameters)throws Exception{
        return sqlSession.insert("userSaveMusic",parameters);
    }

    //用户创建歌单
    public Object userCreatSongsList(Map parameters)throws Exception{
        return sqlSession.insert("userCreatSongsList",parameters);
    }

    //用户编辑歌单之前获取相关数据
    public Object getDataBeforeUserEdit(String id)throws Exception{
        Map map = new HashMap();
        //1.获取歌单分类
        Object type = sqlSession.selectList("getSongsListType");
        //2.获取编辑之前的歌单的信息
        Object info = sqlSession.selectOne("getDataBeforeUserEdit",id);
        map.put("type",type);
        map.put("info",info);
        return map;
    }

    //用户编辑歌单
    public Object userEditSongsList(Map parameters)throws Exception{
        return sqlSession.update("userEditSongsList",parameters);
    }

    //用户向歌单里面添加歌曲
    public Object userAddSongToSongsList(String musicids,String id)throws Exception{
        Map map = new HashMap();
        map.put("id",id);
        if(musicids.contains(",")){
            //多首歌曲的情况
            String[] ids = musicids.split(",");
            for(int i=0;i<ids.length;i++){
                ids[i] = ","+ids[i];
                map.put("musicids",ids[i]);
                sqlSession.update("userAddSongToSongsList",map);
                map.remove("musicids");
            }
        }
        else {
            //一首歌的情况
            musicids = ","+musicids;
            map.put("musicids",musicids);
            sqlSession.update("userAddSongToSongsList",map);
        }
        return null;
    }
}
