package br.com.locadora.Service;


import br.com.locadora.Model.Filme;
import br.com.locadora.Model.Locacao;
import br.com.locadora.Repository.LocacaoRepository;
import br.com.locadora.Util.Retorno;
import br.com.locadora.Util.ServiceGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class LocacaoService extends ServiceGenerico<Locacao> {

    private final FilmeService filmeService;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository, FilmeService filmeService) {
        super(locacaoRepository, Locacao.class);
        this.filmeService = filmeService;
    }

    @Override
    @Transactional
    public Retorno salva(Locacao locacao) {
        return super.salva(locacao);
    }

    @Override
    public Retorno validador(Locacao locacao) {
        Retorno retorno = super.validador(locacao);
        if (retorno.isErro()) {
            return retorno;
        }

        if (locacao.getDataLocacao() == null) {
            locacao.setDataLocacao(new Date());
        }

        if (locacao.getDataLocacao() != null && locacao.getDataDevolucao() != null
                && locacao.getDataLocacao().after(locacao.getDataDevolucao())) {
            return new Retorno("A data de locação nao pode ser superior a data de devolução!");
        }

        Filme filme = filmeService.buscaPorId(locacao.getId());
        if (filme != null) {
            if (locacao.getDataDevolucao() == null && filme.getId() == null) {
                if (filme.getQuantidadeAtual() > 0) {
                    filme.setQuantidadeAtual(filme.getQuantidadeAtual() - 1);
                    Retorno retornoFilme = filmeService.salva(filme);
                    if (retornoFilme.isErro()) {
                        return new Retorno(retornoFilme.getMensagem());
                    }
                } else {
                    return new Retorno("O filme não pode ser locado porque está sem estoque!");
                }
            } else if (filme.getId() != null) {
                Locacao locacaoAnt = buscaPorId(locacao.getId());
                if (locacaoAnt.getDataDevolucao() == null && locacao.getDataDevolucao() != null) {
                    filme.setQuantidadeAtual(filme.getQuantidadeAtual() + 1);
                    Retorno retornoFilme = filmeService.salva(filme);
                    if (retornoFilme.isErro()) {
                        return new Retorno(retornoFilme.getMensagem());
                    }
                }
            }
        }

        return retorno;
    }
}
