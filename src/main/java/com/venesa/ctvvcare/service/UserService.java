package com.venesa.ctvvcare.service;

import com.venesa.ctvvcare.dto.UserDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
	List<UserDetailDTO> findAll();

	Page<UserDetailDTO> getUsername(Pageable pageable);
}
