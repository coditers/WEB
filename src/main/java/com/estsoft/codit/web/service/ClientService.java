package com.estsoft.codit.web.service;

import com.estsoft.codit.db.repository.ClientRepository;
import com.estsoft.codit.db.vo.ClientVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;

  //Get ClientVo Methods
  public ClientVo getClientVoById(int clientId)                   { return clientRepository.get( clientId ); }
  public ClientVo getClientVoByEmailPassword( ClientVo clientVo ) { return clientRepository.getByEmailPassword( clientVo ); }

  //Insert ClientVo method
  public int      registerClient( ClientVo clientVo )               { return clientRepository.insert( clientVo ); }
}
