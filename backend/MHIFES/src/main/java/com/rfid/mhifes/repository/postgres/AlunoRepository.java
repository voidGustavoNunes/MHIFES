package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    boolean existsByMatricula(String matricula);

    Page<Aluno> findAll(Pageable pageable);
}