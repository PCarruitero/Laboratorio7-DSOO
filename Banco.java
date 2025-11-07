import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    // === Estado del sistema (modelo) ===
    private final Scanner scannerConsola;
    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    private final ArrayList<Cuenta>   listaCuentas   = new ArrayList<>();

    public Banco(Scanner scannerConsola) {
        this.scannerConsola = scannerConsola;
    }

    // ============ REGISTROS ============
    public void registrarEmpleado() {
        System.out.println("\n=== Registrar Empleado ===");
        String dniEmpleado      = Validador.leerDNI(scannerConsola,     "DNI (8 dígitos): ");
        String nombreEmpleado   = Validador.leerNombre(scannerConsola,  "Nombre completo: ");
        String telefonoEmpleado = Validador.leerTelefono(scannerConsola,"Teléfono: ");
        String correoEmpleado   = Validador.leerEmail(scannerConsola,   "Correo electrónico: ");
        String idEmpleado       = Validador.leerIdCorto(scannerConsola, "ID empleado (ej. EMP01): ");
        String cargoEmpleado    = Validador.leerCargo(scannerConsola,   "Cargo: ");
        String direccionEmpleado = "-"; // mínimo para constructor

        Empleado nuevoEmpleado = new Empleado(
                dniEmpleado, nombreEmpleado, direccionEmpleado, telefonoEmpleado, correoEmpleado,
                idEmpleado, cargoEmpleado
        );
        listaEmpleados.add(nuevoEmpleado);
        System.out.println("Empleado registrado correctamente.");
    }

    public void registrarCliente() {
        System.out.println("\n=== Registrar Cliente ===");
        String dniCliente      = Validador.leerDNI(scannerConsola,      "DNI (8 dígitos): ");
        String nombreCliente   = Validador.leerNombre(scannerConsola,   "Nombre completo: ");
        String telefonoCliente = Validador.leerTelefono(scannerConsola, "Teléfono: ");
        String correoCliente   = Validador.leerEmail(scannerConsola,    "Correo electrónico: ");
        String idCliente       = Validador.leerIdCorto(scannerConsola,  "ID cliente (ej. CLI01): ");
        String direccionCliente = "—";

        Cliente nuevoCliente = new Cliente(
                dniCliente, nombreCliente, direccionCliente, telefonoCliente, correoCliente, idCliente
        );
        listaClientes.add(nuevoCliente);
        System.out.println("Cliente registrado correctamente.");
    }

    public void crearCuenta() {
        System.out.println("\n=== Crear Cuenta ===");
        // número de cuenta ingresado (validado)
        String numeroCuenta = Validador.leerNumeroCuenta(scannerConsola, "Nº de cuenta (10-20 dígitos): ");

        // titular principal
        String idODniTitular = Validador.leerIdCorto(scannerConsola, "ID/DNI del titular: ");
        Cliente titular = buscarClientePorIdODni(idODniTitular);
        if (titular == null) {
            System.out.println("El cliente no existe. Regístrelo primero.");
            return;
        }

        ArrayList<Cliente> titularesCuenta = new ArrayList<>();
        titularesCuenta.add(titular);

        // tipo mínimo fijo (simple) para mantenerlo esencial
        String tipoCuenta = "Ahorros";
        double saldoInicial = 0.0;

        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, tipoCuenta, saldoInicial, titularesCuenta);
        listaCuentas.add(nuevaCuenta);
        System.out.println("Cuenta creada. Nº: " + nuevaCuenta.getNumero());
    }

    // ============ OPERACIONES ============
    public void depositar() {
        System.out.println("\n=== Depósito ===");
        Cuenta cuentaDestino = pedirCuentaPorNumero();
        if (cuentaDestino == null) return;

        double montoDeposito = Validador.leerMontoPositivo(scannerConsola, "Monto a depositar: S/ ");
        Empleado empleadoAtendedor = pedirEmpleadoPorIdODni();
        if (empleadoAtendedor == null) return;

        Cliente clienteDepositante = pedirClientePorIdODni("ID/DNI del cliente que deposita: ");
        if (clienteDepositante == null) return;

        Deposito deposito = empleadoAtendedor.registrarDeposito(cuentaDestino, (float) montoDeposito, clienteDepositante);
        deposito.procesar();
        System.out.printf("Depósito realizado. Saldo actual: S/ %.2f%n", cuentaDestino.getSaldo());
    }

    public void retirar() {
        System.out.println("\n=== Retiro ===");
        Cuenta cuentaOrigen = pedirCuentaPorNumero();
        if (cuentaOrigen == null) return;

        double montoRetiro = Validador.leerMontoPositivo(scannerConsola, "Monto a retirar: S/ ");
        Empleado empleadoAtendedor = pedirEmpleadoPorIdODni();
        if (empleadoAtendedor == null) return;

        Cliente clienteTitular = pedirClientePorIdODni("ID/DNI del titular que retira: ");
        if (clienteTitular == null) return;

        Retiro retiro = empleadoAtendedor.registrarRetiro(cuentaOrigen, (float) montoRetiro, clienteTitular);
        retiro.procesar();
        System.out.printf("Retiro procesado. Saldo actual: S/ %.2f%n", cuentaOrigen.getSaldo());
    }

    // ============ CONSULTAS ============
    public void verSaldo() {
        Cuenta cuenta = pedirCuentaPorNumero();
        if (cuenta == null) return;
        System.out.printf("Saldo actual: S/ %.2f%n", cuenta.getSaldo());
    }

    public void verMovimientos() {
        Cuenta cuenta = pedirCuentaPorNumero();
        if (cuenta == null) return;

        List<Transaccion> movimientos;
        try {
            movimientos = cuenta.getMovimientos();     // si tu clase Cuenta expone este método
        } catch (Throwable e) {
            movimientos = cuenta.listarMovimientos();  // o este otro
        }

        if (movimientos == null || movimientos.isEmpty()) {
            System.out.println("No hay movimientos.");
            return;
        }
        System.out.println("---- MOVIMIENTOS ----");
        for (Transaccion transaccion : movimientos) {
            System.out.println(transaccion);
        }
    }

    // ============ HELPERS ============
    private Cuenta pedirCuentaPorNumero() {
        String numeroCuenta = Validador.leerNumeroCuenta(scannerConsola, "Nº de cuenta: ");
        Cuenta cuenta = buscarCuentaPorNumero(numeroCuenta);
        if (cuenta == null) System.out.println("La cuenta no existe.");
        return cuenta;
    }

    private Cliente pedirClientePorIdODni(String mensaje) {
        String idODniCliente = Validador.leerIdCorto(scannerConsola, mensaje);
        Cliente cliente = buscarClientePorIdODni(idODniCliente);
        if (cliente == null) System.out.println("Cliente no encontrado.");
        return cliente;
    }

    private Empleado pedirEmpleadoPorIdODni() {
        String idODniEmpleado = Validador.leerIdCorto(scannerConsola, "ID/DNI del empleado que atiende: ");
        Empleado empleado = buscarEmpleadoPorIdODni(idODniEmpleado);
        if (empleado == null) {
            if (!listaEmpleados.isEmpty()) {
                Empleado empleadoFallback = listaEmpleados.get(0);
                System.out.println("No hallado. Usando primer empleado: " + empleadoFallback.getIdEmpleado());
                return empleadoFallback;
            }
            System.out.println("No hay empleados registrados.");
        }
        return empleado;
    }

    private Cliente buscarClientePorIdODni(String idODni) {
        for (Cliente cliente : listaClientes) {
            if (equalsIgnoreCaseSafe(idODni, cliente.getIdCliente()) ||
                equalsIgnoreCaseSafe(idODni, cliente.getDni())) {
                return cliente;
            }
        }
        return null;
    }

    private Empleado buscarEmpleadoPorIdODni(String idODni) {
        for (Empleado empleado : listaEmpleados) {
            if (equalsIgnoreCaseSafe(idODni, empleado.getIdEmpleado()) ||
                equalsIgnoreCaseSafe(idODni, empleado.getDni())) {
                return empleado;
            }
        }
        return null;
    }

    private Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (equalsIgnoreCaseSafe(numeroCuenta, cuenta.getNumero())) return cuenta;
        }
        return null;
    }

    private static boolean equalsIgnoreCaseSafe(String a, String b) {
        return a != null && b != null && a.equalsIgnoreCase(b);
    }

    public ArrayList<Cliente> getClientes() { return listaClientes; }
    public ArrayList<Empleado> getEmpleados() { return listaEmpleados; }
    public ArrayList<Cuenta>   getCuentas()   { return listaCuentas; }
}