package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonafitApp();
//Analize each case
    }

    private static void zonafitApp(){//This handle the bucle
        var salir = false;
        var consola = new Scanner(System.in);
        //CREAMOS OBJETO DE LA CLASE CLIENTE DAO
        var clienteDao = new ClienteDAO();
        while(!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            }catch(Exception e ){
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }
    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** ZONA FIT (GYM)***
                1.Listar Cliente
                2.Buscar Cliente
                3.Agregar Cliente
                4.Modificar Cliente
                5.Eliminar Cliente
                6.Salir
                Elije una opcion:\s 
                """);
        return Integer.parseInt(consola.nextLine());
    }
    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch(opcion){
            case 1 -> {
                System.out.println("*** Listado de Clientes ***");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("*** Introduce id de cliente ***");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if(encontrado){
                    System.out.println("Cliente encontrado: " +  cliente);
                }else{
                    System.out.println("Cliente No Encontrado " + cliente);
                }
            }
            case 3 -> {
                System.out.println("*** Agregar Cliente ***");
                System.out.println("Ingrese nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Ingrese apellido");
                var apellido = consola.nextLine();
                System.out.println("Ingrese membresia");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado){
                    System.out.println("Cliente Agregado " + agregado);
                }else{
                    System.out.println("Cliente NO agregado");
                }
            }
            case 4 -> {
                System.out.println("*** Modificar cliente ***");
                System.out.println("Ingrese id de cliente");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.println("Nombre: ");
                var nombre = consola.nextLine();
                System.out.println("Apellido: ");
                var apellido = consola.nextLine();
                System.out.println("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());

                //CREACION DE OBJECTO A MODIFICAR
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if (modificado) {
                    System.out.println("Cliente modificado " + cliente);
                } else {
                    System.out.println("Cliente No modificado: " + cliente);
                }
            }
            case 5 -> {
                System.out.println("*** Eliminar Cliente ***");
                System.out.println("Id Cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if(eliminado){
                    System.out.println("Cliente Eliminado: " + cliente);
                }else{
                    System.out.println("Cliente No eliminado: " + cliente);
                }
            }
            case 6 -> {
                System.out.println("Hasta Pronto");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida: " + opcion);

        }

        return salir;
    }

}
