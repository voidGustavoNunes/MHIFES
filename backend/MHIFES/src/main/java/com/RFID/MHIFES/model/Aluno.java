package com.RFID.MHIFES.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "aluno")
public class Aluno extends Pessoa {


}