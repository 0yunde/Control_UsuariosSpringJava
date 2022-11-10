package com.controlUsuario.cu.controllers;

import com.controlUsuario.cu.dao.UsuarioDao;
import com.controlUsuario.cu.models.Usuario;
import com.controlUsuario.cu.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;
    //Se comparte en todos los lugares del proyecto y permite las anotaciones de values de la informacion de las properties
    //Inyeccción de dependencias
    @Autowired
    private JWTUtil jwtUtil;


    //Recibe cuenta y contraseña respecto al usuario, parte del servidor
    @RequestMapping(value = "api/iniciarSesion" , method = RequestMethod.POST)
    public String ingresar(@RequestBody Usuario usuario) {

        //Verifica credenciales
        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (usuarioLogueado != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getId()), usuarioLogueado.getCuenta());
            return tokenJwt;
        }
        return "FAIL";
    }
}
