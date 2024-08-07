/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba2_andrea;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author ar466
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(new PalindromoAir());
            ventana.setVisible(true);
        });
    }
}

class VentanaPrincipal extends JFrame {
    private PalindromoAir aerolinea;
    private JTextField campoNombre;
    private JTextField campoBuscar;
    private JTextArea areaSalida;
    private JLabel mensajeLabel;

    public VentanaPrincipal(PalindromoAir aerolinea) {
        this.aerolinea = aerolinea;
        setTitle("Sistema de Venta de Tickets de PalindromoAir");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelEntradas = new JPanel();
        panelEntradas.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel etiquetaNombre = new JLabel("Nombre del Pasajero:");
        campoNombre = new JTextField(20);

        JLabel etiquetaBuscar = new JLabel("Buscar Pasajero:");
        campoBuscar = new JTextField(20);

        panelEntradas.add(etiquetaNombre);
        panelEntradas.add(campoNombre);
        panelEntradas.add(etiquetaBuscar);
        panelEntradas.add(campoBuscar);

        panelPrincipal.add(panelEntradas, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(2, 2, 1, 1));

        JButton botonVender = crearBoton("Vender Ticket");
        botonVender.addActionListener(e -> venderTicket());

        JButton botonImprimir = crearBoton("Imprimir Pasajeros");
        botonImprimir.addActionListener(e -> imprimirPasajeros());

        JButton botonDespachar = crearBoton("Despachar");
        botonDespachar.addActionListener(e -> despachar());

        JButton botonCancelar = crearBoton("Cancelar Ticket");
        botonCancelar.addActionListener(e -> cancelarTicket());

        JButton botonBuscar = crearBoton("Buscar");
        botonBuscar.addActionListener(e -> buscarPasajero());

        JButton botonSalir = crearBoton("Salir");
        botonSalir.addActionListener(e -> salir());

        panelBotones.add(botonVender);
        panelBotones.add(botonImprimir);
        panelBotones.add(botonDespachar);
        panelBotones.add(botonCancelar);
        panelBotones.add(botonBuscar);
        panelBotones.add(botonSalir);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(areaSalida);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        mensajeLabel = new JLabel("Bienvenido al sistema de venta de tickets de PalindromoAir");
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipal.add(mensajeLabel, BorderLayout.PAGE_END);

        add(panelPrincipal);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(128, 0, 128));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 12)); // Botones más pequeños
        return boton;
    }

    private void venderTicket() {
        String nombrePasajero = campoNombre.getText();
        if (nombrePasajero.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese el nombre del pasajero.");
            return;
        }
        aerolinea.venderTicket(nombrePasajero, mensajeLabel);
        campoNombre.setText("");
    }

    private void imprimirPasajeros() {
        aerolinea.imprimirPasajeros(areaSalida);
        mensajeLabel.setText("Lista de pasajeros actualizada.");
    }

    private void despachar() {
        aerolinea.despachar(mensajeLabel);
        areaSalida.setText("");
    }

    private void cancelarTicket() {
        String nombrePasajero = campoNombre.getText();
        if (nombrePasajero.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese el nombre del pasajero.");
            return;
        }
        aerolinea.cancelarTicket(nombrePasajero, mensajeLabel);
        campoNombre.setText("");
    }

    private void buscarPasajero() {
        String nombrePasajero = campoBuscar.getText();
        if (nombrePasajero.isEmpty()) {
            mensajeLabel.setText("Por favor, ingrese el nombre del pasajero.");
            return;
        }
        int posicion = aerolinea.buscarPasajero(nombrePasajero);
        if (posicion != -1) {
            mensajeLabel.setText("Pasajero " + nombrePasajero + " encontrado en el asiento " + posicion + ".");
        } else {
            mensajeLabel.setText("Pasajero " + nombrePasajero + " no encontrado.");
        }
        campoBuscar.setText("");
    }

    private void salir() {
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}