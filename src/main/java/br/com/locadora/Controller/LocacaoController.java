package br.com.locadora.Controller;

import br.com.locadora.Model.Locacao;
import br.com.locadora.Service.LocacaoService;
import br.com.locadora.Util.Retorno;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LocacaoController {

    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/locacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Locacao> buscaLocacao(HttpServletRequest request, @PathVariable long id) {
        return new ResponseEntity<>(locacaoService.buscaPorId(id, true, request.getHeader("token")), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/locacaos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Locacao>> buscaLocacaos(HttpServletRequest request) {
        return new ResponseEntity<>(locacaoService.buscaLista(true, request.getHeader("token")), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/locacao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaLocacao(HttpServletRequest request, @RequestBody Locacao locacao) {
        return new ResponseEntity<>(locacaoService.salva(locacao, true, request.getHeader("token")), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/locacao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiLocacao(HttpServletRequest request, @PathVariable long id) {
        return new ResponseEntity<>(locacaoService.exclui(id, true, request.getHeader("token")), HttpStatus.OK);
    }
}