package br.com.locadora.Model;


import br.com.locadora.Interface.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "login", uniqueConstraints = {@UniqueConstraint(columnNames = {"chave"})})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Login implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Transient
    @Email(message = "Email inválido")
    private String email;

    @Transient
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "i_usuario")
    private Usuario usuario;

    @Column(name = "chave")
    private String chave;

    @Column(name = "data_logon")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dataLogon;

    @Column(name = "data_logoff")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dataLogoff;

    @Column(name = "data_ultima_requisicao")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date dataUltimaRequisicao;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Date getDataLogon() {
        return dataLogon;
    }

    public void setDataLogon(Date dataLogon) {
        this.dataLogon = dataLogon;
    }

    public Date getDataLogoff() {
        return dataLogoff;
    }

    public void setDataLogoff(Date dataLogoff) {
        this.dataLogoff = dataLogoff;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataUltimaRequisicao() {
        return dataUltimaRequisicao;
    }

    public void setDataUltimaRequisicao(Date dataUltimaRequisicao) {
        this.dataUltimaRequisicao = dataUltimaRequisicao;
    }
}
