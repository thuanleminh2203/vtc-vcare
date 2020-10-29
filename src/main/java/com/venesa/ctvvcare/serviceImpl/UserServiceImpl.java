package com.venesa.ctvvcare.serviceImpl;


import com.venesa.ctvvcare.dto.UserDetailDTO;
import com.venesa.ctvvcare.entity.User;
import com.venesa.ctvvcare.repository.UserDetailRepository;
import com.venesa.ctvvcare.repository.UserRepository;
import com.venesa.ctvvcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDetailRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDetailDTO> findAll() {
		return repository.getAll();
	}

	@Override
	public Page<UserDetailDTO> getUsername(Pageable pageable) {
		return userRepository.getUsername(pageable);
	}

	@Override
	public User findByToken(String token) {
//		User user = userRepository.findByToken(token);
//		if (user == null){
//			return false;
//		}
		return userRepository.findByToken(token);
	}
}
