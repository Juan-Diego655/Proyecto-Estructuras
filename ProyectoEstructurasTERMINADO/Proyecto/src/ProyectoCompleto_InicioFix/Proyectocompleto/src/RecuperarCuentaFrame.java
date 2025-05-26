package ProyectoCompleto_InicioFix.Proyectocompleto.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecuperarCuentaFrame extends JFrame {
    
    private JTextField campoUsuario;
    private JButton recuperarBtn;
    
    public RecuperarCuentaFrame() {
        initializeFrame();
        createComponents();
        setupLayout();
        addEventListeners();
        setVisible(true);
    }
    
    private void initializeFrame() {
        setTitle("Recupera tu cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setResizable(true);
    }
    
    private void createComponents() {
        // No es necesario inicializar aquí, se hace en setupLayout()
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel header
        JPanel header = createHeader();
        add(header, BorderLayout.NORTH);
        
        // Panel central con el formulario
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel footer (opcional para espacio)
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 50));
        footer.setBackground(Color.WHITE);
        add(footer, BorderLayout.SOUTH);
    }
    
    private JPanel createHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(212, 31, 17));
        header.setPreferredSize(new Dimension(0, 70));
        
        try {
            ImageIcon icon = new ImageIcon(".src/resources/logo_unab-902421018.png");
            if (icon.getIconWidth() > 0) {
                JLabel logo = new JLabel(new ImageIcon(
                    icon.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH)
                ));
                header.add(logo);
            } else {
                throw new Exception("Logo no encontrado");
            }
        } catch (Exception e) {
            JLabel logoText = new JLabel("UNAB");
            logoText.setFont(new Font("SansSerif", Font.BOLD, 24));
            logoText.setForeground(Color.WHITE);
            logoText.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
            header.add(logoText);
        }
        
        return header;
    }
    
    private JPanel createCenterPanel() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        
        // Título
        JLabel titulo = new JLabel("Recupera tu cuenta");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(new Color(212, 31, 17));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtítulo
        JLabel subtitulo = new JLabel("Usuario o correo asociado a tu cuenta");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(new Color(100, 100, 100));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Campo de texto
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoUsuario.setMaximumSize(new Dimension(300, 35));
        campoUsuario.setPreferredSize(new Dimension(300, 35));
        campoUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(225, 87, 79)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Botón recuperar
        recuperarBtn = new JButton("Recuperar");
        recuperarBtn.setPreferredSize(new Dimension(200, 45));
        recuperarBtn.setMaximumSize(new Dimension(200, 45));
        recuperarBtn.setBackground(new Color(231, 165, 39));
        recuperarBtn.setForeground(Color.WHITE);
        recuperarBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        recuperarBtn.setFocusPainted(false);
        recuperarBtn.setBorder(BorderFactory.createEmptyBorder());
        recuperarBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recuperarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Efecto hover para el botón
        addHoverEffect(recuperarBtn);
        
        // Agregar componentes con espaciado
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(titulo);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(subtitulo);
        centerPanel.add(Box.createVerticalStrut(25));
        centerPanel.add(campoUsuario);
        centerPanel.add(Box.createVerticalStrut(30));
        centerPanel.add(recuperarBtn);
        centerPanel.add(Box.createVerticalGlue());
        
        return centerPanel;
    }
    
    private void addHoverEffect(JButton button) {
        Color originalColor = button.getBackground();
        Color hoverColor = originalColor.darker();
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }
        });
    }
    
    private void addEventListeners() {
        recuperarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarRecuperacion();
            }
        });
        
        // Permitir presionar Enter en el campo de texto
        campoUsuario.addActionListener(e -> procesarRecuperacion());
    }
    
    private void procesarRecuperacion() {
        String inputText = campoUsuario.getText().trim();
        
        if (inputText.isEmpty()) {
            mostrarMensajeError("Por favor ingrese su usuario o correo.", "Campo requerido");
            campoUsuario.requestFocus();
            return;
        }
        
        // Validación básica de formato de email si contiene @
        if (inputText.contains("@") && !esEmailValido(inputText)) {
            mostrarMensajeError("Por favor ingrese un correo electrónico válido.", "Formato inválido");
            campoUsuario.requestFocus();
            return;
        }
        
        // Simular procesamiento
        mostrarMensajeExito("Se ha enviado un enlace de recuperación a su correo registrado.", "Correo enviado");
        
        // Limpiar campo después del éxito
        campoUsuario.setText("");
    }
    
    private boolean esEmailValido(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
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
    
    // Método main para pruebas (opcional)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } catch (Exception e) {
                e.printStackTrace();
            }
            new RecuperarCuentaFrame();
        });
    }
}