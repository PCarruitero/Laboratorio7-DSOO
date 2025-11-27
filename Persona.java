/**
 * Clase abstracta Persona
 * Sirve como clase base para Cliente y Empleado con atributos generales.
 */
public class Persona {

    // Atributos protegidos para herencia directa (hijos: Cliente, Empleado)
    protected String dni;
    protected String nombre;
    protected String direccion;
    protected String telefono;
    protected String email;

    /**
     * Constructor vacío por defecto.
     * Útil para frameworks y serialización.
     */
    public Persona() {}

    /**
     * Constructor principal y completo de la clase Persona.
     * @param dni        Documento nacional de identidad.
     * @param nombre     Nombre completo.
     * @param direccion  Dirección física.
     * @param telefono   Teléfono de contacto.
     * @param email      Correo electrónico.
     */
    public Persona(String dni, String nombre, String direccion, String telefono, String email) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    // --- Métodos Getters ---
    // Permiten solo lectura controlada a los atributos (Encapsulamiento)
    public String getDni()       { return dni; }
    public String getNombre()    { return nombre; }
    public String getDireccion() { return direccion; }
    public String getTelefono()  { return telefono; }
    public String getEmail()     { return email; }

    // --- Métodos Setters ---
    // Permiten solo escritura controlada a los atributos (Encapsulamiento)
    public void setNombre(String nombre)         { this.nombre = nombre; }
    public void setDireccion(String direccion)   { this.direccion = direccion; }
    public void setTelefono(String telefono)     { this.telefono = telefono; }
    public void setEmail(String email)           { this.email = email; }
}
