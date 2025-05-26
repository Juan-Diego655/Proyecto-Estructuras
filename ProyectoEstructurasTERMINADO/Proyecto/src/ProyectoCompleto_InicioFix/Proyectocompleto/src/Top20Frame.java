package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;

public class Top20Frame extends JFrame {

    public Top20Frame() {
        setTitle("Top 20 APUNAB");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Header con logo
        JPanel logoHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoHeader.setBackground(new Color(212, 31, 17));
        logoHeader.setPreferredSize(new Dimension(900, 70));
        try {
            ImageIcon icon = new ImageIcon("resources/logo_unab-902421018.png");
            JLabel logo = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
            logoHeader.add(logo);
        } catch (Exception e) {
            JLabel unabLabel = new JLabel("UNAB");
            unabLabel.setForeground(Color.WHITE);
            unabLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            logoHeader.add(unabLabel);
        }

        // Header con título y botón volver
        JPanel titleHeader = new JPanel(new BorderLayout());
        titleHeader.setBackground(new Color(212, 31, 17));
        titleHeader.setPreferredSize(new Dimension(900, 60));

        JLabel volver = new JLabel("<");
        volver.setFont(new Font("SansSerif", Font.BOLD, 22));
        volver.setForeground(Color.WHITE);
        volver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        volver.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        volver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Cambiar por la clase correcta que tengas
                // new InicioFrame();
                JOptionPane.showMessageDialog(Top20Frame.this, "Volver al inicio");
                dispose();
            }
        });

        JLabel title = new JLabel("Top 20 APUNAB", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));

        titleHeader.add(volver, BorderLayout.WEST);
        titleHeader.add(title, BorderLayout.CENTER);

        // Panel combinado para header
        JPanel combinedHeader = new JPanel(new BorderLayout());
        combinedHeader.add(logoHeader, BorderLayout.NORTH);
        combinedHeader.add(titleHeader, BorderLayout.SOUTH);
        add(combinedHeader, BorderLayout.NORTH);

        // Panel principal con scroll
        JPanel mainPanel = createMainPanel();
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Campo de búsqueda
        JTextField searchField = new JTextField("Buscar");
        searchField.setMaximumSize(new Dimension(800, 30));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createLineBorder(new Color(231, 165, 39), 2));
        panel.add(searchField);
        
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Filtros
        JLabel filtros = new JLabel("Rankings \n Estudiantes | Docentes");
        filtros.setFont(new Font("SansSerif", Font.PLAIN, 14));
        filtros.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(filtros);
        
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Lista de estudiantes
        String[] nombres = {
            "Valentina Ríos", "Carlos Gómez", "Lucía Martínez", "Daniel Torres", "Sofía Ramírez",
            "Juan Pérez", "Camila Fernández", "Andrés Morales", "Isabella Castro", "Felipe Ruiz",
            "Mariana Mendoza", "Sebastián Ortiz", "Laura Hernández", "Mateo Vargas", "Sara Mejía",
            "Diego López", "Gabriela Navarro", "Tomás Aguirre", "Natalia Paredes", "Julián Cárdenas"
        };

        for (int i = 0; i < nombres.length; i++) {
            JPanel itemPanel = createRankingItem(i + 1, nombres[i]);
            panel.add(itemPanel);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Navegación inferior
        JPanel nav = createNavigationPanel();
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(nav);

        return panel;
    }

    private JPanel createRankingItem(int posicion, String nombre) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setMaximumSize(new Dimension(800, 50));
        itemPanel.setPreferredSize(new Dimension(800, 50));

        // Ícono de usuario
        JLabel icono = new JLabel("👤");
        icono.setPreferredSize(new Dimension(40, 40));
        icono.setOpaque(true);
        icono.setBackground(new Color(231, 165, 39));
        icono.setHorizontalAlignment(JLabel.CENTER);
        icono.setFont(new Font("SansSerif", Font.BOLD, 18));

        // Texto con posición y nombre
        JLabel texto = new JLabel("#" + posicion + " - " + nombre);
        texto.setFont(new Font("SansSerif", Font.PLAIN, 14));

        itemPanel.add(icono);
        itemPanel.add(texto);

        return itemPanel;
    }

    private JPanel createNavigationPanel() {
        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        nav.setBackground(Color.WHITE);
        nav.setMaximumSize(new Dimension(800, 60));

        String[] icons = {"🏠", "📄", "🔲", "💰"};
        for (int i = 0; i < icons.length; i++) {
            JButton btn = new JButton(icons[i]);
            btn.setPreferredSize(new Dimension(40, 40));
            if (i == 3) { // Resaltar el botón activo
                btn.setBackground(new Color(231, 165, 39));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(Color.WHITE);
            }
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            nav.add(btn);
        }

        return nav;
    }
}