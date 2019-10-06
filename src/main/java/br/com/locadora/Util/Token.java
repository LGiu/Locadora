package br.com.locadora.Util;

public class Token {

    public Token(String mensagem) {
        this.token = null;
        this.mensagem = mensagem;
    }

    public Token(String token, String mensagem) {
        this.token = token;
        this.mensagem = mensagem;
    }

    private String token;

    private String mensagem;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
