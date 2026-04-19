package com.example.vagasws;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstudanteController {
    
    @Autowired
    private EstudanteRepo estudantesRepo;
    
    public EstudanteController(){
        
    }

    @GetMapping("/h2-console/estudantes")    
    public Iterable<Estudante> getEstudantes(){
        Iterable<Estudante> estudantes = estudantesRepo.findAll();
        return estudantes;
    }

    @PostMapping("/h2-console/estudantes")
    public Estudante createEstudante(@RequestBody Estudante novoEstudante){
        return estudantesRepo.save(novoEstudante);
    }
    
    @PutMapping("/h2-console/estudantes/{id}")
    public Estudante updateEstudante(long id, Estudante estudanteAtualizado) {
        Optional<Estudante> est = estudantesRepo.findById(id);
        if(est.isEmpty()) return null;
        Estudante estudante = est.get();
        estudante.setNome(estudanteAtualizado.getNome());
        estudante.setEmail(estudanteAtualizado.getEmail());
        estudante.setNascimento(estudanteAtualizado.getNascimento());
        estudante.setAnoIngresso(estudanteAtualizado.getAnoIngresso());
        return estudantesRepo.save(estudante);
    }

    @DeleteMapping("/h2-console/estudantes/{id}")
    public void deleteEstudante(@PathVariable long id) {
        estudantesRepo.deleteById(id);
    }
}
