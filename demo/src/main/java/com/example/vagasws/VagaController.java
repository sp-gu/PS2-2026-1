package com.example.vagasws;
import java.util.*;

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

    @GetMapping("/h2-console/vagas")    
    public Iterable<Vaga> getVagas(){
        Iterable<Vaga> vagas = vagasRepo.findAll();
        return vagas;
    }

    @PostMapping("/h2-console/vagas")
    public Vaga createVaga(@RequestBody Vaga novaVaga){
        return vagasRepo.save(novaVaga);
    }
    
    @PutMapping("/h2-console/vgas/{id}")
    public Vaga updateVaga(long id, Vaga vagaAtualizada) {
        Optional<Vaga> vag = vagasRepo.findById(id);
        if(vag.isEmpty()) return null;
        Vaga vaga = vag.get();
        vaga.setTitulo(vagaAtualizada.getTitulo());
        vaga.setDescricao(vagaAtualizada.getDescricao());
        vaga.setPublicacao(vagaAtualizada.getPublicacao());
        vaga.setAtivo(vagaAtualizada.getAtivo());
        vaga.setIdEmpresa(vagaAtualizada.getIdEmpresa());
        return vagasRepo.save(vaga);
    }

    @DeleteMapping("/h2-console/vagas/{id}")
    public void deleteVaga(@PathVariable long id) {
        vagasRepo.deleteById(id);
    }
}
