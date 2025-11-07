import java.time.LocalDateTime;

public class Retiro extends Transaccion {

    /**
     * Constructor de Retiro.
     * Llama al constructor de la clase padre (Transaccion) para inicializar los atributos.
     */
    public Retiro(Cuenta cuenta, float monto, Cliente cliente, Empleado empleado) {
        super("R-" + System.currentTimeMillis(),
                LocalDateTime.now(),
                monto,
                empleado,
                cuenta,
                cliente);
    }

    /**
     * Sobreescritura (Polimorfismo) del método para ejecutar la acción de retiro.
     * Incluye la validación del saldo llamando al método 'debitar' de Cuenta.
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