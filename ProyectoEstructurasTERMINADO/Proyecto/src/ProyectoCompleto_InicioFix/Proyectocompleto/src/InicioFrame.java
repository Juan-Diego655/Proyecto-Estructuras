package ProyectoCompleto_InicioFix.Proyectocompleto.src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InicioFrame extends JFrame {

    public InicioFrame() {
        setTitle("Inicio - UNAB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Encabezado
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(1000, 80));

        // Logo en el lado izquierdo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setOpaque(false);
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        
        // Crear el logo con el nuevo nombre
        ImageIcon logoIcon = cargarLogo();
        
        if (logoIcon == null) {
            // Fallback: crear logo text estilizado
            JLabel logoText = new JLabel("unab");
            logoText.setForeground(Color.WHITE);
            logoText.setFont(new Font("Arial", Font.BOLD, 26));
            logoText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            logoPanel.add(logoText);
        } else {
            JLabel logo = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
            logoPanel.add(logo);
        }
        
        header.add(logoPanel, BorderLayout.WEST);

        // Panel de iconos en el lado derecho
        JPanel iconsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        iconsPanel.setOpaque(false);
        iconsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        
        // Botón de notificaciones con ventana emergente
        JButton notifBtn = crearBotonIcono("🔔", "Notificaciones");
        notifBtn.addActionListener(e -> mostrarNotificaciones());
        
        // Botón de perfil
        JButton perfilBtn = crearBotonIcono("👤", "Perfil");
        perfilBtn.addActionListener(event -> abrirFrame(() -> new PerfilFrame(), "Error al abrir Perfil"));
        
        iconsPanel.add(notifBtn);
        iconsPanel.add(Box.createHorizontalStrut(10));
        iconsPanel.add(perfilBtn);
        header.add(iconsPanel, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        crearContenido();
        setVisible(true);
    }

    // Método mejorado para crear botones de iconos
    private JButton crearBotonIcono(String icono, String tooltip) {
        JButton btn = new JButton(icono);
        btn.setBackground(new Color(231, 165, 39));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        btn.setPreferredSize(new Dimension(40, 40));
        btn.setToolTipText(tooltip);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(231, 165, 39).brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(231, 165, 39));
            }
        });
        
        return btn;
    }

    // Método para cargar el logo actualizado
    private ImageIcon cargarLogo() {
        String[] rutasLogo = {
            "/resources/logo_unab-902421018.png",
            "/logo_unab-902421018.png",
            "/images/logo_unab-902421018.png",
            "resources/logo_unab-902421018.png",
            "images/logo_unab-902421018.png",
            "logo_unab-902421018.png"
        };
        
        for (String ruta : rutasLogo) {
            try {
                ImageIcon icon;
                if (ruta.startsWith("/")) {
                    // Intentar cargar desde resources del classpath
                    java.net.URL imgURL = getClass().getResource(ruta);
                    if (imgURL != null) {
                        icon = new ImageIcon(imgURL);
                        if (icon.getIconWidth() != -1 && icon.getIconHeight() != -1) {
                            return icon;
                        }
                    }
                } else {
                    // Intentar cargar desde ruta relativa
                    icon = new ImageIcon(ruta);
                    if (icon.getIconWidth() != -1 && icon.getIconHeight() != -1) {
                        return icon;
                    }
                }
            } catch (Exception e) {
                // Continuar con la siguiente ruta
                System.out.println("No se pudo cargar logo desde: " + ruta);
            }
        }
        
        return null;
    }

    // Método para crear el contenido con layout responsivo
    private void crearContenido() {
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.WHITE);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Panel superior para saldo y botones
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        
        // Panel de saldo
        JPanel saldoPanel = new JPanel();
        saldoPanel.setLayout(new BoxLayout(saldoPanel, BoxLayout.Y_AXIS));
        saldoPanel.setBackground(Color.WHITE);
        
        JLabel saldoLabel = new JLabel("Saldo de APUNAB");
        saldoLabel.setForeground(Color.GRAY);
        saldoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel saldoValor = new JLabel("$0.00");
        saldoValor.setFont(new Font("SansSerif", Font.BOLD, 24));
        saldoValor.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        saldoPanel.add(saldoLabel);
        saldoPanel.add(Box.createVerticalStrut(5));
        saldoPanel.add(saldoValor);
        
        topPanel.add(saldoPanel, BorderLayout.NORTH);
        
        // Panel de botones de acciones con GridLayout responsivo
        JPanel botonesPanel = new JPanel(new GridBagLayout());
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        String[] tooltips = {"Historial", "Top 20", "Inscripción", "Mis Logros"};
        String[] iconos = {"📚", "🏆", "✏️", "🎯"};
        Runnable[] actions = {
            () -> abrirFrame(() -> new HistorialFrame(), "Error al abrir Historial"),
            () -> abrirFrame(() -> new Top20Frame(), "Error al abrir Top 20"),
            () -> abrirFrame(() -> new InscripcionFrame(), "Error al abrir Inscripción"),
            () -> abrirFrame(() -> new LogrosFrame(), "Error al abrir Logros")
        };

        for (int i = 0; i < tooltips.length; i++) {
            final int index = i;
            JButton btn = crearBotonAccion(tooltips[i], iconos[i]);
            btn.addActionListener(evt -> actions[index].run());
            
            gbc.gridx = i;
            gbc.gridy = 0;
            botonesPanel.add(btn, gbc);
        }
        
        topPanel.add(botonesPanel, BorderLayout.CENTER);
        
        // Botón de detalles de cuenta
        JButton detallesBtn = new JButton("Detalles de mi cuenta  ▶");
        detallesBtn.setBackground(new Color(240, 230, 255));
        detallesBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
        detallesBtn.setFocusPainted(false);
        detallesBtn.setHorizontalAlignment(SwingConstants.LEFT);
        detallesBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        detallesBtn.setPreferredSize(new Dimension(400, 40));
        detallesBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        detallesBtn.addActionListener(evt -> abrirFrame(() -> new PerfilFrame(), "Error al abrir Perfil"));
        
        JPanel detallesContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 20));
        detallesContainer.setBackground(Color.WHITE);
        detallesContainer.add(detallesBtn);
        
        topPanel.add(detallesContainer, BorderLayout.SOUTH);
        
        mainContent.add(topPanel, BorderLayout.NORTH);

        // Panel de eventos
        JPanel eventosContainer = new JPanel(new BorderLayout());
        eventosContainer.setBackground(Color.WHITE);
        eventosContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        JLabel eventosLabel = new JLabel("Próximos eventos");
        eventosLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        eventosContainer.add(eventosLabel, BorderLayout.NORTH);
        
        // Panel de imágenes de eventos con GridLayout responsivo
        JPanel eventosPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        eventosPanel.setBackground(Color.WHITE);
        eventosPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        String[] nombresImagenes = {"auditorio.jpg", "musunab.jpg", "puente.jpg"};
        String[] titulosEventos = {"Conferencia en Auditorio", "Exposición MUSUNAB", "Evento en el Puente"};

        for (int i = 0; i < nombresImagenes.length; i++) {
            JPanel eventoPanel = crearPanelEventoResponsivo(nombresImagenes[i], titulosEventos[i]);
            eventosPanel.add(eventoPanel);
        }
        
        eventosContainer.add(eventosPanel, BorderLayout.CENTER);
        mainContent.add(eventosContainer, BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
    }

    // Método para crear botones de acción estilizados con iconos
    private JButton crearBotonAccion(String texto, String icono) {
        JButton btn = new JButton("<html><center>" + icono + "<br>" + texto + "</center></html>");
        btn.setPreferredSize(new Dimension(100, 70));
        btn.setMinimumSize(new Dimension(100, 70));
        btn.setBackground(new Color(231, 165, 39));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.ORANGE.darker(), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        btn.setFont(new Font("SansSerif", Font.BOLD, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        
        // Efecto hover
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(231, 165, 39).brighter());
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(231, 165, 39));
            }
        });
        
        return btn;
    }

    // Método para crear panel de evento responsivo
    private JPanel crearPanelEventoResponsivo(String nombreImagen, String titulo) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        // Panel para la imagen que se adapte al tamaño disponible
        JPanel imagenContainer = new JPanel(new BorderLayout());
        imagenContainer.setBackground(Color.WHITE);
        imagenContainer.setPreferredSize(new Dimension(250, 140));
        
        // Cargar imagen del evento
        ImageIcon imgIcon = cargarImagenEvento(nombreImagen);
        JLabel imgLabel;
        
        if (imgIcon != null) {
            imgLabel = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (imgIcon != null) {
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        
                        // Calcular dimensiones manteniendo proporción
                        int panelWidth = getWidth();
                        int panelHeight = getHeight();
                        int imgWidth = imgIcon.getIconWidth();
                        int imgHeight = imgIcon.getIconHeight();
                        
                        double scale = Math.min((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);
                        int scaledWidth = (int) (imgWidth * scale);
                        int scaledHeight = (int) (imgHeight * scale);
                        
                        int x = (panelWidth - scaledWidth) / 2;
                        int y = (panelHeight - scaledHeight) / 2;
                        
                        g2d.drawImage(imgIcon.getImage(), x, y, scaledWidth, scaledHeight, this);
                        g2d.dispose();
                    }
                }
            };
        } else {
            imgLabel = new JLabel("Imagen no disponible", SwingConstants.CENTER);
            imgLabel.setBackground(Color.LIGHT_GRAY);
            imgLabel.setOpaque(true);
        }
        
        imagenContainer.add(imgLabel, BorderLayout.CENTER);
        
        JLabel tituloLabel = new JLabel(titulo, SwingConstants.CENTER);
        tituloLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(8, 5, 0, 5));
        
        panel.add(imagenContainer, BorderLayout.CENTER);
        panel.add(tituloLabel, BorderLayout.SOUTH);
        
        return panel;
    }

    // Método para cargar imágenes de eventos
    private ImageIcon cargarImagenEvento(String nombreImagen) {
        String[] rutasImagen = {
            "/resources/" + nombreImagen,
            "/" + nombreImagen,
            "/images/" + nombreImagen,
            "resources/" + nombreImagen,
            "images/" + nombreImagen,
            nombreImagen
        };
        
        for (String ruta : rutasImagen) {
            try {
                ImageIcon icon;
                if (ruta.startsWith("/")) {
                    java.net.URL imgURL = getClass().getResource(ruta);
                    if (imgURL != null) {
                        icon = new ImageIcon(imgURL);
                        if (icon.getIconWidth() != -1 && icon.getIconHeight() != -1) {
                            return icon;
                        }
                    }
                } else {
                    icon = new ImageIcon(ruta);
                    if (icon.getIconWidth() != -1 && icon.getIconHeight() != -1) {
                        return icon;
                    }
                }
            } catch (Exception e) {
                System.out.println("No se pudo cargar imagen desde: " + ruta);
            }
        }
        
        return null;
    }

    // Método genérico para abrir frames con manejo de errores
    private void abrirFrame(FrameSupplier supplier, String mensajeError) {
        try {
            JFrame frame = supplier.get();
            frame.setVisible(true);
        } catch (Exception ex) {
            System.err.println(mensajeError + ": " + ex.getMessage());
            JOptionPane.showMessageDialog(this, 
                mensajeError + "\n" + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    // Interface funcional para crear frames
    @FunctionalInterface
    private interface FrameSupplier {
        JFrame get() throws Exception;
    }

    // Método para mostrar ventana de notificaciones mejorada
    private void mostrarNotificaciones() {
        JDialog dialog = new JDialog(this, "Centro de Notificaciones", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        JLabel titulo = new JLabel("🔔 Notificaciones");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(titulo);
        
        panel.add(Box.createVerticalStrut(15));
        
        String[] notificaciones = {
            "📚 Nueva apuesta disponible en Cálculo I",
            "🎯 Recordatorio: Apuesta en el CSU el viernes",
            "🏆 Has desbloqueado un nuevo logro",
            "📅 Próximo evento: Conferencia de Ingeniería",
            "💰 Recarga de APUNAB completada"
        };
        
        for (String notif : notificaciones) {
            JPanel notifPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            notifPanel.setBackground(new Color(248, 249, 250));
            notifPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            notifPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            
            JLabel notifLabel = new JLabel(notif);
            notifLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            notifPanel.add(notifLabel);
            
            panel.add(notifPanel);
            panel.add(Box.createVerticalStrut(5));
        }
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        dialog.add(scrollPane);
        dialog.setVisible(true);
    }
}