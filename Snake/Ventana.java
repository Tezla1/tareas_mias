import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame {

    private JPanel panel;
    private JButton boton1, boton2, boton3;
    private Component componenteActual; // Almacena el componente actual que se muestra en el panel

    public Ventana() {
        setTitle("Mi Ventana");

        panel = new JPanel();

        boton1 = new JButton("Snake 1");
        boton2 = new JButton("Snake 2");
        boton3 = new JButton("Snake 3");

        panel.add(boton1);
        panel.add(boton2);
        panel.add(boton3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra la interfaz de SnakeGame1
                mostrarSnakeGame1();
            }
        });

        boton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra la interfaz de SnakeGame2
                mostrarSnakeGame2();
            }
        });

        boton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestra la interfaz de SnakeGame3
                mostrarSnakeGame3();
            }
        });

        add(panel);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void mostrarComponente(Component componente) {
        // Limpia el panel
        panel.removeAll();

        // Agrega el nuevo componente al panel
        panel.add(componente);
        componente.requestFocusInWindow();

        // Valida y repinta el panel
        panel.revalidate();
        panel.repaint();

        componenteActual = componente;

        // Ajusta el tamaño de la ventana al componente
        pack();
    }

    public void mostrarSnakeGame1() {
        // Crea una instancia de SnakeGame1 y la muestra en la ventana
        SnakeGame1 snakeGame1 = new SnakeGame1();
        mostrarComponente(snakeGame1);
    }

    public void mostrarSnakeGame2() {
        // Crea una instancia de SnakeGame2 y la muestra en la ventana
        SnakeGame2 snakeGame2 = new SnakeGame2();
        mostrarComponente(snakeGame2);
    }

    public void mostrarSnakeGame3() {
        // Crea una instancia de SnakeGame3 y la muestra en la ventana
        SnakeGame3 snakeGame3 = new SnakeGame3();
        mostrarComponente(snakeGame3);
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
    }
}


