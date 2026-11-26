package com.brunowbbs.escola.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlunoResponseDTO {

    private String id;
    private String nome;
    private String email;
}
