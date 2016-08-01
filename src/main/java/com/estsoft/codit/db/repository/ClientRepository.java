package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.ClientVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {
  @Autowired
  private SqlSession sqlSession;

  public int insert(ClientVo clientVo) {
    return sqlSession.insert("client.insert", clientVo);
  }


  public ClientVo get(int id) { return sqlSession.selectOne("client.selectById", id); }
  public ClientVo getByEmail(String email){return sqlSession.selectOne("client.selectByEmail", email); }
  public ClientVo getByEmailPassword( ClientVo clientVo ) { return sqlSession.selectOne("client.selectByEmailPassword", clientVo); }

}
