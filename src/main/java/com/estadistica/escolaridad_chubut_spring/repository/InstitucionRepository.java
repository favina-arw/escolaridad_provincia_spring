package com.estadistica.escolaridad_chubut_spring.repository;

import com.estadistica.escolaridad_chubut_spring.entity.Institucion;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface InstitucionRepository extends JpaRepository<Institucion, Long> {
}
