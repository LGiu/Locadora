package br.com.locadora.Controller;

import br.com.locadora.Model.Usuario;
import br.com.locadora.Service.UsuarioService;
import br.com.locadora.Util.Retorno;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> buscaUsuario(@PathVariable long id) {
        return new ResponseEntity<>(usuarioService.buscaPorId(id, true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> salvaUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.salva(usuario, true), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Retorno> excluiUsuario(@PathVariable long id) {
        return new ResponseEntity<>(usuarioService.exclui(id, true), HttpStatus.OK);
    }
}