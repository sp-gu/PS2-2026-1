package com.example.vagasws;
import java.util.ArrayList;
import java.util.List;

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
    public EstudanteController(){
        
    }

    @Autowired
    private EstudanteRepo estudantesRepo;

    @GetMapping("/fci/api/estudantes")    
    public Iterable<Estudante> getEstudantes(){
        Iterable<Estudante> estudantes = estudantesRepo.findAll();
        return estudantes;
    }

    @PostMapping("/fci/api/estudante")
    public Estudante createEstudante(@RequestBody Estudante novoEstudante){
        return estudantesRepo.save(novoEstudante);
    }
    
    @PutMapping("/fci/api/estudante/{id}")
    public Estudante updateEstudante(@PathVariable long id, @RequestBody Estudante estudanteAtualizado) {
        for (Estudante e : estudantes) {
            if (e.getId() == id) {
                e.setNome(estudanteAtualizado.getNome());
                e.setNascimento(estudanteAtualizado.getNascimento());
                e.setEmail(estudanteAtualizado.getEmail()); 
                e.setAnoIngresso(estudanteAtualizado.getAnoIngresso());
                return e;
            }
        }
        return null; // Retorna null caso o estudante não seja encontrada
    }

    @DeleteMapping("/fci/api/estudantes/{id}")
    public void deleteEstudante(@PathVariable long id) {
        estudantes.removeIf(e -> e.getId() == id); 
    }
}
