public class Persona {

    // Atributos protegidos: Permiten la herencia y acceso directo desde las subclases (Cliente, Empleado)
    protected String dni;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected String email;

    /**
     * Constructor vacío por defecto.
     */
    public Persona() {}

    /**
     * Constructor principal de la clase Persona.
     */
    public Persona(String dni, String nombre, String direccion, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    // --- Métodos Getters ---
    // Proveen acceso controlado de lectura a los atributos (Encapsulamiento).
    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // --- Métodos Setters ---
    // Proveen acceso controlado de escritura a los atributos (Encapsulamiento).
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
}