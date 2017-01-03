package com.jpyl.music.api.dao.impl;

import com.jpyl.music.api.dao.def.MusicDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/2.
 */
@Repository
public class MusicDaoImpl implements MusicDao {
    @Autowired
    SqlSessionTemplate sqlSession;

    //音乐排行榜相关
    //top500
    public Object getTop500(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("getTop500",page);
    }

    //民歌榜
    public Object getMinGe(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("getMinGe",page);
    }

    //流行榜
    public Object getPopular(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("getPopular",page);
    }

    //新歌榜
    public Object getNewSongs(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("getNewSongs",page);
    }

    //原创榜
    public Object getYuanChuang(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("getYuanChuang",page);
    }

    //k歌翻唱榜
    public Object getKTop(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("getKTop",page);
    }

    //个性化推荐
    public Object getGeXing(String uid,int page)throws Exception{
        page=page-1;
        page = page*50;
        List result;
        if(uid==null||uid.equals("")||uid.equals("(null)")){
            uid="0";
        }
        Map data = (HashMap)sqlSession.selectList("getGeXing",uid);
        if(data.size()<=0||data==null){
            //用户在个性表里面没数据，进行默认推荐
            result = sqlSession.selectList("getTop500",page);
        }else {
            //用户在个性表里面有数据
            data.put("page",page);
            result = sqlSession.selectList("returnGeXing",data);
        }
        return result;
    }

    //用户点击和分享的行为
    public Object userAction(Map parameters)throws Exception{
        return sqlSession.update("userAction",parameters);
    }

    //推荐里面的热销专辑
    public Object getHotSaleList(int page)throws Exception{
        page = page-1;
        return sqlSession.selectList("getHotSaleList",page*50);
    }

    //推荐里面的歌单推荐
    public Object getRecommSongsList(int page)throws Exception{
        page = page-1;
        return sqlSession.selectList("getRecommSongsList",page*50);
    }

    //推荐里面的新碟上架
    public Object getNewAlbumList(int page)throws Exception{
        page = page-1;
        return sqlSession.selectList("getNewAlbumList",page*50);
    }

    //根据音乐id获取音乐详情，这里的mid可以是一个数组
    public Object getMusicDetail(String mids,String uid)throws Exception{
        Map parMap = new HashMap();
        List data= new ArrayList();
        if(mids.contains(",")){
            //多个音乐id的情况
            String[] mid = mids.split(",");
            if(uid==null) {//用户id为空的时候，返回isSave=0
                for (int i = 0; i < mid.length; i++) {
                    parMap.put("mid", mid[i]);
                    Map map = (HashMap)sqlSession.selectOne("getMusicDetail", parMap);
                    map.put("isSave",0);
                    parMap.remove("mid");
                    data.add(i, map);
                }
            }
            else {
                //用户id不为空，后台查找判断用户是否收藏歌
                for (int i = 0; i < mid.length; i++) {
                    parMap.put("mid", mid[i]);
                    parMap.put("uid",uid);
                    Map map = (HashMap)sqlSession.selectOne("getMusicDetail", parMap);
                    map.put("isSave",sqlSession.selectOne("userIsSaveMusic",parMap));
                    parMap.remove("mid");
                    data.add(i, map);
                }
            }
        }else {//单个音乐id的情况
            parMap.put("mid",mids);
            Map map = (HashMap)sqlSession.selectOne("getMusicDetail",parMap);
            if(uid==null){
                map.put("isSave",0);
            }else {
                parMap.put("uid",uid);
                map.put("isSave",sqlSession.selectOne("userIsSaveMusic",parMap));
            }
            data.add(0,map);
        }
        return data;
    }

    //查找相关
    public Object finSomething(String name,int type)throws Exception{
        Object result;
        switch (type){
            case 1:result = sqlSession.selectList("findSongsByName",name);break;//查找音乐
            case 2:result = sqlSession.selectList("findSingersByName",name);break;//查找歌手
            case 3:result = sqlSession.selectList("findAlbumsByName",name);break;//查找专辑
            case 4:result = sqlSession.selectList("findSongsListByName",name);break;//查找歌单
            case 5:result = sqlSession.selectList("findMvByName",name);break;//查找mv
            default:result = sqlSession.selectList("findSongsByName",name);break;//默认查找音乐
        }
        return result;
    }

    //附近热唱
    public Object nearHotK(int page)throws Exception{
        page = page-1;
        page = page*50;
        return sqlSession.selectList("nearHotK",page);
    }

}
