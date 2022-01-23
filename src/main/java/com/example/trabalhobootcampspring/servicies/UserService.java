package com.example.trabalhobootcampspring.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.trabalhobootcampspring.dto.UserDTO;
import com.example.trabalhobootcampspring.entities.User;
import com.example.trabalhobootcampspring.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
		Page<User> pagedList = userRepository.findAll(pageRequest);
		return pagedList.map(element -> new UserDTO(element));
	}
}
