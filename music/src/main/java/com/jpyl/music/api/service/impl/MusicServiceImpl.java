package com.jpyl.music.api.service.impl;

import com.jpyl.music.api.dao.def.MusicDao;
import com.jpyl.music.api.service.def.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/16.
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

    //音乐排行榜相关
    //top500
    public Object getTop500(int page)throws Exception{
        return musicDao.getTop500(page);
    }

    //民歌榜
    public Object getMinGe(int page)throws Exception{
        return musicDao.getMinGe(page);
    }

    //流行榜
    public Object getPopular(int page)throws Exception{
        return musicDao.getPopular(page);
    }

    //新歌榜
    public Object getNewSongs(int page)throws Exception{
        return musicDao.getNewSongs(page);
    }

    //原创榜
    public Object getYuanChuang(int page)throws Exception{
        return musicDao.getYuanChuang(page);
    }

    //k歌翻唱榜
    public Object getKTop(int page)throws Exception{
        return musicDao.getKTop(page);
    }

    //个性化推荐
    public Object getGeXing(String uid,int page)throws Exception{
        return musicDao.getGeXing(uid,page);
    }

    //用户点击和分享的行为
    public Object userAction(Map parameters)throws Exception{
        return musicDao.userAction(parameters);
    }

    //推荐里面的热销专辑
    public Object getHotSaleList(int page)throws Exception{
        return musicDao.getHotSaleList(page);
    }

    //推荐里面的歌单推荐
    public Object getRecommSongsList(int page)throws Exception{
        return musicDao.getRecommSongsList(page);
    }

    //推荐里面的新碟上架
    public Object getNewAlbumList(int page)throws Exception{
        return musicDao.getNewAlbumList(page);
    }

    //根据音乐id获取音乐详情，这里的mid可以是一个数组
    public Object getMusicDetail(String mids,String uid)throws Exception{
        return musicDao.getMusicDetail(mids,uid);
    }

    //查找相关
    public Object finSomething(String name,int type)throws Exception{
        return musicDao.finSomething(name,type);
    }

    //附近热唱
    public Object nearHotK(int page)throws Exception{
        return musicDao.nearHotK(page);
    }
}
