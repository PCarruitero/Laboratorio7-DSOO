/**
 * Clase Empleado
 * Representa a un empleado bancario, que extiende Persona
 * y puede realizar operaciones como depósitos y retiros.
 */
public class Empleado extends Persona {

    // Identificador único del empleado
    private String idEmpleado;

    // Cargo (rol) dentro del banco
    private String cargo;

    /**
     * Constructor vacío requerido por algunos frameworks y por flexibilidad.
     */
    public Empleado() {}

    /**
     * Constructor completo para inicializar un Empleado.
     * Llama al constructor de la superclase Persona.
     * @param dni        Documento nacional de identidad del empleado.
     * @param nombre     Nombre completo del empleado.
     * @param direccion  Dirección física.
     * @param telefono   Teléfono de contacto.
     * @param email      Dirección de correo electrónico.
     * @param idEmpleado Identificador único para el empleado.
     * @param cargo      Cargo (rol) dentro del banco.
     */
    public Empleado(String dni, String nombre, String direccion,
                    String telefono, String email, String idEmpleado, String cargo) {
        super(dni, nombre, direccion, telefono, email); // Inicializa atributos heredados de Persona
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    // --- Getters y Setters específicos de Empleado ---

    /** @return El ID del empleado */
    public String getIdEmpleado() { return idEmpleado; }

    /** @return Cargo/rol del empleado */
    public String getCargo() { return cargo; }

    /** @param cargo Nuevo valor para el cargo */
    public void setCargo(String cargo) { this.cargo = cargo; }

    /**
     * Registra (crea) un nuevo Depósito, asociándolo a la Cuenta y el Cliente.
     * El Empleado es el responsable de esta acción.
     * @param cuenta  Cuenta destino del depósito.
     * @param monto   Monto a depositar.
     * @param cliente Cliente que solicita el depósito.
     * @return Objeto Deposito listo para procesarse.
     */
    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        return new Deposito(cuenta, monto, cliente, this);
    }

    /**
     * Registra (crea) un nuevo Retiro, asociándolo a la Cuenta y el Cliente.
     * El Empleado es el responsable de esta acción.
     * @param cuenta  Cuenta origen del retiro.
     * @param monto   Monto a retirar.
     * @param cliente Cliente que solicita el retiro.
     * @return Objeto Retiro listo para procesarse.
     */
    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        return new Retiro(cuenta, monto, cliente, this);
    }
}
