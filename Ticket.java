
package Prueba2;

public class Ticket {

private String nombrePasajero;
    private double totalPagado;

    public Ticket(String nombrePasajero, double totalPagado) {
        this.nombrePasajero = nombrePasajero;
        this.totalPagado = totalPagado;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public void imprimir() {
        System.out.println("Nombre del pasajero: " + nombrePasajero + ", Total pagado: " + totalPagado);
    }

    
}
