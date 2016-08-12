package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ProblemVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ProblemRepository {
  @Autowired
  private SqlSession sqlSession;


  public void insert(ProblemVo problemVo){
    sqlSession.insert("problem.insert", problemVo);
  }

}
