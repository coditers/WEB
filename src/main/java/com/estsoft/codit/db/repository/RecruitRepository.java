package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.RecruitVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RecruitRepository {
  @Autowired
  private SqlSession sqlSession;

  //Insert
  public int insert(RecruitVo recruitVo) { return sqlSession.insert("recruit.insert", recruitVo); }


  //Get RecruitVo Methods
  public List<RecruitVo> getListByClientId( int clientId) { return sqlSession.selectList("recruit.selectListByClientId", clientId); }

  public RecruitVo getById( int id ) { return sqlSession.selectOne("recruit.selectById", id); }

  public RecruitVo getByIdClientId(int recruitId, int clientId){

    //set query input parameter
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("clientId", clientId);
    map.put("recruitId", recruitId);

    return sqlSession.selectOne("recruit.selectByIdClientId", map);
  }


  //Update Methods
  public void updateRecruitDate(int recruitId, String fromDate, String toDate){

    //set query input parameter
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("recruitId", recruitId);
    map.put("fromDate", fromDate);
    map.put("toDate", toDate);

    sqlSession.update("recruit.updateDate", map);
  }

  public void updateEmailFormat(int recruitId, String emailFormat){

    //set query input parameter
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("recruitId", recruitId);
    map.put("emailFormat", emailFormat);

    sqlSession.update("recruit.updateEmailFormat", map);
  }
}
