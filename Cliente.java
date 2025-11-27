import java.util.ArrayList;

/**
 * Clase Cliente
 * Representa a un cliente de banco, que extiende Persona y puede tener múltiples cuentas.
 */
public class Cliente extends Persona {

    // Identificador único del cliente
    private String idCliente;

    // Lista de cuentas (agregación)
    private ArrayList<Cuenta> cuentas;

    /**
     * Constructor principal para inicializar un Cliente.
     * Inicializa los datos heredados de Persona y la lista de cuentas.
     * @param dni       Documento nacional de identidad del cliente.
     * @param nombre    Nombre completo del cliente.
     * @param direccion Dirección física.
     * @param telefono  Teléfono de contacto.
     * @param email     Dirección de correo electrónico.
     * @param idCliente Identificador único para el cliente.
     */
    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String idCliente) {
        super(dni, nombre, direccion, telefono, email);
        this.idCliente = idCliente;
        this.cuentas = new ArrayList<>();
    }

    /**
     * Agrega una nueva cuenta a la lista de cuentas del cliente.
     * @param cuenta Cuenta a asociar al cliente.
     */
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    /**
     * Retorna la lista de cuentas del cliente (encapsulamiento).
     * @return ArrayList de cuentas
     */
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * Retorna el identificador único del cliente.
     * @return idCliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Calcula y retorna la suma total de los saldos de todas sus cuentas.
     * Demuestra interacción con objetos de la clase Cuenta (agregación).
     * @return Suma total redondeada de los saldos.
     */
    public float getSaldoTotal() {
        float total = 0;
        for (Cuenta cuenta : cuentas) total += cuenta.getSaldo();
        return Math.round(total * 100) / 100f; // Redondeo a dos decimales
    }
}
