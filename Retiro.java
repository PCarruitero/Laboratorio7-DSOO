import java.time.LocalDateTime;

/**
 * Clase Retiro
 * Especializa Transaccion para representar una operación de retiro en cuenta bancaria.
 */
public class Retiro extends Transaccion {

    /**
     * Constructor de Retiro.
     * Inicializa todos los atributos heredados desde Transaccion.
     * @param cuenta   Cuenta origen del retiro.
     * @param monto    Monto a retirar.
     * @param cliente  Cliente que solicita el retiro.
     * @param empleado Empleado que procesa la operación.
     */
    public Retiro(Cuenta cuenta, float monto, Cliente cliente, Empleado empleado) {
        super("R-" + System.currentTimeMillis(), // ID único por tiempo
              LocalDateTime.now(),
              monto,
              empleado,
              cuenta,
              cliente);
    }

    /**
     * Sobrescribe (polimorfismo) el método para ejecutar la acción de retiro.
     * Valida saldo suficiente llamando a debitar en Cuenta y registra el movimiento si corresponde.
     */
    @Override
    public void procesar() {
        if (cuenta.debitar(monto, this)) {
            System.out.println("Retiro exitoso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }
}
