package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JTextField userField;
    private JPasswordField passwordField;
    private JPanel centerPanel;
    private JPanel loginPanel;

    public LoginFrame() {
        setTitle("Inicio de Sesión - UNAB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        createHeader();
        createLoginPanel();
        
        // Listener para redimensionamiento
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustLoginPanelSize();
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
            
            // Intento 1: desde resources en classpath
            try {
                icon = new ImageIcon(getClass().getClassLoader().getResource("resources/logo_unab-902421018.png"));
            } catch (Exception e1) {
                // Intento 2: desde resources sin classpath
                try {
                    icon = new ImageIcon("resources/logo_unab-902421018.png");
                } catch (Exception e2) {
                    // Intento 3: directamente desde la carpeta del proyecto
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
            // Si no encuentra el logo, muestra texto alternativo con estilo
            JLabel logoText = new JLabel("UNAB");
            logoText.setForeground(Color.WHITE);
            logoText.setFont(new Font("Arial", Font.BOLD, 32));
            logoText.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
            header.add(logoText);
            System.out.println("Logo no encontrado en ninguna ruta, usando texto alternativo");
        }

        add(header, BorderLayout.NORTH);
    }

    private void createLoginPanel() {
        // Panel central con layout responsivo
        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        // Panel de login centrado
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Título
        JLabel titulo = new JLabel("Inicia sesión en tu cuenta");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(192, 0, 0));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(titulo);
        loginPanel.add(Box.createVerticalStrut(30));

        // Campo Usuario
        createUserField();
        
        // Campo Contraseña
        createPasswordField();

        // Botón de Login
        createLoginButton();

        // Enlaces
        createLinks();

        // Agregar el panel de login al centro con GridBagLayout para centrarlo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(loginPanel, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void createUserField() {
        JLabel userLbl = new JLabel("Usuario");
        userLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        userLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(userLbl);
        loginPanel.add(Box.createVerticalStrut(5));

        userField = new JTextField();
        userField.setPreferredSize(new Dimension(280, 35));
        userField.setMaximumSize(new Dimension(280, 35));
        userField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(192, 0, 0)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        userField.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(userField);
        loginPanel.add(Box.createVerticalStrut(20));
    }

    private void createPasswordField() {
        JLabel passLbl = new JLabel("Contraseña");
        passLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        passLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(passLbl);
        loginPanel.add(Box.createVerticalStrut(5));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(280, 35));
        passwordField.setMaximumSize(new Dimension(280, 35));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(192, 0, 0)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Enter key para login
        passwordField.addActionListener(e -> performLogin());
        
        loginPanel.add(passwordField);
        loginPanel.add(Box.createVerticalStrut(25));
    }

    private void createLoginButton() {
        JButton btnLogin = new JButton("Acceder");
        btnLogin.setPreferredSize(new Dimension(160, 40));
        btnLogin.setMaximumSize(new Dimension(160, 40));
        btnLogin.setBackground(new Color(192, 0, 0));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setFocusPainted(false);
        
        // Efecto hover para el botón
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnLogin.setBackground(new Color(150, 0, 0));
            }
            public void mouseExited(MouseEvent evt) {
                btnLogin.setBackground(new Color(192, 0, 0));
            }
        });
        
        // Action Listener del botón
        btnLogin.addActionListener(e -> performLogin());
        
        loginPanel.add(btnLogin);
        loginPanel.add(Box.createVerticalStrut(20));
    }

    private void createLinks() {
        JPanel linksPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        linksPanel.setBackground(Color.WHITE);
        linksPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel recuperarCuenta = new JLabel("<html><u>Recupera tu Cuenta</u></html>");
        recuperarCuenta.setForeground(new Color(212, 31, 17));
        recuperarCuenta.setFont(new Font("Arial", Font.PLAIN, 12));
        recuperarCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recuperarCuenta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    new RecuperarCuentaFrame();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "No se pudo abrir la ventana de recuperación", 
                        "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JLabel separador = new JLabel("|");
        separador.setForeground(Color.GRAY);

        JLabel registrarse = new JLabel("<html><u>Registrarte</u></html>");
        registrarse.setForeground(new Color(212, 31, 17));
        registrarse.setFont(new Font("Arial", Font.PLAIN, 12));
        registrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registrarse.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    new RegistroFrame();
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(LoginFrame.this, 
                        "No se pudo abrir la ventana de registro", 
                        "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        linksPanel.add(recuperarCuenta);
        linksPanel.add(separador);
        linksPanel.add(registrarse);
        
        loginPanel.add(linksPanel);
    }

    private void performLogin() {
        String usuario = userField.getText().trim();
        String clave = new String(passwordField.getPassword());

        // Validación básica
        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un usuario", "Error", JOptionPane.WARNING_MESSAGE);
            userField.requestFocus();
            return;
        }
        
        if (clave.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una contraseña", "Error", JOptionPane.WARNING_MESSAGE);
            passwordField.requestFocus();
            return;
        }

        try {
            if (usuario.equals(RegistroDatos.usuario) && clave.equals(RegistroDatos.clave)) {
                JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                new InicioFrame();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField.setText(""); // Limpiar contraseña incorrecta
                userField.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al verificar credenciales: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adjustLoginPanelSize() {
        if (loginPanel == null) return;
        
        int width = getWidth();
        
        // Ajustar el tamaño de los campos según el tamaño de la ventana
        int fieldWidth = Math.min(400, Math.max(250, width / 4));
        int buttonWidth = Math.min(200, Math.max(120, fieldWidth / 2));
        
        Dimension fieldSize = new Dimension(fieldWidth, 35);
        Dimension buttonSize = new Dimension(buttonWidth, 40);
        
        if (userField != null) {
            userField.setPreferredSize(fieldSize);
            userField.setMaximumSize(fieldSize);
        }
        
        if (passwordField != null) {
            passwordField.setPreferredSize(fieldSize);
            passwordField.setMaximumSize(fieldSize);
        }
        
        // Ajustar botones
        Component[] components = loginPanel.getComponents();
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
            new LoginFrame();
        });
    }
}