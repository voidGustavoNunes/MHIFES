package com.rfid.mhifes.repository.postgres;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfid.mhifes.model.postgres.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    Page<Equipamento> findAll(Pageable pageable);
}