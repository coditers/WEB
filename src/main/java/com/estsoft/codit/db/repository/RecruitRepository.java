package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.RecruitVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecruitRepository {
  @Autowired
  private SqlSession sqlSession;

  public List<RecruitVo> getListByClientId( int clientId) { return sqlSession.selectList("recruit.selectListByClientId", clientId); }

  public int insert(RecruitVo recruitVo) { return sqlSession.insert("recruit.insert", recruitVo); }

  public RecruitVo get( int id ) { return sqlSession.selectOne("recruit.selectById", id); }

}
