import java.time.LocalDateTime;

public class Transaccion {

    // Atributos protegidos: Permiten el acceso directo desde las subclases (Deposito, Retiro)
    protected String id;
    protected LocalDateTime fechaHora;
    protected float monto;
    protected Empleado empleado; // Asociación 1:1 (o 0:1) con Empleado
    protected Cuenta cuenta;     // Asociación 1:1 con Cuenta
    protected Cliente cliente;   // Asociación 1:1 con Cliente

    /**
     * Constructor vacío. Inicializa la fechaHora con el momento actual.
     */
    public Transaccion() {
        this.fechaHora = LocalDateTime.now();
    }

    /**
     * Constructor completo para inicializar todos los datos de la Transacción.
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
    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public float getMonto() { return monto; }
    public Empleado getEmpleado() { return empleado; }
    public Cuenta getCuenta() { return cuenta; }
    public Cliente getCliente() { return cliente; }

    /**
     * Método auxiliar para generar un resumen legible de la transacción.
     */
    public String toString() {
        return "ID: " + id +
                "\nFecha: " + fechaHora +
                "\nMonto: S/ " + monto +
                "\nEmpleado: " + (empleado != null ? empleado.getNombre() : "No Aplica") +
                "\nCliente: " + cliente.getNombre() +
                "\nCuenta: " + cuenta.getNumero();
    }
    
    public void procesar() {
        // Implementación base vacía o por defecto
    }
}