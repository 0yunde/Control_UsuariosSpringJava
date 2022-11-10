$(document).ready(function() {
    //Registrar usuario
});

async function registrarUsuario(){
    try {
        let datos = {};
        datos.nombres = document.getElementById('txt_nombres').value ;
        datos.apellidos = document.getElementById('txt_apellidos').value ;
        datos.email = document.getElementById('txt_email').value ;
        datos.telefono = document.getElementById('txt_telefono').value ;
        datos.cuenta = document.getElementById('txt_cuenta').value ;
        datos.contrasena = document.getElementById('txt_contrasena1').value ;

        let repetirContrasena = document.getElementById('txt_contrasena2').value ;
        if(repetirContrasena != datos.contrasena){
            alert('La contraseña que escribiste es diferente.');
            return;
        }


        //Llamado al servidor, esperando  resultado
        const request = await fetch('api/registrarUsuario', {
            method: 'POST',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        alert("La cuenta fue creada con exito!");
        window.location.href = 'ingresar.html';

    } catch (error) {
      console.error(error);
    }
}




