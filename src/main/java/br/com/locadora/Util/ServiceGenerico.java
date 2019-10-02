package br.com.locadora.Util;


import br.com.locadora.Interface.Model;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServiceGenerico<U extends Model> {

    private final JpaRepository<U, Long> jpaRepository;
    private final Class aClass;

    public ServiceGenerico(JpaRepository<U, Long> jpaRepository, Class aClass) {
        this.jpaRepository = jpaRepository;
        this.aClass = aClass;
    }

    public Retorno validador(U u) {
        if (u == null) {
            return new Retorno("Objeto nulo!");
        }

        if (u.getId() != null && !existe(u)) {
            return new Retorno("O registro não existe!!");
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<U>> violations = validator.validate(u);
        List<String> erros = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<U> erro : violations) {
                erros.add(u.getClass().getSimpleName() + "." + erro.getPropertyPath() + ":" + erro.getMessage());
            }
            return new Retorno(erros);
        }

        return new Retorno();
    }

    public Retorno salva(U u, boolean verificaPermissao) {
        if (verificaPermissao) {

        }
        return salva(u);
    }

    public Retorno salva(U u) {
        try {
            Retorno retorno = validador(u);
            if (retorno.isErro()) {
                return retorno;
            }
            u = jpaRepository.save(u);
            return new Retorno(u);
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

    public Retorno exclui(Long id, boolean verificaPermissao) {
        if (verificaPermissao) {

        }
        return exclui(id);
    }

    public Retorno exclui(Long id) {
        try {
            BeanWrapper b = new BeanWrapperImpl(aClass);
            b.setPropertyValue("id", id);
            jpaRepository.delete((U) b.getWrappedInstance());
            return new Retorno<>(id);
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

    public U buscaPorId(Long id, boolean verificaPermissao) {
        if (verificaPermissao) {

        }
        return buscaPorId(id);
    }

    public U buscaPorId(Long id) {
        try {
            return jpaRepository.getOne(id);
        } catch (Exception inored) {
            return null;
        }
    }

    public List<U> buscaLista() {
        try {
            return jpaRepository.findAll();
        } catch (Exception inored) {
            return null;
        }
    }

    public boolean existe(U u) {
        try {
            return jpaRepository.existsById(u.getId());
        } catch (Exception ignored) {
            return false;
        }
    }
}
