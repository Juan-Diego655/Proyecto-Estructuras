package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InscripcionFrame extends JFrame {
    
    private JTextField[] fields;
    private JLabel linkBaja;
    private JButton crearBtn;
    private final String[] labels = {
        "Nombre Completo", "Correo", "Edad",
        "Carrera", "Usuario", "Evento a inscribir"
    };

    public InscripcionFrame() {
        initializeFrame();
        setupLayout();
        addEventListeners();
        setVisible(true);
    }
    
    private void initializeFrame() {
        setTitle("Inscripción de evento");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 650);
        setMinimumSize(new Dimension(700, 600));
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel header
        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);
        
        // Panel central responsivo
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel footer para espacio
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 30));
        footer.setBackground(Color.WHITE);
        add(footer, BorderLayout.SOUTH);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(0, 70));
        
        // Cargar logo con múltiples rutas
        ImageIcon logoIcon = null;
        String[] rutasLogo = {
            "resources/logo_unab-902421018.png",
            "./resources/logo_unab-902421018.png", 
            "src/resources/logo_unab-902421018.png",
            "./src/resources/logo_unab-902421018.png"
        };
        
        for (String ruta : rutasLogo) {
            try {
                java.io.File file = new java.io.File(ruta);
                if (file.exists()) {
                    logoIcon = new ImageIcon(ruta);
                    if (logoIcon.getIconWidth() > 0 && logoIcon.getIconHeight() > 0) {
                        break;
                    }
                }
            } catch (Exception e) {
                // Continuar con la siguiente ruta
            }
        }
        
        if (logoIcon != null && logoIcon.getIconWidth() > 0) {
            JLabel logo = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
            header.add(logo);
        } else {
            // Fallback si no se encuentra el logo
            JLabel logoText = new JLabel("UNAB");
            logoText.setForeground(Color.WHITE);
            logoText.setFont(new Font("SansSerif", Font.BOLD, 24));
            logoText.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            header.add(logoText);
        }
        
        return header;
    }
    
    private JPanel createCenterPanel() {
        // Panel principal con BoxLayout para centrado vertical
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        
        // Título
        JLabel title = new JLabel("Inscripción de evento");
        title.setForeground(new Color(212, 31, 17));
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel del formulario centrado
        JPanel formPanel = createFormPanel();
        
        // Link de baja
        linkBaja = new JLabel("<html>Darte de <font color='#D41F11'><b>BAJA?</b></font></html>");
        linkBaja.setFont(new Font("SansSerif", Font.PLAIN, 14));
        linkBaja.setForeground(new Color(120, 120, 120));
        linkBaja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkBaja.setAlignmentX(Component.CENTER_ALIGNMENT);
        addHoverEffectToLink(linkBaja);
        
        // Botón crear
        crearBtn = new JButton("Crear Inscripción");
        crearBtn.setPreferredSize(new Dimension(200, 45));
        crearBtn.setMaximumSize(new Dimension(200, 45));
        crearBtn.setBackground(new Color(231, 165, 39));
        crearBtn.setForeground(Color.WHITE);
        crearBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        crearBtn.setFocusPainted(false);
        crearBtn.setBorder(BorderFactory.createEmptyBorder());
        crearBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        crearBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addHoverEffectToButton(crearBtn);
        
        // Agregar componentes con espaciado
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(40));
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(25));
        mainPanel.add(linkBaja);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(crearBtn);
        mainPanel.add(Box.createVerticalGlue());
        
        return mainPanel;
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        fields = new JTextField[labels.length];
        
        for (int i = 0; i < labels.length; i++) {
            // Panel para cada campo
            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
            fieldPanel.setBackground(Color.WHITE);
            fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            fieldPanel.setMaximumSize(new Dimension(400, 65));
            
            // Etiqueta
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lbl.setForeground(new Color(212, 31, 17));
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // Campo de texto
            JTextField txt = new JTextField();
            txt.setPreferredSize(new Dimension(400, 35));
            txt.setMaximumSize(new Dimension(400, 35));
            txt.setFont(new Font("SansSerif", Font.PLAIN, 14));
            txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(225, 87, 79)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            txt.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // Agregar placeholder
            addPlaceholderEffect(txt, getPlaceholder(i));
            
            fields[i] = txt;
            
            fieldPanel.add(lbl);
            fieldPanel.add(Box.createVerticalStrut(8));
            fieldPanel.add(txt);
            
            formPanel.add(fieldPanel);
            if (i < labels.length - 1) {
                formPanel.add(Box.createVerticalStrut(20));
            }
        }
        
        return formPanel;
    }
    
    private String getPlaceholder(int index) {
        switch (index) {
            case 0: return "Ingrese su nombre completo";
            case 1: return "ejemplo@correo.com";
            case 2: return "Edad en años (18-99)";
            case 3: return "Nombre de su carrera universitaria";
            case 4: return "Su nombre de usuario";
            case 5: return "Nombre del evento al que desea inscribirse";
            default: return "";
        }
    }
    
    private void addPlaceholderEffect(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent evt) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    
    private void addHoverEffectToButton(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = originalColor.darker();
        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalColor);
            }
        });
    }
    
    private void addHoverEffectToLink(JLabel link) {
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                link.setText("<html><u>Darte de <font color='#D41F11'><b>BAJA?</b></font></u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                link.setText("<html>Darte de <font color='#D41F11'><b>BAJA?</b></font></html>");
            }
        });
    }
    
    private void addEventListeners() {
        crearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarInscripcion();
            }
        });
        
        linkBaja.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                abrirCancelarInscripcion();
            }
        });
        
        // Permitir Enter en los campos para enviar el formulario
        for (JTextField field : fields) {
            field.addActionListener(e -> procesarInscripcion());
        }
    }
    
    private void procesarInscripcion() {
        // Validar todos los campos
        for (int i = 0; i < fields.length; i++) {
            String texto = fields[i].getText().trim();
            String placeholder = getPlaceholder(i);
            
            if (texto.isEmpty() || texto.equals(placeholder)) {
                mostrarMensajeError("Por favor complete todos los campos.", "Campos requeridos");
                fields[i].requestFocus();
                return;
            }
        }
        
        // Validaciones específicas
        if (!validarCamposEspecificos()) {
            return;
        }
        
        // Confirmación de inscripción
        String evento = fields[5].getText().trim();
        String nombre = fields[0].getText().trim();
        
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Confirma su inscripción al evento \"" + evento + "\"?\n" +
            "Participante: " + nombre,
            "Confirmar inscripción",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (respuesta == JOptionPane.YES_OPTION) {
            mostrarMensajeExito(
                "¡Inscripción completada con éxito!\n" +
                "Participante: " + nombre + "\n" +
                "Evento: " + evento + "\n\n" +
                "Recibirá una confirmación por correo electrónico.",
                "Inscripción exitosa"
            );
            dispose();
        }
    }
    
    private boolean validarCamposEspecificos() {
        // Validar email
        String email = fields[1].getText().trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            mostrarMensajeError("Por favor ingrese un correo electrónico válido.", "Email inválido");
            fields[1].requestFocus();
            return false;
        }
        
        // Validar edad
        String edadStr = fields[2].getText().trim();
        try {
            int edad = Integer.parseInt(edadStr);
            if (edad < 16 || edad > 99) {
                mostrarMensajeError("La edad debe estar entre 16 y 99 años.", "Edad inválida");
                fields[2].requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            mostrarMensajeError("Por favor ingrese una edad válida (solo números).", "Edad inválida");
            fields[2].requestFocus();
            return false;
        }
        
        // Validar nombre completo (al menos nombre y apellido)
        String nombre = fields[0].getText().trim();
        if (nombre.split("\\s+").length < 2) {
            mostrarMensajeError("Por favor ingrese su nombre completo (nombre y apellido).", "Nombre incompleto");
            fields[0].requestFocus();
            return false;
        }
        
        // Validar usuario (mínimo 3 caracteres)
        String usuario = fields[4].getText().trim();
        if (usuario.length() < 3) {
            mostrarMensajeError("El nombre de usuario debe tener al menos 3 caracteres.", "Usuario inválido");
            fields[4].requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void abrirCancelarInscripcion() {
        try {
            SwingUtilities.invokeLater(() -> {
                new CancelarInscripcionFrame();
            });
        } catch (Exception e) {
            mostrarMensajeError(
                "No se pudo abrir la ventana de cancelación.", 
                "Error"
            );
        }
    }
    
    private void mostrarMensajeError(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            titulo,
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    private void mostrarMensajeExito(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            titulo,
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    // Método main para pruebas
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e) {
                e.printStackTrace();
            }
            new InscripcionFrame();
        });
    }
}