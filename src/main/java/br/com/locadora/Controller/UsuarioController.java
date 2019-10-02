package br.com.locadora.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    /*private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario buscaUsuario(@PathVariable long id) {
        return usuarioService.buscaPorId(id, true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
    public Retorno salvaUsuario(@RequestBody Usuario usuario){
        return usuarioService.salva(usuario, true);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Retorno excluiUsuario(@PathVariable long id) {
        return usuarioService.exclui(id, true);
    }*/
}