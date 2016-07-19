package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ResultVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by estsoft on 2016-07-19.
 */
@Repository
public class ResultRepository {

    @Autowired
    SqlSession sqlSession;

    public List<ResultVo> getResultList(int applicantId, int testCaseId){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("applicantId", applicantId);
        map.put("testCaseId", testCaseId);

        return null;//sqlSession.selectList("result.selectByIds", map);
    }
}
