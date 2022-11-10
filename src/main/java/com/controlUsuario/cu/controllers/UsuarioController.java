package com.controlUsuario.cu.controllers;

import com.controlUsuario.cu.dao.UsuarioDao;
import com.controlUsuario.cu.models.Usuario;
import com.controlUsuario.cu.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController  {

    //Hace que la clase usuarioDaoImp se cree un objeto y la guarde dentro de esta variable y
    //ademas de compartirlo en memoria para no crearlo reiteradas veces, conceppto de inyeccion de dependencias.
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;


    //Recibe parametro de autorizacion ya que si este esta vacio retornara error 400
    @RequestMapping(value = "api/usuariosLista" , method = RequestMethod.GET)
    public List<Usuario> usuariosLista(@RequestHeader(value="Authorization") String token) {
        System.out.println(token);
        if (!validarToken(token)) { return null; }

        return usuarioDao.obtenerUsuarios();
    }


    private boolean validarToken(String token) {
        //Extracion de ID de usaurio
        String usuarioId = jwtUtil.getKey(token);
        //Devuelve una lista vacia
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.DELETE)
    public void eliminar (@RequestHeader(value="Authorization") String token, @PathVariable Long id) {
        if (!validarToken(token)) { return; }
        usuarioDao.eliminar(id);

    }

    @RequestMapping(value = "api/registrarUsuario" , method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario) {

        //Procedimiento para utilizar argon he incriptacion de este
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        //Valor 1, cantidad de iteraciones para seguridad, Valor 2, uso de memoria interna, Valor 3, hilos de procesos para realizar el trabajo.
        //Valor 4, variable a la cual queremos hacer proceso HASH
        String hash = argon2.hash(1, 1024, 1, usuario.getContrasena());
        //Dar valor Hasheado a la contraseña
        usuario.setContrasena(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/actualizarUsuario", method = RequestMethod.PUT)
    public Usuario editar() {
        Usuario usuarioOb = new Usuario();

        usuarioOb.setNombres("Victor Alejandro");
        usuarioOb.setApellidos("Rojas Yovaniniz");
        usuarioOb.setEmail("virojas@acl.cl");
        usuarioOb.setTelefono(65656666);
        usuarioOb.setCuenta("oyunde");
        usuarioOb.setContrasena("123456");

        return usuarioOb;
    }






    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setNombres("Victor Alejandro");
        usuario.setApellidos("Rojas Yovaniniz");
        usuario.setEmail("virojas@acl.cl");
        usuario.setTelefono(65656666);
        usuario.setCuenta("oyunde");
        usuario.setContrasena("123456");

        return usuario;
    }





    @RequestMapping(value = "usuarioOb/123234")
    public Usuario buscar() {
        Usuario usuarioOb = new Usuario();

        usuarioOb.setNombres("Victor Alejandro");
        usuarioOb.setApellidos("Rojas Yovaniniz");
        usuarioOb.setEmail("virojas@acl.cl");
        usuarioOb.setTelefono(65656666);
        usuarioOb.setCuenta("oyunde");
        usuarioOb.setContrasena("123456");

        return usuarioOb;
    }

}
