import java.util.ArrayList;

public class Cliente extends Persona {

    // Atributo específico del Cliente
    private String idCliente;
    private ArrayList<Cuenta> cuentas;

    // Constructor principal para inicializar un Cliente.
    // Llama al constructor de la superclase Persona e inicializa la lista de cuentas.
    public Cliente(String dni, String nombre, String direccion,
                   String telefono, String email, String idCliente) {

        super(dni, nombre, direccion, telefono, email); // Inicializa atributos heredados
        this.idCliente = idCliente;
        this.cuentas = new ArrayList<>();
    }

    //Comportamiento: Añade una nueva cuenta a la lista de cuentas del cliente
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    //Método Getter: Retorna la lista de cuentas del cliente (Encapsulamiento).
    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    //Método Getter: Retorna el identificador único del cliente (Encapsulamiento).
    public String getIdCliente() { return idCliente; }

    //Método de Negocio: Calcula y retorna la suma total de saldos de todas sus cuentas.
    //Demuestra la interacción con objetos de la clase Cuenta (Agregación).
    public float getSaldoTotal() {
        float total = 0;
        // Itera sobre la lista de Cuentas para sumar los saldos
        for (Cuenta cuenta : cuentas) total += cuenta.getSaldo();
        // Asegura que el total se redondee a dos decimales (Lógica de Negocio)
        return Math.round(total * 100) / 100f;
    }
}