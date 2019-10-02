package br.com.locadora.Service;


import br.com.locadora.Model.Diretor;
import br.com.locadora.Repository.DiretorRepository;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DiretorService extends ServiceGenerico<Diretor> {

    @Autowired
    public DiretorService(DiretorRepository diretorRepository) {
        super(diretorRepository, Diretor.class);
    }

    @Override
    @Transactional
    public Retorno salva(Diretor diretor) {
        return super.salva(diretor);
    }

    @Override
    public Retorno validador(Diretor diretor) {
        return super.validador(diretor);
    }
}
