package com.example.vagasws;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VagaController {
    private List<Vaga> vagas = new ArrayList<>();
    public VagaController(){
        vagas.add(new Vaga(1, "Desenvolvedor Java", "Atuação em projetos backend com Java e Spring. Experiência desejada em APIs REST.", "2025-10-01", true, 1));
        vagas.add(new Vaga(2, "Analista de Suporte Técnico", "Suporte a clientes, resolução de chamados e participação em treinamentos internos.", "2025-09-27", true, 2));
        vagas.add(new Vaga(3, "Engenheiro de Software", "Desenvolvimento de soluções para sistemas corporativos, integração e automação.", "2025-10-03", false, 3));
        vagas.add(new Vaga(4, "Analista de Dados", "Manipulação e análise de grandes volumes de dados. Conhecimentos de SQL e Python.", "2025-09-18", true, 4));
        vagas.add(new Vaga(5, "Designer Digital", "Criação de materiais gráficos, UX/UI e participação em campanhas de marketing.", "2025-09-30", false, 5));
        vagas.add(new Vaga(6, "Consultor de Projetos", "Elaboração e acompanhamento de projetos empresariais e treinamentos.", "2025-10-06", true, 1));
        vagas.add(new Vaga(7, "Programador Full Stack", "Desenvolvimento de aplicações web frontend e backend com foco em automação.", "2025-10-04", true, 2));
    }

    @GetMapping("/fci/api/vagas")    
    public Iterable<Vaga> getVagas(){
        return vagas;
    }

    @GetMapping("/fci/api/vagas/{id}")
    public Vaga getVaga(@PathVariable long id){
        Vaga resposta = null;

        for(Vaga v: vagas) {
            if(v.getId() == id){
                resposta = v;
                break;
            }
        }
        return resposta;
    }

    @PostMapping("/fci/api/vagas")
    public Vaga createVaga(@RequestBody Vaga novaVaga){
        long maiorId = 1;
        for (Vaga e: vagas){
            if(e.getId() > maiorId){
                maiorId = e.getId();
            }
        }
        long novoId = maiorId+1;
        novaVaga.setId(novoId);
        vagas.add(novaVaga);
        return novaVaga;
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
