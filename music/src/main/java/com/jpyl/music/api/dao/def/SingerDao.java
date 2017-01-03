package com.jpyl.music.api.dao.def;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
public interface SingerDao {

    //一级页面获取热门歌手列表
    public Object getFenLeiSingerList(int type1, int type2, int page) throws Exception;
}
