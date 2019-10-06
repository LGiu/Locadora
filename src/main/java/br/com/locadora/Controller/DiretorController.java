package br.com.locadora.Controller;

import br.com.locadora.Model.Diretor;
import br.com.locadora.Service.DiretorService;
import br.com.locadora.Util.Retorno;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/diretor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diretor> buscaDiretor(HttpServletRequest request, @PathVariable long id) {
        return new ResponseEntity<>(diretorService.buscaPorId(id, true, request.getHeader("token")), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/diretores", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Diretor>> buscaDiretores(HttpServletRequest request) {
        return new ResponseEntity<>(diretorService.buscaLista(true, request.getHeader("token")), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/diretor", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaDiretor(HttpServletRequest request, @RequestBody Diretor diretor) {
        return new ResponseEntity<>(diretorService.salva(diretor, true, request.getHeader("token")), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/diretor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiDiretor(HttpServletRequest request, @PathVariable long id) {
        return new ResponseEntity<>(diretorService.exclui(id, true, request.getHeader("token")), HttpStatus.OK);

    }
}