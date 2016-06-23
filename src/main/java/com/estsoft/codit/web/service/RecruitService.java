package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.RecruitRepository;
import com.estsoft.codit.db.vo.RecruitVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by Jisung Lim on 2016-06-21.
 */

@Service
public class RecruitService {

  @Autowired
  RecruitRepository recruitRepository;

  public RecruitVo getVo(int id) {
    return recruitRepository.get(id);
  }
}
