package com.brunowbbs.escola.repositories;

import com.brunowbbs.escola.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<AlunoEntity,String> {
    Optional<AlunoEntity> findByEmail(String email);
    Optional<List<AlunoEntity>> findAllByIdade(int idade);
}
