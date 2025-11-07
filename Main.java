import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scannerConsola = new Scanner(System.in);
        Banco banco = new Banco(scannerConsola); // Banco fusionado (modelo + operaciones)

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
            System.out.println("8) Salir");

            opcionSeleccionada = Validador.leerOpcion(
                    scannerConsola, "Seleccione una opción [1-8]: ", 1, 8
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
                    System.out.println("Saliendo del sistema...");
                    break;
            }
        } while (opcionSeleccionada != 8);

        scannerConsola.close();
    }
}