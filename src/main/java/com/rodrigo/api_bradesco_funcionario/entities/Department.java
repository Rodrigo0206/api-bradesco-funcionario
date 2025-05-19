package com.rodrigo.api_bradesco_funcionario.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}