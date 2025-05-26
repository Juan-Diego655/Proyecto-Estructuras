package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class HistorialFrame extends JFrame {
    private JFrame parentFrame; // Referencia al frame padre

    // Constructor que recibe la referencia del frame padre
    public HistorialFrame(JFrame parent) {
        this.parentFrame = parent;
        initComponents();
    }
    
    // Constructor por defecto (para compatibilidad)
    public HistorialFrame() {
        this.parentFrame = null;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Historial de eventos");
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
        
        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Agregar componentes al frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Agregar listener para cuando se cierre la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                volverClick();
            }
        });

        setVisible(true);
    }
    
    private JPanel createMainHeader() {
        JPanel mainHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainHeader.setBackground(new Color(212, 31, 17));
        mainHeader.setPreferredSize(new Dimension(900, 70));
        
        try {
            // Intentar cargar desde diferentes rutas posibles
            ImageIcon icon = loadImageIcon("resources/logo_unab.png");
            if (icon != null) {
                Image scaledImage = icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                JLabel logo = new JLabel(new ImageIcon(scaledImage));
                mainHeader.add(logo);
            } else {
                throw new Exception("No se pudo cargar el logo");
            }
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
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                volver.setForeground(new Color(200, 200, 200)); // Efecto hover
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                volver.setForeground(Color.WHITE);
            }
        });
        
        JLabel title = new JLabel(" Historial de apuestas", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        titlePanel.add(volver, BorderLayout.WEST);
        titlePanel.add(title, BorderLayout.CENTER);
        
        return titlePanel;
    }
    
    private JPanel createContentPanel() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Agregar estadísticas
        content.add(createStatsPanel());
        content.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Agregar eventos
        addEventItems(content);
        
        return content;
    }
    
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.WHITE);
        
        JLabel apunabLabel = new JLabel("APUNAB obtenidos: 145");
        apunabLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        apunabLabel.setForeground(new Color(212, 31, 17));
        apunabLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel apuestasLabel = new JLabel("Cantidad de apuestas: 3");
        apuestasLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        apuestasLabel.setForeground(new Color(212, 31, 17));
        apuestasLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        statsPanel.add(apunabLabel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        statsPanel.add(apuestasLabel);
        
        return statsPanel;
    }
    
    private void addEventItems(JPanel content) {
        String[] rutas = {
            "resources/PlazoletaPrincipal.jpg",
            "resources/Casona.jpg",
            "resources/biblioteca.jpg"
        };

        String[] descripciones = {
            "Apuesta en evento deportivo estudiantil.\nLugar: Plazoleta Principal UNAB.\nFecha: 15 de mayo 2025.\nResultado: Ganaste 25 APUNAB",
            "Apuesta en competencia cultural.\nLugar: Sede de la Casona.\nFecha: 8 de abril 2025.\nResultado: Ganaste 35 APUNAB",
            "Apuesta en torneo de conocimiento.\nLugar: Biblioteca Central.\nFecha: 22 de marzo 2025.\nResultado: Perdiste 15 APUNAB"
        };

        for (int i = 0; i < rutas.length; i++) {
            JPanel item = createEventItem(rutas[i], descripciones[i]);
            content.add(item);
            if (i < rutas.length - 1) {
                content.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }
    }
    
    private JPanel createEventItem(String imagePath, String description) {
        JPanel item = new JPanel(new BorderLayout());
        item.setBackground(Color.WHITE);
        item.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(212, 31, 17), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        // Imagen
        JLabel imgLabel = createImageLabel(imagePath);
        item.add(imgLabel, BorderLayout.WEST);

        // Descripción
        JTextArea desc = new JTextArea(description);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setBackground(Color.WHITE);
        desc.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        item.add(desc, BorderLayout.CENTER);

        return item;
    }
    
    private JLabel createImageLabel(String imagePath) {
        try {
            // Intentar cargar la imagen desde diferentes rutas
            ImageIcon icon = loadImageIcon(imagePath);
            if (icon != null) {
                Image img = icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
                return new JLabel(new ImageIcon(img));
            } else {
                throw new Exception("No se pudo cargar la imagen: " + imagePath);
            }
        } catch (Exception e) {
            System.err.println("Error cargando imagen " + imagePath + ": " + e.getMessage());
            JLabel noImg = new JLabel("Sin imagen");
            noImg.setPreferredSize(new Dimension(200, 120));
            noImg.setHorizontalAlignment(JLabel.CENTER);
            noImg.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            noImg.setBackground(new Color(240, 240, 240));
            noImg.setOpaque(true);
            return noImg;
        }
    }
    
    // Método mejorado para cargar imágenes con múltiples rutas posibles
    private ImageIcon loadImageIcon(String imagePath) {
        // Lista de rutas posibles donde buscar las imágenes
        String[] possiblePaths = {
            imagePath,                          // Ruta original
            "./" + imagePath,                   // Ruta desde directorio actual
            "src/" + imagePath,                 // Desde carpeta src
            "src/main/" + imagePath,            // Maven structure
            System.getProperty("user.dir") + "/" + imagePath  // Ruta absoluta
        };
        
        for (String path : possiblePaths) {
            try {
                File file = new File(path);
                if (file.exists()) {
                    ImageIcon icon = new ImageIcon(path);
                    // Verificar que la imagen se cargó correctamente
                    if (icon.getIconWidth() > 0) {
                        System.out.println("Imagen cargada desde: " + path);
                        return icon;
                    }
                }
            } catch (Exception e) {
                // Continuar con la siguiente ruta
            }
        }
        
        // Si no se encuentra en rutas de archivo, intentar como recurso
        try {
            java.net.URL imageURL = getClass().getClassLoader().getResource(imagePath);
            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(imageURL);
                System.out.println("Imagen cargada como recurso: " + imagePath);
                return icon;
            }
        } catch (Exception e) {
            // Falló la carga como recurso también
        }
        
        System.err.println("No se pudo encontrar la imagen: " + imagePath);
        return null;
    }
    
    private void volverClick() {
        try {
            // Si tenemos referencia al frame padre, lo mostramos
            if (parentFrame != null) {
                parentFrame.setVisible(true);
                parentFrame.toFront();
                parentFrame.requestFocus();
            } else {
                // Si no tenemos referencia, creamos uno nuevo
                SwingUtilities.invokeLater(() -> {
                    try {
                        new InicioFrame();
                    } catch (Exception e) {
                        System.err.println("Error al crear InicioFrame: " + e.getMessage());
                        e.printStackTrace();
                    }
                });
            }
            
            // Cerrar esta ventana
            this.dispose();
            
        } catch (Exception e) {
            System.err.println("Error en volverClick: " + e.getMessage());
            e.printStackTrace();
        }
    }
}