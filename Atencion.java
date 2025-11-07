import java.time.LocalDateTime;

public class Atencion {

    private String id;
    private LocalDateTime fechaHora;
    private Cliente cliente;    // Asociación 1:1 con Cliente
    private Empleado empleado;  // Asociación 1:1 con Empleado
    private String motivo;
    private String notas = "";

    //Constructor que inicializa una atención al cliente.
    public Atencion(Cliente cliente, String motivo, Empleado empleado) {
        this.id = "A-" + System.currentTimeMillis();
        this.fechaHora = LocalDateTime.now();
        this.cliente = cliente;
        this.motivo = motivo;
        this.empleado = empleado;
    }

    // --- Métodos Getters ---
    public String getId() { return id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public Cliente getCliente() { return cliente; }
    public Empleado getEmpleado() { return empleado; }
    public String getMotivo() { return motivo; }
    public String getNotas() { return notas; }

    //Método de comportamiento: Agrega información adicional a la atención.
    public void agregarNota(String nota) {
        notas += nota + "\n";
    }
}