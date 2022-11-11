package com.controlUsuario.cu.dao;

import com.controlUsuario.cu.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> obtenerUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

}
