import java.time.LocalDateTime;

public class Transferencia extends Transaccion {
    private Cuenta cuentaDestino;

    public Transferencia(Cuenta cuentaOrigen, Cuenta cuentaDestino, float monto, Cliente cliente, Empleado empleado) {
        super("T-" + System.currentTimeMillis(), LocalDateTime.now(), monto, empleado, cuentaOrigen, cliente);
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void procesar() {
        if (cuenta.getSaldo() >= monto) {
            cuenta.debitar(monto, this); // Debita origen
            cuentaDestino.acreditar(monto, this); // Acredita destino
            System.out.println("Transferencia realizada con éxito.");
        } else {
            System.out.println("Saldo insuficiente en la cuenta de origen.");
        }
    }
}

