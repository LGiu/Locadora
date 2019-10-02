package br.com.locadora.Util;


import br.com.locadora.Interface.Model;

import java.util.List;

public class Retorno<U extends Model> {

    public Retorno() {
        this.erro = false;
    }

    public Retorno(String mensagem) {
        this.mensagem = mensagem;
        this.erro = true;
    }

    public Retorno(List<String> mensagens) {
        this.mensagem = String.join(";", mensagens);
        this.erro = true;
    }


    public Retorno(U clazz) {
        this.idGravado = clazz.getId();
        this.erro = false;
    }

    public Retorno(Long id) {
        this.idExcluido = id;
        this.erro = false;
    }

    private Long idGravado;

    private Long idExcluido;

    private String mensagem;

    private boolean erro;

    public Long getIdGravado() {
        return idGravado;
    }

    public void setIdGravado(Long idGravado) {
        this.idGravado = idGravado;
    }

    public Long getIdExcluido() {
        return idExcluido;
    }

    public void setIdExcluido(Long idExcluido) {
        this.idExcluido = idExcluido;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }
}
