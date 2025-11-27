import java.util.Scanner;

/**
 * Clase Main
 * Punto de partida del sistema bancario. Controla el menú y flujo principal.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scannerConsola = new Scanner(System.in);
        Banco banco = new Banco(scannerConsola);

        // Inicializa datos básicos (empleado y cliente de prueba)
        inicializarDatos(banco);

        int opcionSeleccionada;
        do {
            System.out.println("\n========== MENÚ BANCO ==========");
            System.out.println("1) Registrar empleado");
            System.out.println("2) Registrar cliente");
            System.out.println("3) Crear cuenta");
            System.out.println("4) Depositar");
            System.out.println("5) Retirar");
            System.out.println("6) Transferir");
            System.out.println("7) Ver saldo");
            System.out.println("8) Eliminar cliente");
            System.out.println("9) Eliminar cuenta");
            System.out.println("10) Eliminar empleado");
            System.out.println("11) Ver movimientos");
            System.out.println("12) Ver cliente");
            System.out.println("13) Salir");

            // Valida la opción de menú entre 1 y 13
            opcionSeleccionada = Validador.leerOpcion(
                scannerConsola,
                "Seleccione una opción [1-13]: ",
                1, 13
            );

            switch (opcionSeleccionada) {
                case 1:
                    banco.registrarEmpleado();
                    break;
                case 2:
                    banco.registrarCliente();
                    break;
                case 3:
                    banco.crearCuenta();
                    break;
                case 4:
                    banco.depositar();
                    break;
                case 5:
                    banco.retirar();
                    break;
                case 6:
                    banco.transferir();
                    break;
                case 7:
                    banco.verSaldo();
                    break;
                case 8: 
                    banco.eliminarCliente(); 
                    break;
                case 9:
                    banco.eliminarCuenta(); 
                    break;
                case 10:
                    banco.eliminarEmpleado(); 
                    break;
                case 11:
                    banco.verMovimientos();
                    break;
                case 12:
                    banco.verCliente();
                    break;
                case 13:
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        } while (opcionSeleccionada != 13);

        scannerConsola.close();
    }

    /**
     * Método para inicializar datos de ejemplo: agrega un empleado y un cliente iniciales.
     * @param banco Banco donde agregar los registros básicos.
     */
    private static void inicializarDatos(Banco banco) {
        try {
            Empleado empleadoInicial = new Empleado(
                "12345678", "Carlos Ruiz", "-", "999888777", "carlos@banco.com",
                "EMP01", "Gerente"
            );
            Cliente clienteInicial = new Cliente(
                "87654321", "Ana Torres", "-", "987654321", "ana@correo.com", "CLI01"
            );
            banco.getEmpleados().add(empleadoInicial);
            banco.getClientes().add(clienteInicial);
            System.out.println("Datos iniciales cargados correctamente.");
        } catch (Throwable e) {
            System.out.println("No se pudieron cargar los datos iniciales.");
        }
    }
}
