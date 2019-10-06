package br.com.locadora.Model;


import br.com.locadora.Interface.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "locacao")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Locacao implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_usuario")
    @NotNull(message = "O usuário deve ser informado!")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_filme")
    @NotNull(message = "O filme deve ser informado!")
    private Filme filme;

    @Column(name = "data_locacao")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dataLocacao;

    @Column(name = "data_prevista_devolucao")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "A data prevista de devolução deve ser informada!")
    private Date dataPervistaDevolucao;

    @Column(name = "data_devolucao")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dataDevolucao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataPervistaDevolucao() {
        return dataPervistaDevolucao;
    }

    public void setDataPervistaDevolucao(Date dataPervistaDevolucao) {
        this.dataPervistaDevolucao = dataPervistaDevolucao;
    }
}
