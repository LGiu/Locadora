package br.com.locadora.Service;


import br.com.locadora.Model.Filme;
import br.com.locadora.Repository.FilmeRepository;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FilmeService extends ServiceGenerico<Filme> {

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        super(filmeRepository, Filme.class);
    }

    @Override
    @Transactional
    public Retorno salva(Filme filme) {
        return super.salva(filme);
    }

    @Override
    public Retorno validador(Filme filme) {
        return super.validador(filme);
    }
}
