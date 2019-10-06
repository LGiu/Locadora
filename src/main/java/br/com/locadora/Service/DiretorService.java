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

    private final LoginService loginService;

    @Autowired
    public DiretorService(DiretorRepository diretorRepository, LoginService loginService) {
        super(diretorRepository, Diretor.class, loginService);
        this.loginService = loginService;
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
