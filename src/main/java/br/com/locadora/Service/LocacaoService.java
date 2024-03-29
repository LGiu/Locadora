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
    private final LoginService loginService;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository, FilmeService filmeService, LoginService loginService) {
        super(locacaoRepository, Locacao.class, loginService);
        this.filmeService = filmeService;
        this.loginService = loginService;
    }

    @Override
    @Transactional
    public Retorno salva(Locacao locacao) {
        return super.salva(locacao);
    }

    @Override
    public Retorno validador(Locacao locacao) {
        if (locacao.getFilme() == null) {
            return new Retorno("O filme deve ser informado");
        }

        Filme filme = (Filme) filmeService.buscaPorId(locacao.getFilme().getId());
        if (filme != null) {
            if (locacao.getId() == null) {
                Retorno retorno = super.validador(locacao);
                if (retorno.isErro()) {
                    return retorno;
                }

                retorno = validaDatas(locacao);
                if (retorno.isErro()) {
                    return retorno;
                }

                if (filme.getQuantidadeAtual() > 0) {
                    filme.setQuantidadeAtual(filme.getQuantidadeAtual() - 1);
                    Retorno retornoFilme = filmeService.salva(filme);
                    if (retornoFilme.isErro()) {
                        return new Retorno(retornoFilme.getMensagem());
                    }
                } else {
                    return new Retorno("O filme não pode ser locado porque está sem estoque!");
                }
            } else if (locacao.getDataDevolucao() != null) {
                Date dataDevolucao = locacao.getDataDevolucao();
                locacao = buscaPorId(locacao.getId());
                locacao.setDataDevolucao(dataDevolucao);

                Retorno retorno = super.validador(locacao);
                if (retorno.isErro()) {
                    return retorno;
                }

                retorno = validaDatas(locacao);
                if (retorno.isErro()) {
                    return retorno;
                }

                filme.setQuantidadeAtual(filme.getQuantidadeAtual() + 1);
                /*Retorno retornoFilme = filmeService.salva(filme);
                if (retornoFilme.isErro()) {
                    return new Retorno(retornoFilme.getMensagem());
                }*/
                locacao.setFilme(filme);
            } else {
                Retorno retorno = super.validador(locacao);
                if (retorno.isErro()) {
                    return retorno;
                }
            }
        } else {
            return new Retorno("O filme informado não existe!");
        }

        return new Retorno();
    }

    private Retorno validaDatas(Locacao locacao) {
        if (locacao.getDataLocacao() == null) {
            locacao.setDataLocacao(new Date());
        }

        if (locacao.getDataLocacao().after(locacao.getDataPervistaDevolucao())) {
            return new Retorno("A data de locação nao pode ser superior a data prevista devolução!");
        }


        if (locacao.getDataDevolucao() != null && locacao.getDataLocacao().after(locacao.getDataDevolucao())) {
            return new Retorno("A data de locação nao pode ser superior a data de devolução!");
        }

        if (locacao.getDataDevolucao() != null && locacao.getDataDevolucao().before(new Date())) {
            return new Retorno("A data de devolução deve ser maior o igual a hoje!");
        }

        if (locacao.getId() == null && locacao.getDataPervistaDevolucao() != null && locacao.getDataPervistaDevolucao().before(new Date())) {
            return new Retorno("A data prevista de devolução deve ser maior o igual a hoje!");
        }

        return new Retorno();
    }
}
