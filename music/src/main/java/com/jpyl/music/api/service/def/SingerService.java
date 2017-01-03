package com.jpyl.music.api.service.def;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface SingerService {

    //一级页面获取热门歌手列表
    public Object getFenLeiSingerList(int type1, int type2, int page) throws Exception;
}
