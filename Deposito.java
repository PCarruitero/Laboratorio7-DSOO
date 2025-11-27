import java.time.LocalDateTime;

/**
 * Clase Deposito
 * Especializa Transaccion para representar una operación de depósito en cuenta bancaria.
 */
public class Deposito extends Transaccion {

    /**
     * Constructor de Deposito.
     * Inicializa todos los atributos heredados desde Transaccion.
     * @param cuenta   Cuenta destino del depósito.
     * @param monto    Monto a depositar.
     * @param cliente  Cliente que solicita el depósito.
     * @param empleado Empleado que procesa la operación.
     */
    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Empleado empleado) {
        super("D-" + System.currentTimeMillis(), // ID único por tiempo
              LocalDateTime.now(), 
              monto, 
              empleado, 
              cuenta, 
              cliente);
    }

    /**
     * Sobrescribe (polimorfismo) el método para ejecutar la acción de depósito.
     * Aplica el monto a la cuenta usando acreditar y registra el movimiento.
     */
    @Override
    public void procesar() {
        cuenta.acreditar(monto, this);
        System.out.println("Depósito exitoso.");
    }
}
