package br.com.locadora.Repository;

import br.com.locadora.Model.Login;
import br.com.locadora.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Login findByChave(String chave);

    Login findByChaveAndDataLogoffIsNull(String chave);

    Login findByUsuarioAndDataLogoffIsNull(Usuario usuario);
}

