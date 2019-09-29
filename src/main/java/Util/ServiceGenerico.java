package Util;


import Interface.Model;
import Util.Retorno;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceGenerico<U extends Model>  {

    public Retorno validador(U u){
        if(u == null){
            return new Retorno("Objeto nulo!");
        }
        return new Retorno();
    }

    public Retorno salva(U u){
        Retorno retorno = validador(u);
        if(retorno.isErro()){
            return retorno;
        }

        return retorno;
    }

    public Retorno exclui(U u){
        Retorno retorno = validador(u);
        if(retorno.isErro()){
            return retorno;
        }

        return retorno;
    }

    public U buscaPorId(U u){
        return null;
    }

    public List<U> buscaLista(){
        List<U> list = new ArrayList<>();

        return list;
    }


}
