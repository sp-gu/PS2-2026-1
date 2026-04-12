package com.example.vagasws;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Empresa {
    private long id;
    private String nome;
    private String cnpj;
    private String email;

    public Empresa (String nome, String cnpj, String email){
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
    }
}