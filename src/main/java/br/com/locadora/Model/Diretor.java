package br.com.locadora.Model;


import br.com.locadora.Interface.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "diretor", uniqueConstraints = {@UniqueConstraint(columnNames = {"nome"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Diretor implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    @NotNull(message = "O nome deve ser informado!")
    @Size(max = 255, message = "O nome deve possuir no máximo 255 caracteres!")
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
