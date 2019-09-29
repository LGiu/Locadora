package Model;


import Interface.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuario")
//TODO UNIQUE
public class Usuario implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @Email
    @Size(max = 255, message = "O email deve possuir no máximo 255 caracteres!")
    private String email;

    @Column(name = "senha")
    @JsonIgnore
    @Size(max = 30, message = "A senha deve possuir no máximo 30 caracteres!")
    private String senha;

    @Column(name = "nome")
    @Size(max = 255, message = "O nome deve possuir no máximo 255 caracteres!")
    private String nome;

    @Column(name = "estoque_total")
    private Integer estoqueTotal;

    @Column(name = "estoque_atual")
    private Integer estoqueAtual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getEstoqueTotal() {
        return estoqueTotal;
    }

    public void setEstoqueTotal(Integer estoqueTotal) {
        this.estoqueTotal = estoqueTotal;
    }

    public Integer getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }
}
