package com.example.trabalhobootcampspring.servicies;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trabalhobootcampspring.dto.ClientDTO;
import com.example.trabalhobootcampspring.entities.Client;
import com.example.trabalhobootcampspring.repositories.ClientRepository;
import com.example.trabalhobootcampspring.servicies.exceptions.DatabaseException;
import com.example.trabalhobootcampspring.servicies.exceptions.ObjectNotFoundException;

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
		Client entity = client.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setBirthDate(dto.getBirthDate());
		entity.setIncome(dto.getIncome());
		entity.setChildren(dto.getChildren());
		clientRepository.save(entity);
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = clientRepository.getOne(id);
			entity.setName(dto.getName());
			entity.setCpf(dto.getCpf());
			entity.setBirthDate(dto.getBirthDate());
			entity.setIncome(dto.getIncome());
			entity.setChildren(dto.getChildren());
			clientRepository.save(entity);
			return new ClientDTO(entity);
		} catch (EntityNotFoundException msg) {
			throw new ObjectNotFoundException("Object Not Found!" + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		} catch (EmptyResultDataAccessException msg) {
			throw new ObjectNotFoundException("Object Not Found!");
		} catch (DataIntegrityViolationException msg) {
			throw new DatabaseException("Database Integrity Violation!");
		}
	}
}
