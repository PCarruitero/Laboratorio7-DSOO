import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Banco: Controla clientes, empleados y cuentas, y coordina operaciones bancarias.
 */
public class Banco {

    private final Scanner scannerConsola;
    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    private final ArrayList<Cuenta> listaCuentas = new ArrayList<>();

    /**
     * Constructor de la clase Banco.
     * @param scannerConsola Scanner para operaciones interactivas de consola.
     */
    public Banco(Scanner scannerConsola) {
        this.scannerConsola = scannerConsola;
    }

    // ============ REGISTROS ============

    /** Registra un nuevo empleado en el banco. */
    public void registrarEmpleado() {
        System.out.println("\n=== Registrar Empleado ===");
        String dniEmpleado = Validador.leerDNI(scannerConsola, "DNI (8 dígitos): ");
        String nombreEmpleado = Validador.leerNombre(scannerConsola, "Nombre completo: ");
        String telefonoEmpleado = Validador.leerTelefono(scannerConsola, "Teléfono: ");
        String correoEmpleado = Validador.leerEmail(scannerConsola, "Correo electrónico: ");
        String idEmpleado = Validador.leerIdCorto(scannerConsola, "ID empleado (ej. EMP01): ");
        String cargoEmpleado = Validador.leerCargo(scannerConsola, "Cargo: ");
        String direccionEmpleado = "-";
        Empleado nuevoEmpleado = new Empleado(
            dniEmpleado, nombreEmpleado, direccionEmpleado, telefonoEmpleado, correoEmpleado, idEmpleado, cargoEmpleado
        );
        listaEmpleados.add(nuevoEmpleado);
        System.out.println("Empleado registrado correctamente.");
    }

    /** Registra un nuevo cliente en el banco. */
    public void registrarCliente() {
        System.out.println("\n=== Registrar Cliente ===");
        String dniCliente = Validador.leerDNI(scannerConsola, "DNI (8 dígitos): ");
        String nombreCliente;
        do {
            nombreCliente = Validador.leerNombre(scannerConsola, "Nombre completo: ");
            if (!nombreCliente.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("El nombre no puede contener símbolos ni números. Intente nuevamente.");
            }
        } while (!nombreCliente.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+"));
        String telefonoCliente = Validador.leerTelefono(scannerConsola, "Teléfono: ");
        String correoCliente = Validador.leerEmail(scannerConsola, "Correo electrónico: ");
        String idCliente;
        do {
            idCliente = Validador.leerIdCorto(scannerConsola, "ID cliente (ej. CLI01): ");
            if (!idCliente.matches("[a-zA-Z0-9]+")) {
                System.out.println("El ID no puede contener símbolos. Intente nuevamente.");
            }
        } while (!idCliente.matches("[a-zA-Z0-9]+"));
        String direccionCliente = "—";
        Cliente nuevoCliente = new Cliente(
            dniCliente, nombreCliente, direccionCliente, telefonoCliente, correoCliente, idCliente
        );
        listaClientes.add(nuevoCliente);
        System.out.println("Cliente registrado correctamente.");
    }

    /** Crea una nueva cuenta y la asocia a un cliente existente. */
    public void crearCuenta() {
        System.out.println("\n=== Crear Cuenta ===");
        String numeroCuenta = Validador.leerNumeroCuenta(scannerConsola, "Nº de cuenta (10-20 dígitos): ");
        Cliente cliente = pedirClientePorIdODni("ID/DNI del titular: ");
        if (cliente == null) {
            System.out.println("El cliente no existe. Regístrelo primero.");
            return;
        }
        String tipoCuenta = Validador.leerTipoCuenta(scannerConsola, "Tipo (AHORRO / CTE): ");
        double saldoInicial = Validador.leerMontoPositivo(scannerConsola, "Saldo inicial: ");
        ArrayList<Cliente> titulares = new ArrayList<>();
        titulares.add(cliente);
        Cuenta nuevaCuenta = new Cuenta(numeroCuenta, tipoCuenta, saldoInicial, titulares);
        listaCuentas.add(nuevaCuenta);
        cliente.agregarCuenta(nuevaCuenta);
        System.out.println("Cuenta creada y asociada a " + cliente.getNombre());
    }

    // ============ OPERACIONES ============

    /** Realiza un depósito en una cuenta. */
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

    /** Realiza un retiro en una cuenta. */
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

    public void transferir() {
        System.out.println("\n=== Transferencia ===");
        Cuenta origen = pedirCuentaPorNumero();
        Cuenta destino = pedirCuentaPorNumero(); // Solicita dos veces: origen y destino
        if (origen == null || destino == null) return;

        float monto = (float) Validador.leerMontoPositivo(scannerConsola, "Monto a transferir: S/ ");
        Empleado empleadoAtendedor = pedirEmpleadoPorIdODni();
        if (empleadoAtendedor == null) return;  

        Cliente clienteTitular = pedirClientePorIdODni("ID/DNI del titular que transfiere: ");
        if (clienteTitular == null) return;

        Transferencia transferencia = new Transferencia(origen, destino, monto, clienteTitular, empleadoAtendedor);
        transferencia.procesar();
    }

    // ============ CONSULTAS ============

    /** Consulta el saldo de una cuenta por número. */
    public void verSaldo() {
        Cuenta cuenta = pedirCuentaPorNumero();
        if (cuenta == null) return;
        System.out.printf("Saldo actual: S/ %.2f%n", cuenta.getSaldo());
    }

    /** Muestra todos los movimientos registrados en una cuenta. */
    public void verMovimientos() {
        Cuenta cuenta = pedirCuentaPorNumero();
        if (cuenta == null) return;
        List<Transaccion> movimientos = cuenta.getMovimientos();
        if (movimientos == null || movimientos.isEmpty()) {
            System.out.println("No hay movimientos.");
            return;
        }
        System.out.println("---- MOVIMIENTOS ----");
        for (Transaccion transaccion : movimientos) {
            System.out.println(transaccion);
        }
    }

    /** Consulta y muestra los datos completos de un cliente. */
    public void verCliente() {
        System.out.println("\n=== Ver Cliente ===");
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        String idODni = Validador.leerIdCorto(scannerConsola, "Ingrese ID o DNI del cliente: ");
        Cliente cliente = buscarClientePorIdODni(idODni);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
        } else {
            System.out.println("----- Datos del cliente -----");
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("Correo: " + cliente.getEmail());
            System.out.println("ID: " + cliente.getIdCliente());
        }
    }

