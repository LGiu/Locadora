package br.com.locadora.Util;


import br.com.locadora.Interface.Model;
import br.com.locadora.Service.LoginService;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.*;

public class ServiceGenerico<U extends Model> {

    private final JpaRepository<U, Long> jpaRepository;
    private final Class aClass;
    private final LoginService loginService;

    public ServiceGenerico(JpaRepository<U, Long> jpaRepository, Class aClass, LoginService loginService) {
        this.jpaRepository = jpaRepository;
        this.aClass = aClass;
        this.loginService = loginService;
    }

    public Retorno validador(U u) {
        if (u == null) {
            return new Retorno("Objeto nulo!");
        }

        if (u.getId() != null && !existe(u)) {
            return new Retorno("O registro não existe!");
        }

        BeanWrapper b = new BeanWrapperImpl(u);
        b.setAutoGrowNestedPaths(true);
        for (Field field : aClass.getDeclaredFields()) {
            if (field.getType() == Date.class && b.getPropertyValue(field.getName()) != null) {
                b.setPropertyValue(field.getName(), Datas.dataSerializada((Date) b.getPropertyValue(field.getName())));
            }
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

    public Retorno salva(U u, boolean verificaPermissao, String token) {
        if (verificaPermissao) {
            Retorno retorno = loginService.verificaLogin(token);
            if (retorno.isErro()) {
                return retorno;
            }
        }
        return salva(u);
    }

    public Retorno salva(U u) {
        return salva(u, true);
    }

    public Retorno salva(U u, boolean valida) {
        try {
            if (valida) {
                Retorno retorno = validador(u);
                if (retorno.isErro()) {
                    return retorno;
                }
            }

            u = jpaRepository.save(u);
            return new Retorno(u);
        } catch (Exception e) {
            if (e.getCause().getClass() == org.hibernate.exception.ConstraintViolationException.class) {
                UniqueConstraint[] un = u.getClass().getAnnotation(Table.class).uniqueConstraints();
                if (un.length > 0) {
                    return new Retorno<>("Os atributos " + Arrays.toString(un[0].columnNames()) + " devem ser únicos!");
                } else {
                    return new Retorno<>("Exitem atributos que devem ser únicos!");
                }
            } else {
                return new Retorno<>(e.getMessage());
            }
        }
    }

    public Retorno exclui(Long id, boolean verificaPermissao, String token) {
        if (verificaPermissao) {
            Retorno retorno = loginService.verificaLogin(token);
            if (retorno.isErro()) {
                return null;
            }
        }
        return exclui(id);
    }

    public Retorno exclui(Long id) {
        try {
            BeanWrapper b = new BeanWrapperImpl(aClass);
            b.setPropertyValue("id", id);
            U u = (U) b.getWrappedInstance();
            if (!existe(u)) {
                return new Retorno("Id informado não existe!");
            }
            jpaRepository.delete(u);
            return new Retorno<>(id);
        } catch (Exception e) {
            return new Retorno<>(e.getMessage());
        }
    }

    public U buscaPorId(Long id, boolean verificaPermissao, String token) {
        if (verificaPermissao) {
            Retorno retorno = loginService.verificaLogin(token);
            if (retorno.isErro()) {
                return null;
            }
        }
        U u = buscaPorId(id);
        if (u.getId() == null) {
            return null;
        }
        return u;
    }

    public U buscaPorId(Long id) {
        try {
            Optional<U> u = jpaRepository.findById(id);
            return u.get();
        } catch (Exception inored) {
            return null;
        }
    }

    public List<U> buscaLista(boolean verificaPermissao, String token) {
        if (verificaPermissao) {
            Retorno retorno = loginService.verificaLogin(token);
            if (retorno.isErro()) {
                return null;
            }
        }

        return buscaLista();
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
            if (u == null || u.getId() == null) {
                return false;
            }

            return jpaRepository.existsById(u.getId());
        } catch (Exception ignored) {
            return false;
        }
    }

    public Retorno validaToken(String token) {
        return loginService.verificaLogin(token);
    }


}
