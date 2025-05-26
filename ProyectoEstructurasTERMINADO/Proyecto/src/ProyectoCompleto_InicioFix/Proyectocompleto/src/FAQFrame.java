package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FAQFrame extends JFrame {

    public FAQFrame() {
        setTitle("Preguntas Frecuentes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(900, 70));
        try {
            ImageIcon icon = new ImageIcon("resources/logo_unab.png");
            JLabel logo = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
            header.add(logo);
        } catch (Exception e) {
            header.add(new JLabel("UNAB"));
        }
        add(header, BorderLayout.NORTH);

        header = new JPanel(new BorderLayout());
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(800, 60));
        JLabel title = new JLabel("Preguntas frecuentes", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(2, 2, 30, 30));
        gridPanel.setBackground(Color.WHITE);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        String[] preguntas = {
            "¿Qué son los puntos APUNAB y cómo los gano?",
            "¿Qué pasa si pierdo una apuesta?",
            "¿Cuántas apuestas puedo hacer al mismo tiempo?",
            "¿Puedo recuperar mis puntos si me retiro de un evento?"
        };

        String[] respuestas = {
            "Los puntos APUNAB se obtienen al participar y ganar apuestas en eventos académicos o deportivos.",
            "Si pierdes una apuesta, los puntos apostados se descuentan de tu saldo actual de APUNAB.",
            "Puedes participar en hasta 5 apuestas simultáneamente, dependiendo del tipo de evento.",
            "Si te retiras antes de que el evento comience, puedes recuperar hasta el 50% de tus puntos."
        };

        for (int i = 0; i < preguntas.length; i++) {
            JButton btn = new JButton("<html><center>" + preguntas[i] + "</center></html>");
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btn.setBackground(new Color(231, 165, 39));
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            int idx = i;
            btn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, respuestas[idx], "Respuesta", JOptionPane.INFORMATION_MESSAGE)
            );
            gridPanel.add(btn);
        }

        add(gridPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}