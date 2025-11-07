import java.util.ArrayList;

//Clase Banco: Es la clase Gestora o Controlador del sistema.
public class Banco {

    // Listas para almacenar las entidades (Agregación/Composición)
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    // Nota: Las transacciones se almacenan dentro de cada Cuenta.

    //Método para registrar un objeto Cliente en la lista.
    public void registrarCliente(Cliente cliente) { clientes.add(cliente); }

    //Método para registrar un objeto Empleado en la lista.
    public void registrarEmpleado(Empleado empleado) { empleados.add(empleado); }

    /**
     * Método de negocio: Crea una nueva Cuenta y la añade a la lista de cuentas del banco.
     */
    public Cuenta crearCuenta(ArrayList<Cliente> titulares, float saldoInicial) {
        String numero = "C" + (cuentas.size() + 1); // Generación simple de ID
        // El tipo de cuenta está fijo en "Ahorros"
        Cuenta cliente = new Cuenta(numero, "Ahorros", saldoInicial, titulares);
        cuentas.add(cliente);
        return cliente;
    }

    /**
     * Método auxiliar de búsqueda: Encuentra una Cuenta por su número.
     */
    public Cuenta buscarCuentaPorNumero(String numero) {
        for (Cuenta cuenta : cuentas)
            if (cuenta.getNumero().equals(numero))
                return cuenta;
        return null; // Retorna null si no se encuentra
    }
}