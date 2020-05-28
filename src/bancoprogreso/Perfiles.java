/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancoprogreso;

/**
 *
 * @author {Cristian Aragon, Sergio Correa, Nicolas Martinez, Carlos Ortigoza}
 */
public class Perfiles {

    private final Acciones accion;

    public Perfiles() {
        accion = new Acciones();

        /**
         *
         * inicializamos con los colaboradores del banco perfiles: perfile de la
         * aplicacion: administrador,funcionario, cliente
         */
        String[] admin = { "Sergio", "Correa", "1", "0", "2020-30-12", "0" };
        accion.crearUsuarios(admin);

        String[] funcionario = { "Carlos", "ortigoza", "0", "500000", "2020-30-12", "1" };
        accion.crearUsuarios(funcionario);

        String[] cliente = { "Nicolas", "Martinez", "2", "500000", "2020-30-12", "2" };
        accion.crearUsuarios(cliente);
    }

    /*
     *
     * esta funcion publica tendra la logica de las acciones que se tendra perfil
     * administrador el administrador tiene todos los privilegios dentro de la
     * aplicacion
     */
    public void admin(String cedula, int perfil) {
        boolean seguirExec;
        do {
            seguirExec = true;
            switch (this.panelAcciones(perfil)) {

                // Metodo funciona correctamente
                case "Registrar cliente":
                    accion.crearUsuarios(Utilitarios.formularioUsuario());
                    Utilitarios.mostrar("Usuario creado", Boolean.TRUE);
                    seguirExec = true;
                    break;

                /**
                 * 
                 * Listar los clientes ordenados de mayor a menor por cedula Metodo funciona
                 * correctamente
                 */
                case "Listar clientes":
                    Utilitarios.mostrar(accion.mostrarUsuarios(), Boolean.TRUE);
                    seguirExec = true;
                    break;

                // Metodo funciona correctamente
                case "Buscar cliente":
                    Utilitarios.mostrar(accion.buscarCliente(), Boolean.TRUE);
                    seguirExec = true;
                    break;

                // Metodo funciona correctamente
                case "Consultar saldos":
                    Utilitarios.mostrar(accion.sumaSaldos(), Boolean.TRUE);
                    seguirExec = true;
                    break;

                default:
                    // Por defecto saldra si no tiene alguna de la opciones "salir"
                    Utilitarios.mostrar("Gracias por usar nuestra aplicacion", Boolean.TRUE);
                    seguirExec = false;
                    break;
            }
        } while (seguirExec);
    }

    /*
     *
     * esta funcion publica tendra la logica de las acciones que se tendra perfil
     * funcionario funcionario tendra las opciones de poder registrar , ver la info
     * de un cliente perfil 2
     */
    public void funcionario(String cedula, int perfil) {
        boolean seguirExec;
        do {
            seguirExec = true;
            switch (this.panelAcciones(perfil)) {

                // Metodo funciona correctamente
                case "Registrar cliente":
                    accion.crearUsuarios(Utilitarios.formularioUsuario());
                    Utilitarios.mostrar("Usuario creado", Boolean.TRUE);
                    seguirExec = true;
                    break;

                // Buscar cliente
                case "Buscar cliente":
                    Utilitarios.mostrar(accion.buscarCliente(), Boolean.TRUE);
                    seguirExec = true;
                    break;

                default:
                    // Por defecto saldra si no tiene alguna de la opciones "salir"
                    seguirExec = false;
                    Utilitarios.mostrar("Gracias por usar nuestra aplicacion", Boolean.TRUE);
                    break;
            }
        } while (seguirExec);
    }

    /*
     *
     * esta funcion publica tendra la logica de las acciones que se tendra perfil
     * clientes cliente podra ver su informacion personal, y sus saldos
     */
    public void cliente(String cedula, int perfil) {
        boolean seguirExec;
        do {
            seguirExec = true;
            switch (this.panelAcciones(perfil)) {

                // Metodo funcionando perfectamente
                case "Consulta tu información":
                    Utilitarios.mostrar(accion.mostrarInformacion(cedula), Boolean.TRUE);
                    seguirExec = true;
                    break;

                case "Realiza abono":
                    String saldoActual = accion.obtenerSaldoActual(cedula);
                    accion.debitarAbono(Utilitarios.abonos(saldoActual), cedula);
                    seguirExec = true;
                    break;

                default:
                    // Por defecto saldra si no tiene alguna de la opciones "salir"
                    seguirExec = false;
                    Utilitarios.mostrar("Gracias por usar nuestra aplicacion", Boolean.TRUE);
                    break;
            }
        } while (seguirExec);
    }

    /*
     *
     * metodo para buscar dentro de array clientes dependiendo si existe devolvera
     * el perfil del usuario en caso de no existir retornara 3 que es un valor que
     * no existe en perfiles
     *
     */
    public int buscarUsuario(String cedula) {
        int perfil = 3;
        for (int i = 0; i < accion.getClientes().length - 1; i++) {
            for (int j = 0; j < accion.getClientes()[i].length; j++) {
                if (accion.getClientes()[i][2].equals(cedula)) {
                    perfil = Integer.parseInt(accion.getClientes()[i][5]);
                }
            }
        }
        return perfil;
    }

    /*
     *
     * metodo privado, por perfil mostrara las opciones disponibles estas se
     * mostraran de acuerdo al perfil
     * 
     * @params object opcionesCliente menu disponible por perfil
     *
     */
    private String panelAcciones(int perfil) {
        switch (perfil) {
            case 0:
                Object[] opcionesAdmin = { "Registrar cliente", "Listar clientes", "Buscar cliente", "Consultar saldos",
                        "Salir" };
                return Utilitarios.menuMostrar("Bienvenido Administrador\n Selecciona una opción", opcionesAdmin);
            case 1:
                Object[] opcionesFuncionario = { "Registrar cliente", "Buscar cliente", "Salir" };
                return Utilitarios.menuMostrar("Bienvenido Funcionario\n Selecciona una opción", opcionesFuncionario);

            case 2:
                Object[] opcionesCliente = { "Consulta tu información", "Realiza abono", "Salir" };
                return Utilitarios.menuMostrar("Bienvenido cliente\n Selecciona una opción", opcionesCliente);

        }
        return "Opción no válida";
    }

}
