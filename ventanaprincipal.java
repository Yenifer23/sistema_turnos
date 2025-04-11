import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JPanel panelContenido;
    private JButton botonRegistrarTurno, botonVerTurnos;

    public VentanaPrincipal() {
        // Colores UI
        UIManager.put("Button.disabledText", Color.BLACK);

        setTitle("Sistema de Turnos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel lateral izquierdo con imagen de fondo
        PanelConImagen menuPanel = new PanelConImagen("C:\\Users\\yenif\\OneDrive\\Documentos\\2025\\programar\\imagen de sistema d turnos\\images.jpg");
        menuPanel.setLayout(new GridLayout(1, 1));
        menuPanel.setPreferredSize(new Dimension(200, 0));

        JLabel etiquetaMenu = new JLabel("Menú", SwingConstants.CENTER);
        etiquetaMenu.setForeground(Color.WHITE);
        etiquetaMenu.setFont(new Font("Segoe UI", Font.BOLD, 30));
        menuPanel.add(etiquetaMenu);

        Font font = new Font("Segoe UI", Font.BOLD, 18);

        panelContenido = new JPanel();
        panelContenido.setLayout(new BorderLayout());
        panelContenido.add(crearPanelBienvenida());

        // Panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(192, 192, 192));
        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));

        // Botones
        botonRegistrarTurno = new JButton("Registrar Turno");
        botonVerTurnos = new JButton("Ver Turnos");

        configurarBoton(botonRegistrarTurno, font, new Color(255, 102, 102), Color.BLACK);
        configurarBoton(botonVerTurnos, font, new Color(255, 102, 102), Color.BLACK);

        botonRegistrarTurno.setEnabled(false);
        botonVerTurnos.setEnabled(false);

        botonRegistrarTurno.addActionListener(e -> mostrarPanel(new RegistroTurnos()));
        botonVerTurnos.addActionListener(e -> mostrarPanel(new MostrarTurnos()));

        panelInferior.add(botonRegistrarTurno);
        panelInferior.add(botonVerTurnos);

        add(menuPanel, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private JPanel crearPanelBienvenida() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelLogo.setOpaque(false);

        try {
            ImageIcon iconoLogo = new ImageIcon("C:\\Users\\yenif\\OneDrive\\Documentos\\2025\\programar\\imagen de sistema d turnos\\Captura de pantalla 2025-04-10 205854.png");
            Image imagen = iconoLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel etiquetaLogo = new JLabel(new ImageIcon(imagen));
            panelLogo.add(etiquetaLogo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel.add(panelLogo, BorderLayout.NORTH);

        JPanel contenedorCentral = new JPanel(new GridBagLayout());
        contenedorCentral.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 10, 0);

        JPanel contenedorFormulario = new JPanel();
        contenedorFormulario.setOpaque(false);
        contenedorFormulario.setLayout(new BoxLayout(contenedorFormulario, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Sistemas de turnos ADYAR");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenedorFormulario.add(label);
        contenedorFormulario.add(Box.createVerticalStrut(10));

        JLabel labelUsuario = new JLabel("Nombre de usuario:");
        labelUsuario.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoUsuario = new JTextField(15);
        campoUsuario.setMaximumSize(new Dimension(200, 30));
        campoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelContraseña = new JLabel("Contraseña:");
        labelContraseña.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField campoContraseña = new JPasswordField(15);
        campoContraseña.setMaximumSize(new Dimension(200, 30));
        campoContraseña.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botonLogin = new JButton("Iniciar sesión");
        botonLogin.setBackground(new Color(255, 102, 102));
        botonLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonLogin.setForeground(Color.BLACK);
        botonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonLogin.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String contraseña = new String(campoContraseña.getPassword());

            if (usuario.equals("admin") && contraseña.equals("admin")) {
                JOptionPane.showMessageDialog(panel, "¡Bienvenido!");
                botonRegistrarTurno.setEnabled(true);
                botonVerTurnos.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrectos");
            }
        });

        contenedorFormulario.add(Box.createVerticalStrut(10));
        contenedorFormulario.add(labelUsuario);
        contenedorFormulario.add(campoUsuario);
        contenedorFormulario.add(Box.createVerticalStrut(10));
        contenedorFormulario.add(labelContraseña);
        contenedorFormulario.add(campoContraseña);
        contenedorFormulario.add(Box.createVerticalStrut(20));
        contenedorFormulario.add(botonLogin);

        gbc.gridy = 0;
        contenedorCentral.add(contenedorFormulario, gbc);
        panel.add(contenedorCentral, BorderLayout.CENTER);

        return panel;
    }

    private void mostrarPanel(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void configurarBoton(JButton boton, Font font, Color bg, Color fg) {
        boton.setPreferredSize(new Dimension(160, 50));
        boton.setBackground(bg);
        boton.setForeground(fg);
        boton.setFont(font);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }

    // Clase interna para fondo con imagen
    class PanelConImagen extends JPanel {
        private Image imagenFondo;

        public PanelConImagen(String rutaImagen) {
            try {
                ImageIcon icono = new ImageIcon(rutaImagen);
                imagenFondo = icono.getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagenFondo != null) {
                g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}

