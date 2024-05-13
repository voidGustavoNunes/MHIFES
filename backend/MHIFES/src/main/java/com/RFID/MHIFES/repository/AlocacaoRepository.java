package com.rfid.mhifes.repository;

import java.time.Year;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rfid.mhifes.model.Alocacao;

import net.sf.jasperreports.engine.JasperPrint;

public interface AlocacaoRepository extends JpaRepository<Alocacao, Long> {

    @Query("SELECT a FROM Alocacao a")
    List<Alocacao> findAll();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Ativo'")
    List<Alocacao> findAllStatusAtivo();

    @Query("SELECT a FROM Alocacao a WHERE a.status = 'Inativo'")
    List<Alocacao> findAllStatusInativo();

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.ano = ?1 AND a.status = 'Ativo'")
    List<Alocacao> findByAno(Year ano);

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.semestre = ?1 AND a.status = 'Ativo'")
    List<Alocacao> findBySemestre(Long semestre);

    @Query("SELECT DISTINCT a FROM Alocacao a JOIN a.periodoDisciplina pd JOIN pd.periodo p WHERE p.ano = ?1 and p.semestre = ?2 AND a.status = 'Ativo'")
    List<Alocacao> findByAnoAndSemestre(@Param("ano") Year ano, @Param("semestre") Long semestre);

}
