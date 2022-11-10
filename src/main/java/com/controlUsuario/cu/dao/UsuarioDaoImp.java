package com.controlUsuario.cu.dao;

import com.controlUsuario.cu.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository // Funcionalidad para conexion base de datos
@Transactional //Funcionalidad para armar consultas sql
public class UsuarioDaoImp implements UsuarioDao{


    @PersistenceContext //Realiza los cambios que se han realizado sobre las entidades en la base de datos
    private EntityManager entityManager;//Conexion de base de datos


    @Override
    @Transactional
    public List<Usuario> obtenerUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE cuenta = :cuenta";
         List<Usuario> listaRs = entityManager.createQuery(query)
                .setParameter("cuenta", usuario.getCuenta())
                 .getResultList();

         //Suceptible a null pointer exepcion
         //Si el nombre de la cuenta no existe en base de datos, en la lista volvera vacia
         //Al tratar de obtener el primer valor devolvera null, y si se intenta obtener la contraseña se efectua este nullpointer exepcion
         //Control del error
        if(listaRs.isEmpty()){
            return null;
        }

         String contrasenaHasheada = listaRs.get(0).getContrasena();

         //Procedimiento para utilizar argon he incriptacion de este
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //Verifica por medio de verificacion la contraseña hasheada en base de datos y la que se entrega bajo boolean
        if( argon2.verify(contrasenaHasheada, usuario.getContrasena().toCharArray())){
            return listaRs.get(0);
        }
        return null;

    }

}
