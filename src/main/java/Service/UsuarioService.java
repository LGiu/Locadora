package Service;


import Model.Usuario;
import Util.Retorno;
import Util.ServiceGenerico;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends ServiceGenerico<Usuario> {

    @Override
    public Retorno salva(Usuario usuario){
        return super.salva(usuario);
    }

    @Override
    public Retorno validador(Usuario usuario){
        return new Retorno();
    }
}
