package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PerfilFrame extends JFrame {
    private static final Color ROJO_UNAB = new Color(212, 31, 17);
    private static final Color AMARILLO_UNAB = new Color(231, 165, 39);
    private static final Font FONT_TITULO = new Font("SansSerif", Font.BOLD, 14);
    private static final Font FONT_NORMAL = new Font("SansSerif", Font.PLAIN, 13);
    
    private JFrame parentFrame;
    private JTextArea bioArea;
    private RegistroDatos datosUsuario;
    
    // Información del usuario
    private String nombreUsuario = "juan.garcia";
    private String nombreCompleto = "Juan García López";
    private String correo = "juan.garcia@unab.edu.co";
    private String carrera = "Ingeniería de Sistemas";
    private String edad = "22";
    private String cedula = "1094876543";
    private String celular = "3124567890";
    private String direccion = "Calle 45 #23-10";
    private String id = "10234567";
    private int nivel = 3;
    private String titulo = "Avanzado UNAB";

    public PerfilFrame() {
        this.parentFrame = null;
        cargarDatosUsuario();
        initComponents();
    }
    
    public PerfilFrame(JFrame parent) {
        this.parentFrame = parent;
        cargarDatosUsuario();
        initComponents();
    }
    
private void cargarDatosUsuario() {
    try {
        // Intentar obtener datos de RegistroDatos si existe
        if (RegistroDatos.nombre != null) {
            if (RegistroDatos.nombre != null && !RegistroDatos.nombre.isEmpty()) {
                nombreCompleto = RegistroDatos.nombre;
                nombreUsuario = nombreCompleto.toLowerCase().replace(" ", ".");
            }
        }
        if (RegistroDatos.correo != null && !RegistroDatos.correo.isEmpty()) {
            correo = RegistroDatos.correo;
        }
        if (RegistroDatos.carrera != null && !RegistroDatos.carrera.isEmpty()) {
            carrera = RegistroDatos.carrera;
        }
        if (RegistroDatos.edad != null && !RegistroDatos.edad.isEmpty()) {
            edad = RegistroDatos.edad;
        }
    } catch (Exception e) {
        // Si no existe RegistroDatos, usar datos por defecto
        System.out.println("Usando datos por defecto - RegistroDatos no disponible");
    }
}

    
    private void initComponents() {
        setTitle("Perfil de Usuario - " + nombreCompleto);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                volverAlInicio();
            }
        });

        setVisible(true);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ROJO_UNAB);
        header.setPreferredSize(new Dimension(1000, 80));

        // Panel izquierdo
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setBackground(ROJO_UNAB);
        leftPanel.add(createBackButton());
        leftPanel.add(createLogo());

        // Panel central con saludo
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        centerPanel.setBackground(ROJO_UNAB);
        JLabel saludoLabel = new JLabel("Bienvenido, " + nombreCompleto);
        saludoLabel.setForeground(Color.WHITE);
        saludoLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        centerPanel.add(saludoLabel);

        // Panel derecho
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        rightPanel.setBackground(ROJO_UNAB);
        rightPanel.add(createNotificationButton());

        header.add(leftPanel, BorderLayout.WEST);
        header.add(centerPanel, BorderLayout.CENTER);
        header.add(rightPanel, BorderLayout.EAST);
        return header;
    }
    
    private JLabel createBackButton() {
        JLabel volver = new JLabel("←");
        volver.setFont(new Font("SansSerif", Font.BOLD, 24));
        volver.setForeground(Color.WHITE);
        volver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        volver.setToolTipText("Volver al inicio");
        volver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { volverAlInicio(); }
            @Override
            public void mouseEntered(MouseEvent e) { volver.setForeground(new Color(200, 200, 200)); }
            @Override
            public void mouseExited(MouseEvent e) { volver.setForeground(Color.WHITE); }
        });
        return volver;
    }
    
    private JLabel createLogo() {
        try {
            ImageIcon icon = loadImageIcon("resources/logo_unab-902421018.png");
            if (icon != null) {
                Image scaledImage = icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
                return new JLabel(new ImageIcon(scaledImage));
            }
        } catch (Exception e) {
            // Fallback
        }
        JLabel unabLabel = new JLabel("UNAB");
        unabLabel.setForeground(Color.WHITE);
        unabLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        return unabLabel;
    }
    
    private JLabel createNotificationButton() {
        JLabel bell = new JLabel("🔔");
        bell.setForeground(Color.YELLOW);
        bell.setFont(new Font("SansSerif", Font.PLAIN, 24));
        bell.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bell.setToolTipText("Ver notificaciones");
        bell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { mostrarNotificaciones(); }
            @Override
            public void mouseEntered(MouseEvent e) { bell.setFont(new Font("SansSerif", Font.PLAIN, 26)); }
            @Override
            public void mouseExited(MouseEvent e) { bell.setFont(new Font("SansSerif", Font.PLAIN, 24)); }
        });
        return bell;
    }
    
    private JPanel createMainContent() {
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.add(createLeftPanel(), BorderLayout.WEST);
        mainContent.add(createRightPanel(), BorderLayout.CENTER);
        return mainContent;
    }
    
    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setPreferredSize(new Dimension(280, 520));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        leftPanel.add(createAvatarSection());
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(createUserInfoSection());
        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        leftPanel.add(createBioSection());
        leftPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        leftPanel.add(createSaveButton());
        leftPanel.add(Box.createVerticalGlue());
        
        return leftPanel;
    }
    
    private JPanel createAvatarSection() {
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.Y_AXIS));
        avatarPanel.setBackground(Color.WHITE);
        avatarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel perfil = createAvatarLabel();
        JButton cambiarFoto = new JButton("Cambiar foto");
        cambiarFoto.setFont(new Font("SansSerif", Font.PLAIN, 10));
        cambiarFoto.setBackground(AMARILLO_UNAB);
        cambiarFoto.setForeground(Color.WHITE);
        cambiarFoto.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cambiarFoto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cambiarFoto.setMaximumSize(new Dimension(100, 25));
        cambiarFoto.addActionListener(e -> cambiarFotoPerfil());

        avatarPanel.add(perfil);
        avatarPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        avatarPanel.add(cambiarFoto);
        
        return avatarPanel;
    }
    
    private JLabel createAvatarLabel() {
        JLabel perfil;
        try {
            ImageIcon icon = loadImageIcon("resources/perfil_usuario.jpg");
            if (icon != null) {
                Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                perfil = new JLabel(new ImageIcon(img));
            } else {
                throw new Exception("Usar avatar por defecto");
            }
        } catch (Exception e) {
            perfil = new JLabel("👤", JLabel.CENTER);
            perfil.setFont(new Font("SansSerif", Font.PLAIN, 60));
            perfil.setOpaque(true);
            perfil.setBackground(AMARILLO_UNAB);
        }
        
        perfil.setPreferredSize(new Dimension(150, 150));
        perfil.setMaximumSize(new Dimension(150, 150));
        perfil.setAlignmentX(Component.CENTER_ALIGNMENT);
        perfil.setBorder(BorderFactory.createLineBorder(ROJO_UNAB, 3));
        return perfil;
    }
    
    private JPanel createUserInfoSection() {
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(Color.WHITE);
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("Información Básica"));
        
        // Nombre completo
        JLabel nombreLabel = new JLabel("<html><b>Nombre:</b> " + nombreCompleto + "</html>");
        nombreLabel.setFont(FONT_NORMAL);
        nombreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Usuario
        JLabel usuarioLabel = new JLabel("<html><b>Usuario:</b> " + nombreUsuario + "</html>");
        usuarioLabel.setFont(FONT_NORMAL);
        usuarioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Carrera
        JLabel carreraLabel = new JLabel("<html><b>Programa:</b> " + carrera + "</html>");
        carreraLabel.setFont(FONT_NORMAL);
        carreraLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Edad
        JLabel edadLabel = new JLabel("<html><b>Edad:</b> " + edad + " años</html>");
        edadLabel.setFont(FONT_NORMAL);
        edadLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        userInfoPanel.add(nombreLabel);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userInfoPanel.add(usuarioLabel);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userInfoPanel.add(carreraLabel);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        userInfoPanel.add(edadLabel);
        
        return userInfoPanel;
    }
    
    private JTextArea createBioSection() {
        bioArea = new JTextArea("Apasionado por la tecnología y la innovación educativa. Siempre en busca de nuevos desafíos académicos.");
        bioArea.setFont(new Font("SansSerif", Font.PLAIN, 12));
        bioArea.setLineWrap(true);
        bioArea.setWrapStyleWord(true);
        bioArea.setEditable(true);
        bioArea.setBackground(Color.WHITE);
        bioArea.setBorder(BorderFactory.createTitledBorder("Acerca de mí"));
        bioArea.setRows(4);
        return bioArea;
    }
    
    private JButton createSaveButton() {
        JButton guardar = new JButton("Guardar cambios");
        guardar.setBackground(ROJO_UNAB);
        guardar.setForeground(Color.WHITE);
        guardar.setFont(new Font("SansSerif", Font.BOLD, 12));
        guardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        guardar.addActionListener(e -> guardarCambiosPerfil());
        return guardar;
    }
    
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(248, 248, 248));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        rightPanel.add(createContactInfoPanel());
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(createStatsPanel());
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(createButtonPanel());
        rightPanel.add(Box.createVerticalGlue());
        
        return rightPanel;
    }
    
    private JPanel createContactInfoPanel() {
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBackground(Color.WHITE);
        contactPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Información de Contacto"),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Información organizada en una tabla más limpia
        String[][] contactInfo = {
            {"📧 Correo:", correo},
            {"📱 Celular:", celular},
            {"🏠 Dirección:", direccion},
            {"🆔 ID Estudiante:", id},
            {"📄 C.C:", cedula},
            {"⭐ Nivel:", String.valueOf(nivel)},
            {"🏆 Título:", titulo}
        };
        
        for (String[] info : contactInfo) {
            JPanel rowPanel = new JPanel(new BorderLayout(10, 0));
            rowPanel.setBackground(Color.WHITE);
            rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
            
            JLabel keyLabel = new JLabel(info[0]);
            keyLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
            keyLabel.setPreferredSize(new Dimension(120, 20));
            
            JLabel valueLabel = new JLabel(info[1]);
            valueLabel.setFont(FONT_NORMAL);
            
            rowPanel.add(keyLabel, BorderLayout.WEST);
            rowPanel.add(valueLabel, BorderLayout.CENTER);
            
            contactPanel.add(rowPanel);
            contactPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        return contactPanel;
    }
    
    private JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Estadísticas APUNAB"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JLabel puntos = new JLabel("🏆 Tienes 1850 puntos APUNAB");
        puntos.setFont(new Font("SansSerif", Font.BOLD, 16));
        puntos.setForeground(ROJO_UNAB);
        
        JLabel meta = new JLabel("🎯 Te faltan 150 puntos para graduarte!");
        meta.setFont(FONT_TITULO);
        meta.setForeground(AMARILLO_UNAB);

        JProgressBar progreso = new JProgressBar(0, 2000);
        progreso.setValue(1850);
        progreso.setStringPainted(true);
        progreso.setString("92.5% completado");
        progreso.setForeground(ROJO_UNAB);

        String[] stats = {
            "📊 APUNAB promedio por semana: 35",
            "📈 APUNAB promedio por mes: 150",
            "📅 APUNAB promedio por semestre: 675",
            "🗓️ APUNAB promedio por año: 1350"
        };

        statsPanel.add(puntos);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        statsPanel.add(meta);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        statsPanel.add(progreso);
        statsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        for (String stat : stats) {
            JLabel lbl = new JLabel(stat);
            lbl.setFont(FONT_NORMAL);
            statsPanel.add(lbl);
        }

        return statsPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(248, 248, 248));

        String[][] buttons = {
            {"❓ Ayuda", "FAQ"},
            {"🏆 Ver mis logros", "LOGROS"},
            {"📊 Uso de APUNAB", "HISTORIAL"}
        };
        
        for (String[] btn : buttons) {
            buttonPanel.add(createStyledButton(btn[0], btn[1]));
        }

        return buttonPanel;
    }
    
    private JButton createStyledButton(String text, String action) {
        JButton button = new JButton(text);
        button.setBackground(AMARILLO_UNAB);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(160, 40));
        button.setBorder(BorderFactory.createBevelBorder(0));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        
        button.addActionListener(e -> handleButtonAction(action));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { button.setBackground(ROJO_UNAB); }
            @Override
            public void mouseExited(MouseEvent e) { button.setBackground(AMARILLO_UNAB); }
        });
        
        return button;
    }
    
    private void handleButtonAction(String action) {
        switch (action) {
            case "FAQ":
                abrirFAQFrame();
                break;
            case "LOGROS":
                abrirLogrosFrame();
                break;
            case "HISTORIAL":
                abrirUsoAPUNABFrame();
                break;
        }
    }
    
    private ImageIcon loadImageIcon(String imagePath) {
        String[] paths = {imagePath, "./" + imagePath, "src/" + imagePath};
        
        for (String path : paths) {
            try {
                File file = new File(path);
                if (file.exists()) {
                    ImageIcon icon = new ImageIcon(path);
                    if (icon.getIconWidth() > 0) return icon;
                }
            } catch (Exception e) {
                // Continue with next path
            }
        }
        
        try {
            java.net.URL imageURL = getClass().getClassLoader().getResource(imagePath);
            if (imageURL != null) return new ImageIcon(imageURL);
        } catch (Exception e) {
            // Resource loading failed
        }
        
        return null;
    }
    
    // Event handlers mejorados
    private void volverAlInicio() {
        try {
            if (parentFrame != null) {
                parentFrame.setVisible(true);
                parentFrame.toFront();
                parentFrame.requestFocus();
            } else {
                // Si no hay parentFrame, intentar crear InicioFrame
                SwingUtilities.invokeLater(() -> {
                    try {
                        new InicioFrame();
                    } catch (Exception e) {
                        System.err.println("Error al crear InicioFrame: " + e.getMessage());
                    }
                });
            }
            this.dispose();
        } catch (Exception e) {
            System.err.println("Error en volverAlInicio: " + e.getMessage());
        }
    }
    
    private void mostrarNotificaciones() {
        SwingUtilities.invokeLater(() -> {
            JDialog notifDialog = new JDialog(this, "Notificaciones", true);
            notifDialog.setSize(400, 300);
            notifDialog.setLocationRelativeTo(this);
            
            String[] notificaciones = {
                "🎉 ¡Felicidades! Has ganado 25 APUNAB",
                "📚 Nuevo evento: Competencia de programación",
                "⏰ Evento cultural mañana a las 2:00 PM", 
                "🏆 Has alcanzado el nivel 3"
            };
            
            JList<String> lista = new JList<>(notificaciones);
            lista.setFont(FONT_NORMAL);
            lista.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JScrollPane scroll = new JScrollPane(lista);
            notifDialog.add(scroll, BorderLayout.CENTER);
            
            JButton cerrar = new JButton("Cerrar");
            cerrar.addActionListener(e -> notifDialog.dispose());
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(cerrar);
            notifDialog.add(buttonPanel, BorderLayout.SOUTH);
            
            notifDialog.setVisible(true);
        });
    }
    
    private void cambiarFotoPerfil() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Imágenes", "jpg", "jpeg", "png", "gif"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, 
                "Foto actualizada: " + archivo.getName(),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void guardarCambiosPerfil() {
        String nuevaBio = bioArea.getText();
        JOptionPane.showMessageDialog(this, 
            "Cambios guardados exitosamente.",
            "Perfil actualizado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Métodos para abrir frames específicos - CORREGIDOS
    private void abrirFAQFrame() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Buscar constructor que acepte JFrame parent
                try {
                    java.lang.reflect.Constructor<?> constructor = 
                        Class.forName("ProyectoCompleto_InicioFix.Proyectocompleto.src.FAQFrame")
                             .getConstructor(JFrame.class);
                    constructor.newInstance(this);
                } catch (NoSuchMethodException e1) {
                    // Si no existe constructor con parent, intentar constructor sin parámetros
                    try {
                        java.lang.reflect.Constructor<?> constructor = 
                            Class.forName("ProyectoCompleto_InicioFix.Proyectocompleto.src.FAQFrame")
                                 .getConstructor();
                        constructor.newInstance();
                    } catch (Exception e2) {
                        // Si no existe la clase o constructores, mostrar dialog fallback
                        mostrarFAQDialog();
                    }
                }
            } catch (Exception e) {
                mostrarFAQDialog();
            }
        });
    }
    
    private void abrirLogrosFrame() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Buscar constructor que acepte JFrame parent
                try {
                    java.lang.reflect.Constructor<?> constructor = 
                        Class.forName("ProyectoCompleto_InicioFix.Proyectocompleto.src.LogrosFrame")
                             .getConstructor(JFrame.class);
                    constructor.newInstance(this);
                } catch (NoSuchMethodException e1) {
                    // Si no existe constructor con parent, intentar constructor sin parámetros
                    try {
                        java.lang.reflect.Constructor<?> constructor = 
                            Class.forName("ProyectoCompleto_InicioFix.Proyectocompleto.src.LogrosFrame")
                                 .getConstructor();
                        constructor.newInstance();
                    } catch (Exception e2) {
                        // Si no existe la clase o constructores, mostrar dialog fallback
                        mostrarLogrosDialog();
                    }
                }
            } catch (Exception e) {
                mostrarLogrosDialog();
            }
        });
    }
    
    private void abrirUsoAPUNABFrame() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Buscar constructor que acepte JFrame parent
                try {
                    java.lang.reflect.Constructor<?> constructor = 
                        Class.forName("ProyectoCompleto_InicioFix.Proyectocompleto.src.UsoAPUNABFrame")
                             .getConstructor(JFrame.class);
                    constructor.newInstance(this);
                } catch (NoSuchMethodException e1) {
                    // Si no existe constructor con parent, intentar constructor sin parámetros
                    try {
                        java.lang.reflect.Constructor<?> constructor = 
                            Class.forName("ProyectoCompleto_InicioFix.Proyectocompleto.src.UsoAPUNABFrame")
                                 .getConstructor();
                        constructor.newInstance();
                    } catch (Exception e2) {
                        // Si no existe la clase o constructores, mostrar dialog fallback
                        mostrarHistorialDialog();
                    }
                }
            } catch (Exception e) {
                mostrarHistorialDialog();
            }
        });
    }
    
    // Métodos fallback con dialogs (en caso de que los frames no existan)
    private void mostrarFAQDialog() {
        mostrarDialog("Ayuda - FAQ", 
            "PREGUNTAS FRECUENTES\n\n" +
            "¿Qué son los APUNAB?\n" +
            "Puntos obtenidos por participar en eventos universitarios.\n\n" +
            "¿Cómo gano APUNAB?\n" +
            "Participando en eventos deportivos, culturales y académicos.\n\n" +
            "¿Cuántos necesito?\n" +
            "2000 APUNAB para graduarte.");
    }
    
    private void mostrarLogrosDialog() {
        mostrarDialog("Mis Logros",
            "🏆 Participante Destacado - Nivel 3\n" +
            "🎯 1850 APUNAB acumulados\n" +
            "📚 5 eventos académicos completados\n" +
            "🏃 3 eventos deportivos completados\n" +
            "🎭 2 eventos culturales completados\n" +
            "⭐ Racha de 7 días participando");
    }
    
    private void mostrarHistorialDialog() {
        mostrarDialog("Uso de APUNAB",
            "RESUMEN DE ACTIVIDAD\n\n" +
            "• Esta semana: +35 APUNAB\n" +
            "• Este mes: +150 APUNAB\n" +
            "• Este semestre: +675 APUNAB\n\n" +
            "ÚLTIMAS ACTIVIDADES:\n" +
            "• Evento deportivo: +25 APUNAB\n" +
            "• Competencia cultural: +35 APUNAB\n" +
            "• Torneo académico: +15 APUNAB\n\n" +
            "Progreso: 92.5% hacia graduación");
    }
    
    private void mostrarDialog(String titulo, String mensaje) {
        JDialog dialog = new JDialog(this, titulo, true);
        dialog.setSize(450, 350);
        dialog.setLocationRelativeTo(this);
        
        JTextArea textArea = new JTextArea(mensaje);
        textArea.setFont(FONT_NORMAL);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(15, 15, 15, 15));
        
        JScrollPane scroll = new JScrollPane(textArea);
        dialog.add(scroll, BorderLayout.CENTER);
        
        JButton cerrar = new JButton("Cerrar");
        cerrar.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cerrar);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
}