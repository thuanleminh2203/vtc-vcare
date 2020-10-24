package com.venesa.ctvvcare.repository;


import com.venesa.ctvvcare.dto.UserDetailDTO;
import com.venesa.ctvvcare.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Integer> {
	
	@Query(value ="SELECT ud.id, ud.birthday , ud.fullname, ud.khoa, ud.phone_number, u.username from user_details ud JOIN user u on ud.id_user = u.id", nativeQuery=true)
	public List<UserDetailDTO> getAll();
}
