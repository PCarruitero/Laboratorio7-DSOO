import java.time.LocalDateTime;

/**
 * Clase Transaccion
 * Clase base para operaciones bancarias (Depósito, Retiro, etc.).
 * Permite herencia y asociación con otras entidades clave.
 */
public class Transaccion {

    // Atributos protegidos permiten acceso limitado en subclases (Deposito, Retiro)
    protected String id;
    protected LocalDateTime fechaHora;
    protected float monto;
    protected Empleado empleado; // Asociación 1:1 (o 0:1) con Empleado
    protected Cuenta cuenta;     // Asociación 1:1 con Cuenta
    protected Cliente cliente;   // Asociación 1:1 con Cliente

    /**
     * Constructor vacío. Inicializa fechaHora con el momento actual.
     */
    public Transaccion() {
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Constructor completo para inicializar todos los datos de la transacción.
     * @param id        Identificador único de la transacción.
     * @param fechaHora Fecha y hora de registro.
     * @param monto     Monto operado.
     * @param empleado  Empleado responsable de la operación.
     * @param cuenta    Cuenta involucrada.
     * @param cliente   Cliente asociado a la operación.
     */
    public Transaccion(String id, LocalDateTime fechaHora, float monto,
                       Empleado empleado, Cuenta cuenta, Cliente cliente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.empleado = empleado;
        this.cuenta = cuenta;
        this.cliente = cliente;
    }

    // --- Métodos Getters ---
    public String getId()                 { return id; }
    public LocalDateTime getFechaHora()   { return fechaHora; }
    public float getMonto()               { return monto; }
    public Empleado getEmpleado()         { return empleado; }
    public Cuenta getCuenta()             { return cuenta; }
    public Cliente getCliente()           { return cliente; }

    /**
     * Genera un resumen legible de la transacción.
     * @return Texto descriptivo de todos los datos relevantes.
     */
    @Override
    public String toString() {
        return "ID: " + id +
               "\nFecha: " + fechaHora +
               "\nMonto: S/ " + monto +
               "\nEmpleado: " + (empleado != null ? empleado.getNombre() : "No Aplica") +
               "\nCliente: "   + (cliente != null ? cliente.getNombre() : "No Aplica") +
               "\nCuenta: "    + (cuenta != null ? cuenta.getNumero() : "No Aplica");
    }

    /**
     * Método base para procesamiento de la transacción.
     * Subclases (Depósito, Retiro) deben sobreescribir según su lógica.
     */
    public void procesar() {
        // Implementación base vacía: cada tipo de transacción define su propio procesamiento.
    }
}
