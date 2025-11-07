import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerConsola = new Scanner(System.in);
        Banco banco = new Banco(scannerConsola);

        // Inicializar un empleado y un cliente fuera del main (por método aparte)
        inicializarDatos(banco);

        int opcionSeleccionada;
        do {
            System.out.println("\n========== MENÚ BANCO ==========");
            System.out.println("1) Registrar empleado");
            System.out.println("2) Registrar cliente");
            System.out.println("3) Crear cuenta");
            System.out.println("4) Depositar");
            System.out.println("5) Retirar");
            System.out.println("6) Ver saldo");
            System.out.println("7) Ver movimientos");
            System.out.println("8) Ver cliente");
            System.out.println("9) Salir");

            opcionSeleccionada = Validador.leerOpcion(
                    scannerConsola, "Seleccione una opción [1-9]: ", 1, 9
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
                    banco.verSaldo();
                    break;
                case 7:
                    banco.verMovimientos();
                    break;
                case 8:
                    banco.verCliente();
                    break;
                case 9:
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        } while (opcionSeleccionada != 9);

        scannerConsola.close();
    }

    // Método para crear un empleado y un cliente iniciales
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
