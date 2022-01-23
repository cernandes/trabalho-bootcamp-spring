package com.example.trabalhobootcampspring.servicies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trabalhobootcampspring.dto.ClientDTO;
import com.example.trabalhobootcampspring.entities.Client;
import com.example.trabalhobootcampspring.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> pagedList = clientRepository.findAll(pageRequest);
		return pagedList.map(element -> new ClientDTO(element));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> client = clientRepository.findById(id);
		ClientDTO dto = new ClientDTO(client.get());
		return dto;
	}
}
