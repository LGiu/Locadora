package Service;


import Model.Filme;
import Model.Usuario;
import Util.Retorno;
import Util.ServiceGenerico;
import org.springframework.stereotype.Service;

@Service
public class FilmeService extends ServiceGenerico<Filme> {

    @Override
    public Retorno salva(Filme filme){
        return super.salva(filme);
    }

    @Override
    public Retorno validador(Filme filme){
        return new Retorno();
    }
}
