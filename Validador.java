import java.util.Scanner;

public final class Validador {
    private Validador() {}

    public static String leerNombre(Scanner scanner, String mensaje) {
        final String regexLetras = "^[A-Za-zÁÉÍÓÚÑáéíóúÜü'\\-]+( [A-Za-zÁÉÍÓÚÑáéíóúÜü'\\-]+)*$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexLetras)) return entrada;
            System.out.println("Solo letras y espacios (mín. 2).");
        }
    }

    public static String leerCargo(Scanner scanner, String mensaje) {
        final String regexCargo = "^[A-Za-zÁÉÍÓÚÑáéíóúÜü'\\- ]{2,40}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexCargo)) return entrada;
            System.out.println("Solo letras y espacios.");
        }
    }

    public static String leerTelefono(Scanner scanner, String mensaje) {
        final String regexTelefono = "^\\d{7,12}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexTelefono)) return entrada;
            System.out.println("Solo dígitos (7-12).");
        }
    }

    public static String leerEmail(Scanner scanner, String mensaje) {
        final String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexEmail)) return entrada;
            System.out.println("Correo inválido (debe tener '@' y dominio).");
        }
    }

    public static String leerDNI(Scanner scanner, String mensaje) {
        final String regexDni = "^\\d{8}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexDni)) return entrada;
            System.out.println("DNI: 8 dígitos.");
        }
    }

    public static String leerIdCorto(Scanner scanner, String mensaje) {
        final String regexId = "^[A-Za-z0-9\\-]{2,12}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.matches(regexId)) return entrada;
            System.out.println("Use letras/números/guion (2–12).");
        }
    }

    public static String leerNumeroCuenta(Scanner scanner, String mensaje) {
        final String regexCuenta = "^\\d{10,20}$";
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches(regexCuenta)) return entrada;
            System.out.println("Nº de cuenta inválido (10–20 dígitos).");
        }
    }

    public static double leerMontoPositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                double monto = Double.parseDouble(entrada);
                if (monto >= 0) return Math.round(monto * 100.0) / 100.0;
                System.out.println("Debe ser > 0.");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido (use punto decimal).");
            }                              
        }
    }

    public static boolean confirmarSN(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String rpta = sc.nextLine().trim().toUpperCase();
            if (rpta.equals("Si")) return true;
            if (rpta.equals("No")) return false;
            System.out.println("Responda con Si o No.");
        }
    }

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