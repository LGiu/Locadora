package br.com.locadora.Service;


import br.com.locadora.Model.Filme;
import br.com.locadora.Repository.FilmeRepository;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FilmeService<List> extends ServiceGenerico<Filme> {

    private final FilmeRepository filmeRepository;
    private final DiretorService diretorService;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository, DiretorService diretorService) {
        super(filmeRepository, Filme.class);
        this.filmeRepository = filmeRepository;
        this.diretorService = diretorService;
    }

    @Override
    @Transactional
    public Retorno salva(Filme filme) {
        return super.salva(filme);
    }

    @Override
    public Retorno validador(Filme filme) {
        Retorno retorno = super.validador(filme);
        if (retorno.isErro()) {
            return retorno;
        }


        if (filme.getQuantidadeTotal() == null) {
            filme.setQuantidadeTotal(1);
        }

        if (filme.getQuantidadeAtual() == null) {
            filme.setQuantidadeAtual(filme.getQuantidadeTotal());
        }

        if (filme.getQuantidadeTotal() < filme.getQuantidadeAtual()) {
            return new Retorno("A quantidade total não pode ser menor que a quantidade atual!");
        }

        if (!diretorService.existe(filme.getDiretor())) {
            return new Retorno("O diretor informado não existe!");
        }

        return retorno;
    }

    public java.util.List<Filme> buscaFilmesPorTitulo(String titulo) {
        return filmeRepository.findFilmeByTituloLike("%" + titulo + "%");
    }
}
