package com.brunowbbs.escola.services;

import com.brunowbbs.escola.dtos.AlunoRequestDTO;
import com.brunowbbs.escola.dtos.AlunoResponseDTO;
import com.brunowbbs.escola.entities.AlunoEntity;
import com.brunowbbs.escola.repositories.AlunoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {

    public record AlunoResponseByIdDTO(
            String id,
            String nome,
            Integer idade
    ) {}

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository){
        this.repository = repository;
    }


    private AlunoResponseDTO converteToDTO(AlunoEntity aluno){
        return new AlunoResponseDTO(aluno.getId(), aluno.getNome());
    }

    public List<AlunoResponseDTO> listarTodos(){
        return this.repository.findAll().stream().map(this::converteToDTO).toList();
    }

    public AlunoResponseByIdDTO buscarPorID(String id){
        AlunoEntity aluno = this.repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        return new AlunoResponseByIdDTO(aluno.getId(), aluno.getNome(), aluno.getIdade());
    }

    public AlunoResponseDTO cadastrar(AlunoRequestDTO alunoDTO){
        AlunoEntity alunoEntity = new AlunoEntity();
        alunoEntity.setNome(alunoDTO.getNome());
        alunoEntity.setEmail(alunoDTO.getEmail());
        alunoEntity.setIdade(alunoDTO.getIdade());

        AlunoEntity alunoSalvo = this.repository.save(alunoEntity);

        return converteToDTO(alunoSalvo);

    }

    public AlunoResponseDTO atualizar(String id, AlunoRequestDTO dto){

        AlunoEntity entity = this.repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!"));

        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setEmail(dto.getEmail());

      AlunoEntity alunoAtualizado = this.repository.save(entity);

      return converteToDTO(alunoAtualizado);

    }

    public void excluir(String id){
        if(!this.repository.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        this.repository.deleteById(id);
    }
}
