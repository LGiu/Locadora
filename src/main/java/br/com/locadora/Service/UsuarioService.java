package br.com.locadora.Service;


import br.com.locadora.Model.Usuario;
import br.com.locadora.Repository.UsuarioRepository;
import br.com.locadora.Util.Cripto;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService extends ServiceGenerico<Usuario> {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, LoginService loginService) {
        super(usuarioRepository, Usuario.class, loginService);
        this.usuarioRepository = usuarioRepository;
        this.loginService = loginService;
    }

    @Override
    @Transactional
    public Retorno salva(Usuario usuario) {
        if (usuario.getSenha() != null) {
            usuario.setSenha(Cripto.criptografa(usuario.getSenha()));
        }
        return super.salva(usuario);
    }

    @Override
    public Retorno validador(Usuario usuario) {
        Retorno retorno = super.validador(usuario);
        if (retorno.isErro()) {
            return retorno;
        }

        Usuario usuarioAnt = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioAnt != null) {
            return new Retorno("O usuário já existe!");
        }

        return retorno;
    }

    public Usuario buscaPorEmailESenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

}
