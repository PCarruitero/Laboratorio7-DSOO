import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ==========================================
        //   CREAR OBJETOS BASE DEL SISTEMA
        // ==========================================

        Banco banco = new Banco();

        // Empleado del banco
        Empleado emp = new Empleado(
                "12345678",
                "Carlos López",
                "Av. Lima 101",
                "99999999",
                "empleado@banco.com",
                "EMP01",
                "Cajero"
        );
        banco.registrarEmpleado(emp);

        // Cliente del banco
        Cliente cliente = new Cliente(
                "87654321",
                "Brayan Motta",
                "Cayma - Arequipa",
                "912345678",
                "brayan@gmail.com",
                "CLI01"
        );
        banco.registrarCliente(cliente);

        // Crear cuenta bancaria para el cliente
        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(cliente);
        Cuenta cuenta = banco.crearCuenta(titulares, 500.00f); // saldo inicial
        cliente.agregarCuenta(cuenta);

        int opcion;

        // ==========================================
        //                MENÚ
        // ==========================================
        do {
            System.out.println("\n-------------------------------------");
            System.out.println("          SISTEMA DEL BANCO");
            System.out.println("-------------------------------------");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar retiro");
            System.out.println("4. Ver movimientos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1: // CONSULTAR SALDO
                    System.out.println("\n--- CONSULTA DE SALDO ---");
                    System.out.println("Saldo actual: S/ " + cuenta.getSaldo());
                    break;

                case 2: // DEPÓSITO
                    System.out.println("\n--- DEPÓSITO ---");
                    System.out.print("Ingrese monto: ");
                    float montoDep = sc.nextFloat();

                    Deposito dep = emp.registrarDeposito(cuenta, montoDep, cliente);
                    dep.procesar();
                    break;

                case 3: // RETIRO
                    System.out.println("\n--- RETIRO ---");
                    System.out.print("Ingrese monto: ");
                    float montoRet = sc.nextFloat();

                    Retiro ret = emp.registrarRetiro(cuenta, montoRet, cliente);
                    ret.procesar();
                    break;

                case 4: // MOVIMIENTOS
                    System.out.println("\n--- MOVIMIENTOS ---");

                    ArrayList<Transaccion> movs = cuenta.listarMovimientos();

                    if (movs.isEmpty()) {
                        System.out.println("No hay movimientos.");
                    } else {
                        for (Transaccion transaccion : movs) {
                            System.out.println(transaccion);
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }
}
