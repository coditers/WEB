package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.ClientRepository;
import com.estsoft.codit.db.vo.ClientVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by JisungLim on 2016-06-21.
 */
@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  /**
   * Check whether the sign up process succeed or not.
   * If signup failed, return false. Also succeeded, true.
   * @return true if succeed.
   */
  public ClientVo getVoById(int id) {
    //Check whether the client info exists.
    return clientRepository.get(id);
  }

  /**
   * Register new client.
   *
   * @param clientVo A information set of client who wants to sign up.
   * @return 1 if sign up process is succeed.
   */
  public int signup( ClientVo clientVo ) {
    return clientRepository.insert( clientVo );
  }

  /**
   * Valid the client's email and password.
   */
  public ClientVo validsignin( ClientVo clientVo ) {

    return clientRepository.selectByEmailPassword( clientVo );
  }

}
