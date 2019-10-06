package br.com.locadora.Model;


import br.com.locadora.Interface.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "filme", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "i_diretor"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Filme implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo")
    @NotNull(message = "O título deve ser informado!")
    @Size(max = 255, message = "O titulo deve possuir no máximo 255 caracteres!")
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_diretor")
    private Diretor diretor;

    @Column(name = "quantidade_total")
    private Integer quantidadeTotal;

    @Column(name = "quantidade_atual")
    private Integer quantidadeAtual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }
}
