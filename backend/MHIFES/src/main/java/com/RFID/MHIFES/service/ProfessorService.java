package com.rfid.mhifes.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Professor;
import com.rfid.mhifes.repository.ProfessorRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ProfessorService {

    private ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> listar() {
        return professorRepository.findAll();
    }

    public Professor buscarPorId(@NotNull @Positive Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public Professor criar(@Valid @NotNull Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizar(@NotNull @Positive Long id, @Valid @NotNull Professor professor) {
        return professorRepository.findById(id)
                .map(professorEditado -> {
                    professorEditado.setNome(professor.getNome());
                    professorEditado.setMatricula(professor.getMatricula());
                    return professorRepository.save(professorEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    public void excluir(@NotNull @Positive Long id) {
        professorRepository.delete(professorRepository.findById(id)
                .orElseThrow(() -> new RegistroNotFoundException(id)));
    }

}
