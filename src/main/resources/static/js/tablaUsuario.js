$(document).ready(function() {
    //Carde usuarios
    cargarUsuarios();
    //Visualizacion de los datos en tabla
    $('#tablaUsuario').DataTable();
    //Actualizar Nombre de usuario en pagina
    nombreCuentaUsuario();
});

function nombreCuentaUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.cuenta;
}

async function cargarUsuarios(){
    try {
        //Llamado al servidor, esperando  resultado
        const request = await fetch('api/usuariosLista', {
            method: 'GET',
            headers: getHeaders()
        });

        const responseUsuariosLista = await request.json();

        //Variable para recorred cada usario que figurara en la tabla
        let listadoHTML = '';

        for (let usuario of responseUsuariosLista ) {

          let botonEliminar =  '<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

          let usuarioTr ='<tr><td>'
          +usuario.id+'</td><td>'
          +usuario.nombres+'</td><td>'
          +usuario.apellidos+'</td><td>'
          +usuario.email+'</td><td>'
          +usuario.telefono+'</td><td>'
          +usuario.cuenta+'</td><td>'
          +usuario.contrasena+'</td><td>'+botonEliminar+'</td></tr>';

          listadoHTML += usuarioTr;
        }

        document.querySelector('#tablaUsuario tbody').outerHTML = listadoHTML;

    } catch (error) {
      console.error(error);
    }
}

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}

async function eliminarUsuario(id){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }
    const request = await fetch('api/usuario/' + id, {
        method: 'DELETE',
        headers: getHeaders()
    });

    location.reload();
}
