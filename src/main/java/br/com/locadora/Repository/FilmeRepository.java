package br.com.locadora.Repository;

import br.com.locadora.Model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findFilmeByTituloLike(String titulo);
}

