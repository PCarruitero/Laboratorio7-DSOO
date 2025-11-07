import java.util.ArrayList;

/**
 Clase Cuenta
 Representa una cuenta bancaria con soporte para depósitos, retiros
 y registro de movimientos. El saldo se maneja con redondeo a 2 decimales.
 */
public class Cuenta {

    private String numero;
    private String tipo;
    private double saldo;
    private ArrayList<Transaccion> movimientos;
    private ArrayList<Cliente> titulares;

    //Constructor de la cuenta
    public Cuenta(String numero, String tipo, float saldoInicial, ArrayList<Cliente> titulares) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = (titulares != null) ? titulares : new ArrayList<>();
        this.movimientos = new ArrayList<>();
    }

    //Redondea un monto a 2 decimales.
    private double redondear(double monto) {
        return Math.round(monto * 100) / 100f;
    }

    // ====================================
    // GETTERS
    // ====================================
    public String getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public double getSaldo() { return saldo; }
    public ArrayList<Cliente> getTitulares() { return titulares; }
    public ArrayList<Transaccion> getMovimientos() { return movimientos; }

    // ====================================
    // SETTERS
    // ====================================
    public void setNumero(String numero) { this.numero = numero; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setSaldo(float saldo) { this.saldo = redondear(saldo); }
    public void setTitulares(ArrayList<Cliente> titulares) { this.titulares = titulares; }

    // ====================================
    // MÉTODOS DE NEGOCIO
    // ====================================

    /**
     * Acredita un monto a la cuenta.
     */
    public void acreditar(double monto, Transaccion transaccion) {
        saldo = redondear(saldo + monto);
        movimientos.add(transaccion);
    }

    /**
     * Debita un monto si hay saldo suficiente.
     */
    public boolean debitar(double monto, Transaccion transaccion) {
        if (saldo >= monto) {
            saldo = redondear(saldo - monto);
            movimientos.add(transaccion);
            return true;
        }
        return false;
    }

    /**
     * Retorna la lista completa de movimientos.
     */
    public ArrayList<Transaccion> listarMovimientos() {
        return movimientos;
    }

    /**
     * Agrega un nuevo titular a la cuenta.
     */
    public void agregarTitular(Cliente cliente) {
        titulares.add(cliente);
    }
}
