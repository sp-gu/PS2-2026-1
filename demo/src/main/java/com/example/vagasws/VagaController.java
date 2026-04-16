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
public class VagaController {
    @Autowired
    private VagaRepo vagasRepo;

    public VagaController(){
        
    }

    @GetMapping("/fci/api/vagas")    
    public Iterable<Vaga> getVagas(){
        Iterable<Vaga> vagas = vagasRepo.findAll();
        return vagas;
    }

    @PostMapping("/fci/api/vagas")
    public Vaga createVaga(@RequestBody Vaga novaVaga){
        return vagasRepo.save(novaVaga);
    }
    
    @PutMapping("/fci/api/vgas/{id}")
    public Vaga updateVaga(@PathVariable long id, @RequestBody Vaga vagaAtualizada) {
        for (Vaga e : vagas) {
            if (e.getId() == id) {
                e.setTitulo(vagaAtualizada.getTitulo());
                e.setDescricao(vagaAtualizada.getDescricao());
                e.setPublicacao(vagaAtualizada.getPublicacao()); 
                e.setAtivo(vagaAtualizada.getAtivo());
                e.setIdEmpresa(vagaAtualizada.getIdEmpresa());
                return e;
            }
        }
        return null; // Retorna null caso a vaga não seja encontrada
    }

    @DeleteMapping("/fci/api/vagas/{id}")
    public void deleteVaga(@PathVariable long id) {
        vagas.removeIf(e -> e.getId() == id); 
    }
}
