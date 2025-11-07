import java.time.LocalDateTime;

public class Deposito extends Transaccion {

    /**
     * Constructor de Deposito.
     * Llama al constructor de la clase padre (Transaccion) para inicializar los atributos.
     */
    public Deposito(Cuenta cuenta, float monto, Cliente cliente, Empleado empleado) {
        super("D-" + System.currentTimeMillis(),
                LocalDateTime.now(),
                monto,
                empleado,
                cuenta,
                cliente);
    }

    /**
     * Sobreescritura (Polimorfismo) del método para ejecutar la acción de depósito.
     * Llama al método 'acreditar' de la clase Cuenta.
     */
    @Override
    public void procesar() {
        cuenta.acreditar(monto, this);
        System.out.println("Depósito exitoso.");
    }
}
