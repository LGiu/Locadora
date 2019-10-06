package br.com.locadora.Service;


import br.com.locadora.Model.Usuario;
import br.com.locadora.Repository.UsuarioRepository;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UsuarioService extends ServiceGenerico<Usuario> {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository, Usuario.class);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Retorno salva(Usuario usuario) {
        if (usuario.getSenha() != null) {
            usuario.setSenha(new BCryptPasswordEncoder().encode(UUID.randomUUID().toString()));
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

}
