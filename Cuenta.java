import java.util.ArrayList;

/**
 * Clase Cuenta
 * Representa una cuenta bancaria con soporte para depósitos, retiros
 * y registro de movimientos. El saldo se maneja con redondeo a 2 decimales.
 */
public class Cuenta {

    private String numero;
    private String tipo;
    private double saldo;
    private ArrayList<Transaccion> movimientos;
    private ArrayList<Cliente> titulares;

    /**
     * Constructor de la cuenta.
     * @param numero         Número identificador.
     * @param tipo           Tipo de cuenta (AHORRO/CTE).
     * @param saldoInicial   Saldo inicial (positivo).
     * @param titulares      Lista de clientes titulares.
     */
    public Cuenta(String numero, String tipo, double saldoInicial, ArrayList<Cliente> titulares) {
        this.numero = numero;
        this.tipo = tipo;
        this.saldo = redondear(saldoInicial);
        this.titulares = (titulares != null) ? titulares : new ArrayList<>();
        this.movimientos = new ArrayList<>();
    }

    // --- Utilidad privada para redondear a 2 decimales ---
    private double redondear(double monto) {
        return Math.round(monto * 100) / 100.0;
    }

    // ====== GETTERS ======
    public String getNumero()         { return numero; }
    public String getTipo()           { return tipo; }
    public double getSaldo()          { return saldo; }
    public ArrayList<Cliente> getTitulares()    { return titulares; }
    public ArrayList<Transaccion> getMovimientos() { return movimientos; }

    // ====== SETTERS ======
    public void setNumero(String numero)              { this.numero = numero; }
    public void setTipo(String tipo)                  { this.tipo = tipo; }
    public void setSaldo(double saldo)                { this.saldo = redondear(saldo); }
    public void setTitulares(ArrayList<Cliente> titulares) { this.titulares = titulares; }

    // ====== MÉTODOS DE NEGOCIO ======

    /**
     * Deposita/acredita un monto a la cuenta.
     * @param monto         Monto a depositar.
     * @param transaccion   Transaccion generada por depósito.
     */
    public void acreditar(double monto, Transaccion transaccion) {
        saldo = redondear(saldo + monto);
        movimientos.add(transaccion);
    }

    /**
     * Intenta debitar un monto si hay saldo suficiente.
     * @param monto         Monto a debitar.
     * @param transaccion   Transacción generada por retiro.
     * @return true si el retiro se procesó; false en caso de saldo insuficiente.
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
     * Retorna una copia de la lista completa de movimientos.
     */
    public ArrayList<Transaccion> listarMovimientos() {
        return new ArrayList<>(movimientos);
    }

    /**
     * Agrega un nuevo titular (cliente) a la cuenta.
     * @param cliente Cliente a agregar.
     */
    public void agregarTitular(Cliente cliente) {
        titulares.add(cliente);
    }

    // Puedes agregar aquí excepciones personalizadas si manejas errores específicos.
}
