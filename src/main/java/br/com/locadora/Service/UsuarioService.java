package br.com.locadora.Service;


import br.com.locadora.Model.Usuario;
import br.com.locadora.Repository.UsuarioRepository;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UsuarioService extends ServiceGenerico<Usuario> {

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        super(usuarioRepository, Usuario.class);
    }

    @Override
    @Transactional
    public Retorno salva(Usuario usuario) {
        return super.salva(usuario);
    }

    @Override
    public Retorno validador(Usuario usuario) {
        return new Retorno();
    }
}
