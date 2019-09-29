package Service;


import Model.Diretor;
import Util.Retorno;
import Util.ServiceGenerico;
import org.springframework.stereotype.Service;

@Service
public class DiretorService extends ServiceGenerico<Diretor> {

    @Override
    public Retorno salva(Diretor diretor){
        return super.salva(diretor);
    }

    @Override
    public Retorno validador(Diretor diretor){
        return new Retorno();
    }
}
