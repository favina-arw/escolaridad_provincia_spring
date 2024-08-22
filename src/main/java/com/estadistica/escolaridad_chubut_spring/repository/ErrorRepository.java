package com.estadistica.escolaridad_chubut_spring.repository;

import com.estadistica.escolaridad_chubut_spring.entity.Error;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ErrorRepository extends JpaRepository<Error, Long> {
}
