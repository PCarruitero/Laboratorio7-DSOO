import java.util.Scanner;

/**
 * Clase utilitaria Validador
 * Métodos static para solicitar y validar la entrada por consola de distintos campos clave.
 * No instancia objetos, sólo agrupa validaciones.
 */
public final class Validador {

    /** Constructor privado para impedir instanciación. */
    private Validador() {}

    /**
     * Valida y pide un nombre completo solo con letras y espacios.
     */
    public static String leerNombre(Scanner scanner, String mensaje) {
        final String regexLetras = "^[A-Za-zÁÉÍÓÚÑáéíóúÜü'\\-]+( [A-Za-zÁÉÍÓÚÑáéíóúÜü'\\-]+)*$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexLetras)) return entrada;
            System.out.println("Solo letras y espacios (mín. 2).");
        }
    }

    /**
     * Valida y pide el cargo/rol para empleados.
     */
    public static String leerCargo(Scanner scanner, String mensaje) {
        final String regexCargo = "^[A-Za-zÁÉÍÓÚÑáéíóúÜü'\\- ]{2,40}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexCargo)) return entrada;
            System.out.println("Solo letras y espacios.");
        }
    }

    /**
     * Valida un número de teléfono.
     */
    public static String leerTelefono(Scanner scanner, String mensaje) {
        final String regexTelefono = "^\\d{7,12}$"; // Entre 7 y 12 dígitos
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            // Valida estructura y cantidad de dígitos
            if (!entrada.matches(regexTelefono)) {
                System.out.println("Solo dígitos (7-12).");
                continue;
            }
            // Impide que el teléfono sea puro ceros
            if (entrada.matches("^0+$")) {
                System.out.println("El teléfono no puede ser solo ceros.");
                continue;
            }
            return entrada;
        }
    }


    /**
     * Valida y pide el correo electrónico con dominio y @.
     */
    public static String leerEmail(Scanner scanner, String mensaje) {
        final String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexEmail)) return entrada;
            System.out.println("Correo inválido (debe tener '@' y dominio).");
        }
    }

    /**
     * Valida y pide el DNI (8 dígitos).
     */
    public static String leerDNI(Scanner scanner, String mensaje) {
        final String regexDni = "^\\d{8}$"; // Debe ser exactamente 8 dígitos
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.matches(regexDni)) {
                System.out.println("DNI: 8 dígitos.");
                continue;
            }
            // Verifica que no sea solamente ceros
            if (entrada.equals("00000000")) {
                System.out.println("DNI no puede ser solo ceros.");
                continue;
            }
            // Puedes agregar aquí otras restricciones, como listas de DNIs inválidos.
            return entrada;
        }
    }


    /**
     * Valida y pide un ID alfanumérico corto (para clientes/empleados).
     */
    public static String leerIdCorto(Scanner scanner, String mensaje) {
        final String regexId = "^[A-Za-z0-9\\-]{2,12}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.matches(regexId)) return entrada;
            System.out.println("Use letras/números/guion (2–12).");
        }
    }

    /**
     * Valida y pide el número de cuenta (10-20 dígitos).
     */
    public static String leerNumeroCuenta(Scanner scanner, String mensaje) {
        final String regexCuenta = "^\\d{10,20}$"; // Entre 10 y 20 dígitos
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            // Valida que la cuenta tenga la longitud esperada
            if (!entrada.matches(regexCuenta)) {
                System.out.println("Nº de cuenta inválido (10–20 dígitos).");
                continue;
            }
            // Impide que el número de cuenta sea puro ceros
            if (entrada.matches("^0+$")) {
                System.out.println("El número de cuenta no puede ser solo ceros.");
                continue;
            }
            // Puedes expandir aún más la lógica si lo requieres
            return entrada;
        }
    }


    /**
     * Valida y pide un monto positivo (double).
     */
    public static double leerMontoPositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                double monto = Double.parseDouble(entrada);
                if (monto > 0) { // Solo acepta valores mayores a cero
                    return Math.round(monto * 100.0) / 100.0;
                }
                System.out.println("El monto debe ser mayor a cero.");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido (use punto decimal).");
            }
        }
    }

    /**
     * Pide confirmación Si/No (caso general para operaciones).
     */
    public static boolean confirmarSN(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String rpta = sc.nextLine().trim().toUpperCase();
            if (rpta.equals("SI")) return true;
            if (rpta.equals("NO")) return false;
            System.out.println("Responda con Si o No.");
        }
    }

    /**
     * Valida y pide el tipo de cuenta (AHORRO o CTE).
     */
    public static String leerTipoCuenta(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String tipo = scanner.nextLine().trim().toUpperCase();
            if (tipo.equals("AHORRO") || tipo.equals("CTE")) {
                return tipo;
            }
            System.out.println("Tipo de cuenta inválido. Opciones válidas: AHORRO o CTE.");
        }
    }

    /**
     * Valida la selección de una opción dentro de un menú.
     */
    public static int leerOpcion(Scanner scanner, String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                int opcion = Integer.parseInt(entrada);
                if (opcion >= minimo && opcion <= maximo) return opcion;
                System.out.println("Opción fuera de rango.");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un entero válido.");
            }
        }
    }
}
