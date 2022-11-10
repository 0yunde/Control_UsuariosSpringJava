$(document).ready(function() {
    //Inicio de sesión
});

async function iniciarSesion(){
    try {z
        let datos = {};
        datos.cuenta = document.getElementById('txt_cuenta').value ;
        datos.contrasena = document.getElementById('txt_contrasena1').value ;

        //Llamado al servidor, esperando  resultado
        const request = await fetch('api/iniciarSesion', {
            method: 'POST',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        const responseIngresar = await request.text();

        if (responseIngresar != 'FAIL') {
        localStorage.token = responseIngresar;
        localStorage.cuenta = datos.cuenta;
        window.location.href = 'tablaUsuario.html'
      } else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
      }

    } catch (error) {
      console.error(error);
    }
}
