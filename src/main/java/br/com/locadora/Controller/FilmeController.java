package br.com.locadora.Controller;

import br.com.locadora.Model.Filme;
import br.com.locadora.Service.FilmeService;
import br.com.locadora.Util.Retorno;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filme/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Filme> buscaFilme(@PathVariable long id) {
        return new ResponseEntity<>((Filme) filmeService.buscaPorId(id, true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filmes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Filme>> buscaFilmes() {
        return new ResponseEntity<>(filmeService.buscaLista(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filmes/{titulo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Filme>> buscaFilmesPorTitulo(@PathVariable String titulo) {
        return new ResponseEntity<>(filmeService.buscaFilmesPorTitulo(titulo), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/filme", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaFilme(@RequestBody Filme filme) {
        return new ResponseEntity<>(filmeService.salva(filme, true), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/filme/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiFilme(@PathVariable long id) {
        return new ResponseEntity<>(filmeService.exclui(id, true), HttpStatus.OK);

    }
}