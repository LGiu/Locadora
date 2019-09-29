package Service;


import Model.Locacao;
import Model.Usuario;
import Util.Retorno;
import Util.ServiceGenerico;
import org.springframework.stereotype.Service;

@Service
public class LocacaoService extends ServiceGenerico<Locacao> {

    @Override
    public Retorno salva(Locacao locacao){
        return super.salva(locacao);
    }

    @Override
    public Retorno validador(Locacao locacao){
        return new Retorno();
    }
}
