package com.estsoft.codit.db.repository;

import com.estsoft.codit.db.vo.CartVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepository {

  @Autowired
  private SqlSession sqlSession;


  public int insert( CartVo vo){ return sqlSession.insert("cart.insert", vo); }

  public List<CartVo> getListByRecruitId( int recruitId ){ return sqlSession.selectList("cart.selectListByRecruitId", recruitId); }

  public void deleteByRecruitId(int recruitId){ sqlSession.delete("cart.deleteByRecruitId", recruitId); }
}
