package com.jpyl.music.api.dao.impl;

import com.jpyl.music.api.dao.def.SysDao;
import com.sun.javafx.collections.MappingChange;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@Repository
public class SysDaoImpl implements SysDao {
    @Autowired
    SqlSessionTemplate sqlSession;

    //管理员登录
    public Object sysLogin(String sysname,String syspass)throws Exception{
        Map map = new HashMap();
        map.put("sysname",sysname);
        map.put("syspass",syspass);
        return sqlSession.selectOne("sysLogin",map);

    }

    //管理员上传音乐
    @Transactional
    public Object sysUploaMusic(Map parameters)throws Exception{

        String result = "";
        //还是要先上传歌曲
        Integer mid = sqlSession.insert("",parameters);
        if(mid==null){//上传失败
            result = "fail";
        }else {
            //歌曲文件上传成功
            result = "success";
            //第一步上传歌词文件,拿到歌词id
            Integer m_lrcid = sqlSession.insert("sysUploadLrc", parameters);
            if (m_lrcid == null) {
                m_lrcid = 0;
                parameters.put("m_lrcid", m_lrcid);

            } else {
                parameters.put("m_lrcid", m_lrcid);
            }
            //第二步,上传mv文件,返回mvid
            Integer m_mvid = sqlSession.insert("sysUploadMV", parameters);
            if (m_mvid == null) {
                m_mvid = 0;
                parameters.put("m_mvid", m_mvid);

            } else {
                parameters.put("m_mvid", m_mvid);
            }
            //最后更新音乐表的歌词和mv字段
            sqlSession.update("",parameters);
        }
        //第三步,届时歌手id,专辑id,分类id都会包含在parameters里面
        //第四步,高清,无损,和铃声会在控制层里面做逻辑判断,然后将参数放在parameters里面
        //最后将所有数据插入表
        return result;
    }

}
