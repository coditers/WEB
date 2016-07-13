package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ApplicantVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicantRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<ApplicantVo> getListByRecruitId( int recruitId){
    return sqlSession.selectList("applicant.selectListByRecruitId", recruitId);
  }

  public int insert( ApplicantVo vo ){
    return sqlSession.insert("applicant.insert", vo);
  }

  public void insertList( List<ApplicantVo> list){
    for( ApplicantVo vo : list){
      insert(vo);
    }
  }
}
