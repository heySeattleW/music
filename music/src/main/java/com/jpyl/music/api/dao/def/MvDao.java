package com.jpyl.music.api.dao.def;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface MvDao {

    //获取热门mv
    public Object getHotMvList(int page)throws Exception;
}
