package com.rfid.mhifes.service;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.exception.ValidationException;
import com.rfid.mhifes.model.postgres.Aluno;
import com.rfid.mhifes.repository.postgres.AlunoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class AlunoService extends GenericServiceImpl<Aluno, AlunoRepository> {

    public AlunoService(AlunoRepository alunoRepository) {
        super(alunoRepository);
    }

    @Override
    public Aluno criar(@Valid @NotNull Aluno aluno) {

        if (repository.existsByMatricula(aluno.getMatricula())) {
            throw new ValidationException("Matrícula já cadastrada");
        }

        return repository.save(aluno);
    }

    @Override
    public Aluno atualizar(@NotNull @Positive Long id, @Valid @NotNull Aluno aluno) {
        return repository.findById(id).map(alunoEditado -> {
            // Valida se a matrícula já está cadastrada para outro aluno ou se a matrícula é a mesma
            if (!alunoEditado.getMatricula().equals(aluno.getMatricula()) && repository
                    .existsByMatricula(aluno.getMatricula())) {
                throw new ValidationException("Matrícula já cadastrada");
            }
            alunoEditado.setNome(aluno.getNome());
            alunoEditado.setMatricula(aluno.getMatricula());
            alunoEditado.setCurso(aluno.getCurso());
            return repository.save(alunoEditado);
        }).orElseThrow(() -> new RegistroNotFoundException(id));
    }
}
