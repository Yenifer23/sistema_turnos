import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroTurnos extends JPanel {
    private JTextField txtNombre, txtApellido, txtDni, txtDoctor, txtEspecialidad, txtMatricula;

    public RegistroTurnos() {
        setLayout(null);
        setBackground(new Color(245, 245, 255));

        Font font = new Font("Segoe UI", Font.PLAIN, 16);

        JLabel[] etiquetas = {
            new JLabel("Nombre:"), new JLabel("Apellido:"), new JLabel("DNI:"),
            new JLabel("Doctor:"), new JLabel("Especialidad:"), new JLabel("Matrícula:")
        };

        JTextField[] campos = {
            txtNombre = new JTextField(), txtApellido = new JTextField(), txtDni = new JTextField(),
            txtDoctor = new JTextField(), txtEspecialidad = new JTextField(), txtMatricula = new JTextField()
        };

        int y = 30;
        for (int i = 0; i < etiquetas.length; i++) {
            etiquetas[i].setBounds(40, y, 120, 25);
            etiquetas[i].setFont(font);
            add(etiquetas[i]);

            campos[i].setBounds(170, y, 250, 30);
            campos[i].setFont(font);
            add(campos[i]);

            y += 50;
        }

        JButton btnGuardar = new JButton("Guardar Turno");
        btnGuardar.setBounds(170, y, 250, 40);
        btnGuardar.setBackground(new Color(255, 182, 193));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(font);
        btnGuardar.addActionListener(e -> guardarTurno());
        add(btnGuardar);

        // Guardar con Enter desde el último campo
        txtMatricula.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guardarTurno();
                }
            }
        });
    }

    private void guardarTurno() {
        try {
            Connection con = ConexionBD.conectar();
            String sql = "INSERT INTO turnos (nombre, apellido, dni, doctor, especialidad, matricula) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtApellido.getText());
            ps.setString(3, txtDni.getText());
            ps.setString(4, txtDoctor.getText());
            ps.setString(5, txtEspecialidad.getText());
            ps.setString(6, txtMatricula.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Turno guardado con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar turno: " + e.getMessage());
        }
    }
}
