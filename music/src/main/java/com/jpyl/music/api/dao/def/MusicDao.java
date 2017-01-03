package com.jpyl.music.api.dao.def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/2.
 */
public interface MusicDao {

    //音乐排行榜相关
    //top500
    public Object getTop500(int page)throws Exception;

    //民歌榜
    public Object getMinGe(int page)throws Exception;

    //流行榜
    public Object getPopular(int page)throws Exception;

    //新歌榜
    public Object getNewSongs(int page)throws Exception;

    //原创榜
    public Object getYuanChuang(int page)throws Exception;

    //k歌翻唱榜
    public Object getKTop(int page)throws Exception;

    //个性化推荐
    public Object getGeXing(String uid,int page)throws Exception;

    //用户点击和分享的行为
    public Object userAction(Map parameters)throws Exception;

    //推荐里面的热销专辑
    public Object getHotSaleList(int page)throws Exception;

    //推荐里面的歌单推荐
    public Object getRecommSongsList(int page)throws Exception;

    //推荐里面的新碟上架
    public Object getNewAlbumList(int page)throws Exception;

    //根据音乐id获取音乐详情，这里的mid可以是一个数组
    public Object getMusicDetail(String mids,String uid)throws Exception;

    //查找相关
    public Object finSomething(String name,int type)throws Exception;

    //附近热唱
    public Object nearHotK(int page)throws Exception;
}
