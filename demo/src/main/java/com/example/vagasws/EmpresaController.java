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
public class EmpresaController {

    @Autowired
    private EmpresaRepo empresasRepo;

    public EmpresaController(){

    }

    @GetMapping("/fci/api/empresas")    
    public Iterable<Empresa> getEmpresas(){
        Iterable<Empresa> empresas = empresasRepo.findAll();
        return empresas;
    }

    @PostMapping("/fci/api/empresas")
    public Empresa createEmpresa(@RequestBody Empresa novaEmpresa){
        return empresasRepo.save(novaEmpresa);
    }

    // Método PUT para atualizar uma empresa existente
    @PutMapping("/fci/api/empresas/{id}")
    public Empresa updateEmpresa(long id, Empresa e){
        Optional<Empresa> emp = empresasRepo.findById(id);
        if(emp.isEmpty()) return null;
        Empresa empresa = emp.get();
        empresa.setNome(e.getNome());
        empresa.setCnpj(e.getCnpj());
        empresa.setEmail(e.getEmail());
        return empresasRepo.save(empresa);
    }

    // Método DELETE para remover uma empresa
    @DeleteMapping("/fci/api/empresas/{id}")
    public void deleteEmpresa(@PathVariable long id) {
        empresasRepo.deleteById(id);
    }
}
