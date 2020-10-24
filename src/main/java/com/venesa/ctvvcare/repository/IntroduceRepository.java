package com.venesa.ctvvcare.repository;

import com.venesa.ctvvcare.entity.IntroduceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author thuanlm
 * @created at 10/21/2020
 */
@Repository
public interface IntroduceRepository extends JpaRepository<IntroduceEntity,Long> {
}
