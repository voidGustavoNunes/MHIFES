package com.rfid.mhifes.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.rfid.mhifes.exception.RegistroNotFoundException;
import com.rfid.mhifes.model.Equipamento;
import com.rfid.mhifes.repository.EquipamentoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class EquipamentoService extends GenericServiceImpl<Equipamento, EquipamentoRepository> {

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        super(equipamentoRepository);

    }

    @Override
    public Equipamento atualizar(@NotNull @Positive Long id, @Valid @NotNull Equipamento equipamento) {
        return repository.findById(id)
                .map(equipamentoEditado -> {
                    validar(equipamento);
                    equipamentoEditado.setNome(equipamento.getNome());
                    return repository.save(equipamentoEditado);
                }).orElseThrow(() -> new RegistroNotFoundException(id));
    }

    @Override
    public void validar(@Valid @NotNull Equipamento equipamento) {

        if (equipamento.getNome() == null || equipamento.getNome().isEmpty()) {
            throw new DataIntegrityViolationException("Nome não pode ser nulo");
        }
        
    }
}
