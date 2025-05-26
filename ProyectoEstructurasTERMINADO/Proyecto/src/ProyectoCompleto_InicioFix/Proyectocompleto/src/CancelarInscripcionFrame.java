package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CancelarInscripcionFrame extends JFrame {
    
    private JTextField[] campos;
    private JButton eliminarBtn;
    private JLabel reenlace;
    private final String[] etiquetas = {
        "Usuario", 
        "Evento a eliminar", 
        "Razón para darse de baja"
    };
    
    public CancelarInscripcionFrame() {
        initializeFrame();
        setupLayout();
        addEventListeners();
        setVisible(true);
    }
    
    private void initializeFrame() {
        setTitle("Borrar inscripción");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 550);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel header
        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);
        
        // Panel central con el formulario
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel footer
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 30));
        footer.setBackground(Color.WHITE);
        add(footer, BorderLayout.SOUTH);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(0, 70));
        
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
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        // Título
        JLabel titulo = new JLabel("Borrar inscripción");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 26));
        titulo.setForeground(new Color(212, 31, 17));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel del formulario
        JPanel formPanel = createFormPanel();
        
        // Link de reenlace
        reenlace = new JLabel("<html>Inscríbete a otro <b><font color='#D41F11'>Evento</font></b></html>");
        reenlace.setFont(new Font("SansSerif", Font.PLAIN, 14));
        reenlace.setAlignmentX(Component.CENTER_ALIGNMENT);
        reenlace.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addHoverEffectToLink(reenlace);
        
        // Botón eliminar
        eliminarBtn = new JButton("Eliminar Inscripción");
        eliminarBtn.setPreferredSize(new Dimension(200, 45));
        eliminarBtn.setMaximumSize(new Dimension(200, 45));
        eliminarBtn.setBackground(new Color(220, 53, 69)); // Color más apropiado para eliminar
        eliminarBtn.setForeground(Color.WHITE);
        eliminarBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        eliminarBtn.setFocusPainted(false);
        eliminarBtn.setBorder(BorderFactory.createEmptyBorder());
        eliminarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        eliminarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addHoverEffectToButton(eliminarBtn);
        
        // Agregar componentes con espaciado
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(titulo);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(formPanel);
        centerPanel.add(Box.createVerticalStrut(25));
        centerPanel.add(reenlace);
        centerPanel.add(Box.createVerticalStrut(25));
        centerPanel.add(eliminarBtn);
        centerPanel.add(Box.createVerticalGlue());
        
        return centerPanel;
    }
    
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        campos = new JTextField[etiquetas.length];
        
        for (int i = 0; i < etiquetas.length; i++) {
            // Panel para cada campo
            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
            fieldPanel.setBackground(Color.WHITE);
            fieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            fieldPanel.setMaximumSize(new Dimension(400, 70));
            
            // Etiqueta
            JLabel lbl = new JLabel(etiquetas[i]);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 14));
            lbl.setForeground(new Color(212, 31, 17));
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // Campo de texto
            JTextField txt;
            if (i == 2) { // Campo de razón - más alto para texto largo
                txt = new JTextField();
                txt.setPreferredSize(new Dimension(400, 40));
                txt.setMaximumSize(new Dimension(400, 40));
            } else {
                txt = new JTextField();
                txt.setPreferredSize(new Dimension(400, 35));
                txt.setMaximumSize(new Dimension(400, 35));
            }
            
            txt.setFont(new Font("SansSerif", Font.PLAIN, 14));
            txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(225, 87, 79)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            txt.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // Placeholder effect
            addPlaceholderEffect(txt, getPlaceholder(i));
            
            campos[i] = txt;
            
            fieldPanel.add(lbl);
            fieldPanel.add(Box.createVerticalStrut(8));
            fieldPanel.add(txt);
            
            formPanel.add(fieldPanel);
            if (i < etiquetas.length - 1) {
                formPanel.add(Box.createVerticalStrut(20));
            }
        }
        
        return formPanel;
    }
    
    private String getPlaceholder(int index) {
        switch (index) {
            case 0: return "Ingrese su nombre de usuario";
            case 1: return "Nombre del evento del cual desea darse de baja";
            case 2: return "Explique brevemente el motivo de la cancelación";
            default: return "";
        }
    }
    
    private void addPlaceholderEffect(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
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
                link.setText("<html><u>Inscríbete a otro <b><font color='#D41F11'>Evento</font></b></u></html>");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                link.setText("<html>Inscríbete a otro <b><font color='#D41F11'>Evento</font></b></html>");
            }
        });
    }
    
    private void addEventListeners() {
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarEliminacion();
            }
        });
        
        reenlace.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirInscripcionFrame();
            }
        });
        
        // Permitir Enter en los campos para enviar el formulario
        for (JTextField campo : campos) {
            campo.addActionListener(e -> procesarEliminacion());
        }
    }
    
    private void procesarEliminacion() {
        // Validar campos
        for (int i = 0; i < campos.length; i++) {
            String texto = campos[i].getText().trim();
            String placeholder = getPlaceholder(i);
            
            if (texto.isEmpty() || texto.equals(placeholder)) {
                mostrarMensajeError("Por favor complete todos los campos.", "Campos requeridos");
                campos[i].requestFocus();
                return;
            }
        }
        
        // Validación adicional para el campo de usuario
        String usuario = campos[0].getText().trim();
        if (usuario.length() < 3) {
            mostrarMensajeError("El nombre de usuario debe tener al menos 3 caracteres.", "Usuario inválido");
            campos[0].requestFocus();
            return;
        }
        
        // Confirmación antes de eliminar
        int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de que desea eliminar su inscripción al evento \"" + campos[1].getText().trim() + "\"?\n" +
            "Esta acción no se puede deshacer.",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (respuesta == JOptionPane.YES_OPTION) {
            // Simular procesamiento
            mostrarMensajeExito(
                "Su inscripción al evento \"" + campos[1].getText().trim() + "\" ha sido eliminada correctamente.\n" +
                "Recibirá una confirmación por correo electrónico.",
                "Inscripción eliminada"
            );
            
            // Limpiar campos después del éxito
            limpiarFormulario();
        }
    }
    
    private void limpiarFormulario() {
        for (int i = 0; i < campos.length; i++) {
            campos[i].setText(getPlaceholder(i));
            campos[i].setForeground(Color.GRAY);
        }
    }
    
    private void abrirInscripcionFrame() {
        try {
            // Intentar abrir el frame de inscripción
            SwingUtilities.invokeLater(() -> {
                // Aquí podrías crear una nueva instancia de InscripcionFrame
                // new InscripcionFrame();
                
                // Por ahora, mostrar un mensaje
                JOptionPane.showMessageDialog(
                    this,
                    "Redirigiendo a la página de inscripciones...",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
                );
            });
        } catch (Exception e) {
            mostrarMensajeError("Error al abrir la página de inscripciones.", "Error");
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
            new CancelarInscripcionFrame();
        });
    }
}
