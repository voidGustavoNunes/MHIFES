package com.rfid.mhifes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;

import com.rfid.mhifes.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    boolean existsByHoraInicioAndHoraFim(LocalTime horaInicio, LocalTime horaFim);
}