    // ============ HELPERS ============

    /** Solicita y busca una cuenta por número. */
    private Cuenta pedirCuentaPorNumero() {
        String numeroCuenta = Validador.leerNumeroCuenta(scannerConsola, "Nº de cuenta: ");
        Cuenta cuenta = buscarCuentaPorNumero(numeroCuenta);
        if (cuenta == null) System.out.println("La cuenta no existe.");
        return cuenta;
    }

    /** Solicita y busca un cliente por ID o DNI. */
    private Cliente pedirClientePorIdODni(String mensaje) {
        String idODniCliente = Validador.leerIdCorto(scannerConsola, mensaje);
        Cliente cliente = buscarClientePorIdODni(idODniCliente);
        if (cliente == null) System.out.println("Cliente no encontrado.");
        return cliente;
    }

    /** Solicita y busca un empleado por ID o DNI. */
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

    /** Busca un cliente por coincidencia de ID o DNI. */
    private Cliente buscarClientePorIdODni(String idODni) {
        for (Cliente cliente : listaClientes) {
            if (equalsIgnoreCaseSafe(idODni, cliente.getIdCliente()) ||
                equalsIgnoreCaseSafe(idODni, cliente.getDni())) {
                return cliente;
            }
        }
        return null;
    }

    /** Busca un empleado por coincidencia de ID o DNI. */
    private Empleado buscarEmpleadoPorIdODni(String idODni) {
        for (Empleado empleado : listaEmpleados) {
            if (equalsIgnoreCaseSafe(idODni, empleado.getIdEmpleado()) ||
                equalsIgnoreCaseSafe(idODni, empleado.getDni())) {
                return empleado;
            }
        }
        return null;
    }

    /** Elimina un cliente del banco por ID o DNI */
    public void eliminarCliente() {
        System.out.println("\n=== Eliminar Cliente ===");
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes para eliminar.");
            return;
        }
        String idODni = Validador.leerIdCorto(scannerConsola, "Ingrese ID o DNI del cliente a eliminar: ");
        Cliente cliente = buscarClientePorIdODni(idODni);
        if (cliente == null) {
            System.out.println("El cliente no fue encontrado.");
            return;
        }
        // Remover cuentas asociadas al cliente
        listaCuentas.removeIf(cuenta -> cuenta.getTitulares().contains(cliente));
        // Remover al cliente
        listaClientes.remove(cliente);
        System.out.println("Cliente y cuentas asociadas eliminados correctamente.");
    }

    /** Elimina una cuenta por número */
    public void eliminarCuenta() {
        System.out.println("\n=== Eliminar Cuenta ===");
        if (listaCuentas.isEmpty()) {
            System.out.println("No hay cuentas para eliminar.");
            return;
        }
        String numeroCuenta = Validador.leerNumeroCuenta(scannerConsola, "Ingrese el número de cuenta a eliminar: ");
        Cuenta cuenta = buscarCuentaPorNumero(numeroCuenta);
        if (cuenta == null) {
            System.out.println("Cuenta no encontrada.");
            return;
        }
        // Remueve la cuenta de sus titulares también
        for (Cliente titular : cuenta.getTitulares()) {
            titular.getCuentas().remove(cuenta);
        }
        listaCuentas.remove(cuenta);
        System.out.println("Cuenta eliminada correctamente.");
    }

    /** Elimina un empleado del banco por ID o DNI */
    public void eliminarEmpleado() {
        System.out.println("\n=== Eliminar Empleado ===");
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados para eliminar.");
            return;
        }
        String idODni = Validador.leerIdCorto(scannerConsola, "Ingrese ID o DNI del empleado a eliminar: ");
        Empleado empleado = buscarEmpleadoPorIdODni(idODni);
        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        listaEmpleados.remove(empleado);
        System.out.println("Empleado eliminado correctamente.");
    }


    /** Busca una cuenta por número. */
    private Cuenta buscarCuentaPorNumero(String numeroCuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (equalsIgnoreCaseSafe(numeroCuenta, cuenta.getNumero())) return cuenta;
        }
        return null;
    }

    /** Utilidad para comparación segura de cadenas ignorando mayúsculas/minúsculas. */
    private static boolean equalsIgnoreCaseSafe(String a, String b) {
        return a != null && b != null && a.equalsIgnoreCase(b);
    }

    // Getters públicos para listas principales
    public ArrayList<Cliente> getClientes() { return listaClientes; }
    public ArrayList<Empleado> getEmpleados() { return listaEmpleados; }
    public ArrayList<Cuenta> getCuentas() { return listaCuentas; }
}
