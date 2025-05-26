package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogrosFrame extends JFrame {

    public LogrosFrame() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Tus logros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header principal con logo
        JPanel mainHeader = createMainHeader();
        
        // Panel para título y navegación
        JPanel titlePanel = createTitlePanel();

        // Combinar headers en un panel vertical
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(mainHeader, BorderLayout.NORTH);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        
        // Contenido central
        JPanel content = createContentPanel();
        
        // ScrollPane para hacer el contenido scrolleable
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Agregar componentes al frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
    
    private JPanel createMainHeader() {
        JPanel mainHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainHeader.setBackground(new Color(212, 31, 17));
        mainHeader.setPreferredSize(new Dimension(900, 70));
        
        try {
            ImageIcon icon = new ImageIcon("resources/logo_unab.png");
            Image scaledImage = icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(scaledImage));
            mainHeader.add(logo);
        } catch (Exception e) {
            JLabel unabLabel = new JLabel("UNAB");
            unabLabel.setForeground(Color.WHITE);
            unabLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
            mainHeader.add(unabLabel);
        }
        
        return mainHeader;
    }
    
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(212, 31, 17));
        titlePanel.setPreferredSize(new Dimension(900, 60));
        
        JLabel volver = new JLabel("<");
        volver.setFont(new Font("SansSerif", Font.BOLD, 22));
        volver.setForeground(Color.WHITE);
        volver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        volver.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        volver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                volverClick();
            }
        });
        
        JLabel title = new JLabel("Tus logros", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        titlePanel.add(volver, BorderLayout.WEST);
        titlePanel.add(title, BorderLayout.CENTER);
        
        return titlePanel;
    }
    
    private JPanel createContentPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Campo de búsqueda
        JTextField search = new JTextField("Buscar");
        search.setFont(new Font("SansSerif", Font.PLAIN, 14));
        search.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(231, 165, 39), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        search.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        search.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        content.add(search);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // Título de sección
        JLabel sectionTitle = new JLabel("Logros alcanzados");
        sectionTitle.setFont(new Font("SansSerif", Font.BOLD, 16));
        sectionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(sectionTitle);
        content.add(Box.createRigidArea(new Dimension(0, 15)));

        // Agregar logros
        addLogros(content);
        
        // Footer con puntos
        content.add(Box.createRigidArea(new Dimension(0, 30)));
        JPanel footer = createFooterPanel();
        content.add(footer);
        
        return content;
    }
    
    private void addLogros(JPanel content) {
        String[] descripciones = {
            "Realizaste tu primera apuesta en el sistema APUNAB",
            "Ganaste 3 apuestas consecutivas en eventos deportivos",
            "Acumulaste 500 puntos por predicciones acertadas",
            "Participaste en 10 eventos con apuestas activas",
            "Obtuviste el mayor puntaje en una apuesta grupal"
        };

        for (String descripcion : descripciones) {
            JPanel logroPanel = createLogroPanel(descripcion);
            content.add(logroPanel);
            content.add(Box.createRigidArea(new Dimension(0, 15)));
        }
    }
    
    private JPanel createLogroPanel(String descripcion) {
        JPanel logroPanel = new JPanel(new BorderLayout());
        logroPanel.setBackground(Color.WHITE);
        logroPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(212, 31, 17), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        logroPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        // Icono del logro
        JLabel icono = new JLabel("🏆");
        icono.setFont(new Font("SansSerif", Font.PLAIN, 30));
        icono.setPreferredSize(new Dimension(50, 50));
        icono.setHorizontalAlignment(JLabel.CENTER);
        
        // Descripción del logro
        JLabel desc = new JLabel(descripcion);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        desc.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        
        logroPanel.add(icono, BorderLayout.WEST);
        logroPanel.add(desc, BorderLayout.CENTER);
        
        return logroPanel;
    }
    
    private JPanel createFooterPanel() {
        JPanel footer = new JPanel(new FlowLayout());
        footer.setBackground(Color.WHITE);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        for (int i = 0; i < 3; i++) {
            JLabel dot = new JLabel("⬜");
            dot.setFont(new Font("SansSerif", Font.PLAIN, 24));
            footer.add(dot);
        }
        
        return footer;
    }
    
    private void volverClick() {
        try {
            new InicioFrame();
            dispose();
        } catch (Exception e) {
            System.err.println("Error al abrir InicioFrame: " + e.getMessage());
        }
    }
}