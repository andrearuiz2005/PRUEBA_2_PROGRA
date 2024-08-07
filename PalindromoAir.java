/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_andrea;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author ar466
 */
public class PalindromoAir {
    Ticket[] asientos = new Ticket[30];

    public int primerDisponible() {
        return primerDisponibleRecursivo(0);
    }

    private int primerDisponibleRecursivo(int indice) {
        if (indice >= asientos.length) {
            return -1;
        }
        if (asientos[indice] == null) {
            return indice;
        }
        return primerDisponibleRecursivo(indice + 1);
    }

    public int buscarPasajero(String nombre) {
        return buscarPasajeroRecursivo(nombre, 0);
    }

    private int buscarPasajeroRecursivo(String nombre, int indice) {
        if (indice >= asientos.length) {
            return -1;
        }
        if (asientos[indice] != null && asientos[indice].getNombrePasajero().equals(nombre)) {
            return indice;
        }
        return buscarPasajeroRecursivo(nombre, indice + 1);
    }

    public boolean esPalindromo(String nombre) {
        return esPalindromoRecursivo(nombre, 0, nombre.length() - 1);
    }

    private boolean esPalindromoRecursivo(String nombre, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return true;
        }
        if (nombre.charAt(izquierda) != nombre.charAt(derecha)) {
            return false;
        }
        return esPalindromoRecursivo(nombre, izquierda + 1, derecha - 1);
    }

    public void imprimirPasajeros(JTextArea areaSalida) {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            if (asientos[i] != null) {
                salida.append("Asiento ").append(i).append(": ")
                      .append(asientos[i].getNombrePasajero())
                      .append(" - $").append(asientos[i].getTotalPagado())
                      .append("\n");
            }
        }
        areaSalida.setText(salida.toString());
    }

    public double ingresos() {
        return ingresosRecursivo(0);
    }

    private double ingresosRecursivo(int indice) {
        if (indice >= asientos.length) {
            return 0;
        }
        if (asientos[indice] == null) {
            return ingresosRecursivo(indice + 1);
        }
        return asientos[indice].getTotalPagado() + ingresosRecursivo(indice + 1);
    }

    public void reiniciar() {
        reiniciarRecursivo(0);
    }

    private void reiniciarRecursivo(int indice) {
        if (indice >= asientos.length) {
            return;
        }
        asientos[indice] = null;
        reiniciarRecursivo(indice + 1);
    }

    public void venderTicket(String nombre, JLabel mensajeLabel) {
        int asientoDisponible = primerDisponible();
        if (asientoDisponible == -1) {
            mensajeLabel.setText("No hay asientos disponibles.");
            return;
        }
        double precioTicket = 800;
        if (esPalindromo(nombre)) {
            precioTicket *= 0.8;
        }
        asientos[asientoDisponible] = new Ticket(nombre, precioTicket);
        mensajeLabel.setText("Ticket vendido a " + nombre + " por $" + precioTicket);
    }

    public boolean cancelarTicket(String nombre, JLabel mensajeLabel) {
        int indicePasajero = buscarPasajero(nombre);
        if (indicePasajero == -1) {
            mensajeLabel.setText("Pasajero " + nombre + " no encontrado.");
            return false;
        }
        asientos[indicePasajero] = null;
        mensajeLabel.setText("Ticket de " + nombre + " cancelado.");
        return true;
    }

    public void despachar(JLabel mensajeLabel) {
        double ingresosTotales = ingresos();
        mensajeLabel.setText("Ingresos totales: $" + ingresosTotales + ". Todos los asientos reiniciados.");
        reiniciar();
    }
}