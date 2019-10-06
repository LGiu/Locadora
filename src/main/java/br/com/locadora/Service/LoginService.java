package br.com.locadora.Service;

import br.com.locadora.Model.Login;
import br.com.locadora.Model.Usuario;
import br.com.locadora.Repository.LoginRepository;
import br.com.locadora.Util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class LoginService extends ServiceGenerico<Login> {

    private final UsuarioService usuarioService;
    private final LoginRepository loginRepository;
    private final LoginService loginService;

    @Autowired
    public LoginService(@Lazy UsuarioService usuarioService, LoginRepository loginRepository, @Lazy LoginService loginService) {
        super(loginRepository, Login.class, loginService);
        this.usuarioService = usuarioService;
        this.loginRepository = loginRepository;
        this.loginService = loginService;
    }

    public Token logon(Login login) {
        Retorno retorno = super.validador(login);
        if (retorno.isErro()) {
            return new Token(retorno.getMensagem());
        }

        if (login.getEmail() == null || login.getSenha() == null) {
            return new Token("Email e senha devem ser informados!");
        }


        login.setSenha(Cripto.criptografa(login.getSenha()));

        Usuario usuario = usuarioService.buscaPorEmailESenha(login.getEmail(), login.getSenha());
        if (usuario != null) {
            if (loginRepository.findByUsuarioAndDataLogoffIsNull(usuario) != null) {
                return new Token("Usuário já está logado!");
            }

            login.setChave(UUID.randomUUID().toString());
            login.setDataLogon(new Date());
            login.setDataUltimaRequisicao(new Date());
            login.setUsuario(usuario);
            retorno = super.salva(login, false);
            if (retorno.isErro()) {
                return new Token(retorno.getMensagem());
            }
            return new Token(login.getChave(), null);
        } else {
            return new Token("Usuário informado não existe!");
        }
    }

    public Token logoff(Login login) {
        if (login.getChave() == null) {
            return new Token("Chave deve ser informada!");
        }

        login = loginRepository.findByChave(login.getChave());
        if (login == null) {
            return new Token("Chave informada não existe!");
        }

        login.setDataUltimaRequisicao(new Date());
        login.setDataLogoff(new Date());
        Retorno retorno = super.salva(login, false);
        if (retorno.isErro()) {
            return new Token(retorno.getMensagem());
        }
        return new Token("Logoff realizado com sucesso!");
    }

    public Retorno verificaLogin(String chave) {
        Login login = loginRepository.findByChaveAndDataLogoffIsNull(chave);
        if (login == null) {
            return new Retorno("Usuário deve estar logado!");
        }

        if (Datas.data(new Date(), 30).after(login.getDataUltimaRequisicao())) {
            login.setDataLogoff(new Date());
            super.salva(login, false);
            return new Retorno("Login expirou!");
        }

        login.setDataUltimaRequisicao(new Date());
        super.salva(login, false);

        return new Retorno();
    }
}
