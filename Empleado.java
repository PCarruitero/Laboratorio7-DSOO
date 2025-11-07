public class Empleado extends Persona {

    private String idEmpleado;
    private String cargo;

    /**
     * Constructor vacío.
     */
    public Empleado() {}

    /**
     * Constructor completo para inicializar un Empleado.
     * Llama al constructor de la superclase Persona mediante 'super()'.
     */
    public Empleado(String dni, String nombre, String direccion, String telefono, String email,
                    String idEmpleado, String cargo) {

        super(dni, nombre, direccion, telefono, email); // Inicializa atributos heredados de Persona
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    // --- Getters y Setters específicos de Empleado ---
    public String getIdEmpleado() { return idEmpleado; }
    public String getCargo() { return cargo; }

    /**
     * Método de comportamiento: Registra (crea) un nuevo Depósito, asociándolo a la Cuenta y el Cliente.
     * El Empleado es el responsable de esta acción.
     */
    public Deposito registrarDeposito(Cuenta cuenta, float monto, Cliente cliente) {
        return new Deposito(cuenta, monto, cliente, this);
    }

    /**
     * Método de comportamiento: Registra (crea) un nuevo Retiro.
     * El Empleado es el responsable de esta acción.
     */
    public Retiro registrarRetiro(Cuenta cuenta, float monto, Cliente cliente) {
        return new Retiro(cuenta, monto, cliente, this);
    }
}