import java.time.LocalDateTime;

/**
 * Clase Atencion
 * Representa una atención o interacción solicitada por un cliente y realizada por un empleado.
 * Permite registrar el motivo y notas adicionales para seguimiento.
 */
public class Atencion {

    private String id; // Identificador único de la atención
    private LocalDateTime fechaHora; // Fecha y hora de registro
    private Cliente cliente;   // Asociación 1:1 con Cliente
    private Empleado empleado; // Asociación 1:1 con Empleado
    private String motivo;     // Motivo principal de la atención
    private String notas = ""; // Notas adicionales agregables

    /**
     * Constructor para inicializar una atención al cliente.
     * Crea un ID único y registra el tiempo.
     * @param cliente  Cliente que solicita la atención.
     * @param motivo   Motivo principal de la atención.
     * @param empleado Empleado que atiende al cliente.
     */
    public Atencion(Cliente cliente, String motivo, Empleado empleado) {
        this.id = "A-" + System.currentTimeMillis();
        this.fechaHora = LocalDateTime.now();
        this.cliente = cliente;
        this.motivo = motivo;
        this.empleado = empleado;
    }

    // --- Getters ---
    public String getId()             { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Cliente getCliente()       { return cliente; }
    public Empleado getEmpleado()     { return empleado; }
    public String getMotivo()         { return motivo; }
    public String getNotas()          { return notas; }

    /**
     * Método para agregar información adicional a la atención.
     * @param nota Comentario adicional o detalle relevante.
     */
    public void agregarNota(String nota) {
        notas += nota + "\n";
    }
}
