package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistroFrame extends JFrame {

    private JTextField[] fields;
    private JPanel centerPanel;
    private JPanel formPanel;

    public RegistroFrame() {
        setTitle("Registro de Usuario - UNAB");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        createHeader();
        createRegistrationForm();

        // Listener para redimensionamiento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustFormSize();
            }
        });

        setVisible(true);
    }

    private void createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(900, 70));

        try {
            // Probamos diferentes rutas para el logo
            ImageIcon icon = null;
            
            try {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/logo_unab-902421018.png"));
            } catch (Exception e1) {
                try {
                    icon = new ImageIcon("resources/logo_unab-902421018.png");
                } catch (Exception e2) {
                    icon = new ImageIcon("logo_unab-902421018.png");
                }
            }
            
            if (icon != null && icon.getIconWidth() > 0) {
                JLabel logo = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)));
                header.add(logo);
            } else {
                throw new Exception("Logo no encontrado");
            }
            
        } catch (Exception e) {
            JLabel logoText = new JLabel("UNAB");
            logoText.setForeground(Color.WHITE);
            logoText.setFont(new Font("Arial", Font.BOLD, 32));
            logoText.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
            header.add(logoText);
        }

        add(header, BorderLayout.NORTH);
    }

    private void createRegistrationForm() {
        // Panel central con layout responsivo
        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        // Panel del formulario
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Título
        JLabel title = new JLabel("Regístrate");
        title.setForeground(new Color(212, 31, 17));
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(title);
        formPanel.add(Box.createVerticalStrut(30));

        // Crear campos del formulario
        String[] labels = { "Nombre Completo", "Correo", "Edad", "Carrera", "Usuario", "Clave" };
        fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            // Etiqueta
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("Arial", Font.PLAIN, 14));
            lbl.setForeground(new Color(212, 31, 17));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            formPanel.add(lbl);
            formPanel.add(Box.createVerticalStrut(5));

            // Campo de texto
            JTextField txt;
            if (i == 5) { // Campo de clave
                txt = new JPasswordField();
            } else {
                txt = new JTextField();
            }
            
            txt.setPreferredSize(new Dimension(300, 35));
            txt.setMaximumSize(new Dimension(300, 35));
            txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(225, 87, 79)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
            txt.setFont(new Font("Arial", Font.PLAIN, 14));
            txt.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            formPanel.add(txt);
            formPanel.add(Box.createVerticalStrut(20));
            fields[i] = txt;
        }

        // Link de recuperar cuenta
        JLabel recover = new JLabel("<html><u>Recuperar cuenta</u></html>");
        recover.setFont(new Font("Arial", Font.PLAIN, 12));
        recover.setForeground(new Color(225, 87, 79));
        recover.setAlignmentX(Component.CENTER_ALIGNMENT);
        recover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recover.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    new RecuperarCuentaFrame();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(RegistroFrame.this, 
                        "No se pudo abrir la ventana de recuperación", 
                        "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        formPanel.add(recover);
        formPanel.add(Box.createVerticalStrut(20));

        // Botón de crear cuenta
        JButton crearButton = new JButton("Crear Cuenta");
        crearButton.setPreferredSize(new Dimension(180, 45));
        crearButton.setMaximumSize(new Dimension(180, 45));
        crearButton.setBackground(new Color(231, 165, 39));
        crearButton.setForeground(Color.WHITE);
        crearButton.setFont(new Font("Arial", Font.BOLD, 16));
        crearButton.setFocusPainted(false);
        crearButton.setBorder(BorderFactory.createEmptyBorder());
        crearButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        crearButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Efecto hover
        crearButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                crearButton.setBackground(new Color(200, 140, 30));
            }
            public void mouseExited(MouseEvent evt) {
                crearButton.setBackground(new Color(231, 165, 39));
            }
        });

        crearButton.addActionListener(e -> performRegistration());
        formPanel.add(crearButton);

        // Agregar el panel del formulario al centro
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(formPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void performRegistration() {
        // Validar que todos los campos estén llenos
        for (int i = 0; i < fields.length; i++) {
            String text = fields[i].getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Por favor complete el campo: " + getFieldName(i), 
                    "Campo vacío", JOptionPane.WARNING_MESSAGE);
                fields[i].requestFocus();
                return;
            }
        }

        // Validaciones específicas
        if (!isValidEmail(fields[1].getText().trim())) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese un correo válido", 
                "Correo inválido", JOptionPane.WARNING_MESSAGE);
            fields[1].requestFocus();
            return;
        }

        if (!isValidAge(fields[2].getText().trim())) {
            JOptionPane.showMessageDialog(this, 
                "Por favor ingrese una edad válida (número entre 16 y 100)", 
                "Edad inválida", JOptionPane.WARNING_MESSAGE);
            fields[2].requestFocus();
            return;
        }

        if (fields[4].getText().trim().length() < 4) {
            JOptionPane.showMessageDialog(this, 
                "El usuario debe tener al menos 4 caracteres", 
                "Usuario muy corto", JOptionPane.WARNING_MESSAGE);
            fields[4].requestFocus();
            return;
        }

        if (fields[5].getText().length() < 6) {
            JOptionPane.showMessageDialog(this, 
                "La contraseña debe tener al menos 6 caracteres", 
                "Contraseña muy corta", JOptionPane.WARNING_MESSAGE);
            fields[5].requestFocus();
            return;
        }

        // Guardar datos
        try {
            RegistroDatos.nombre = fields[0].getText().trim();
            RegistroDatos.correo = fields[1].getText().trim();
            RegistroDatos.edad = fields[2].getText().trim();
            RegistroDatos.carrera = fields[3].getText().trim();
            RegistroDatos.usuario = fields[4].getText().trim();
            RegistroDatos.clave = fields[5].getText();

            JOptionPane.showMessageDialog(this, 
                "¡Cuenta creada exitosamente!\nYa puedes iniciar sesión con tu usuario: " + RegistroDatos.usuario, 
                "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
            
            new LoginFrame();
            dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear la cuenta: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String getFieldName(int index) {
        String[] names = { "Nombre Completo", "Correo", "Edad", "Carrera", "Usuario", "Clave" };
        return names[index];
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.length() > 5;
    }

    private boolean isValidAge(String ageStr) {
        try {
            int age = Integer.parseInt(ageStr);
            return age >= 16 && age <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void adjustFormSize() {
        if (formPanel == null || fields == null) return;
        
        int width = getWidth();
        
        // Ajustar el tamaño de los campos según el tamaño de la ventana
        int fieldWidth = Math.min(400, Math.max(250, width / 3));
        int buttonWidth = Math.min(200, Math.max(150, fieldWidth / 2));
        
        Dimension fieldSize = new Dimension(fieldWidth, 35);
        Dimension buttonSize = new Dimension(buttonWidth, 45);
        
        // Ajustar campos de texto
        for (JTextField field : fields) {
            if (field != null) {
                field.setPreferredSize(fieldSize);
                field.setMaximumSize(fieldSize);
            }
        }
        
        // Ajustar botón
        Component[] components = formPanel.getComponents();
        for (Component comp : components) {
            if (comp instanceof JButton) {
                comp.setPreferredSize(buttonSize);
                ((JComponent) comp).setMaximumSize(buttonSize);
            }
        }
        
        revalidate();
        repaint();
    }

    // Método main para pruebas (opcional)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e) {
                e.printStackTrace();
            }
            new RegistroFrame();
        });
    }
}