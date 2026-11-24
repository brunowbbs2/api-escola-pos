package com.brunowbbs.escola.repositories;

import com.brunowbbs.escola.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity,String> {
}
