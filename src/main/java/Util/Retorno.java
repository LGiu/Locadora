package Util;

public class Retorno {

    public Retorno() {
        this.erro = false;
    }

    public Retorno(String mensagem) {
        this.mensagem = mensagem;
        this.erro = true;
    }

    private Integer idGravado;

    private Integer idExcluido;

    private String mensagem;

    private boolean erro;

    public void setIdGravado(Integer idGravado) {
        this.idGravado = idGravado;
    }

    public void setIdExcluido(Integer idExcluido) {
        this.idExcluido = idExcluido;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getIdGravado() {
        return idGravado;
    }

    public Integer getIdExcluido() {
        return idExcluido;
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
