import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JTextField idtextField1;
    private JTextField nombretextField2;
    private JTextField correotextField3;
    private JTextField edadtextField4;
    private JButton ingresarButton;
    private JButton mostrarButton;
    public JTextArea textArea1;
    private JButton actualizarButton;
    private JButton eliminarButton1;
    public JPanel mainPanel;

    metodosCRUD mC = new metodosCRUD();

    public Login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mC.insertarUsuarios(Integer.parseInt(idtextField1.getText()) ,nombretextField2.getText(),correotextField3.getText(), Integer.parseInt(edadtextField4.getText()));
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idtextField1.getText();
                String nombre = nombretextField2.getText();
                String correo = correotextField3.getText();
                String edad = edadtextField4.getText();

                String query = "UPDATE usuarios set nombre=?, correo=?, edad=? where id=?";
                try (Connection con = Conexion.getConnection();
                     PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setString(1, nombre);
                    ps.setString(2, correo);
                    ps.setInt(3, Integer.parseInt(edad));
                    ps.setInt(4, Integer.parseInt(id));
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                mC.modificarUsuario();
            }
        });

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder result = new StringBuilder(); // To accumulate all results

                String query = "SELECT * FROM usuarios";
                try (Connection con = Conexion.getConnection();
                     PreparedStatement ps = con.prepareStatement(query)) {
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        // For each user, append their data to the result
                        int id = rs.getInt("id");
                        String nombre = rs.getString("nombre");
                        String correo = rs.getString("correo");
                        int edad = rs.getInt("edad");

                        result.append(leerUsu(id, nombre, correo, edad)).append("\n\n");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                // Set the accumulated result to the JTextArea
                textArea1.setText(result.toString());
            }
        });

        eliminarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mC.eliminarUsuario(Integer.parseInt(idtextField1.getText()));
            }
        });
    }

    // Formats the user data
    private String leerUsu(int id,String nombre, String correo, int edad) {
        return "\n  ---- SISTEMA DE REGISTROS ----" +
                "\n Codgio ID: " + id +
                "\n Nombre: " + nombre +
                "\n Correo Electronico: " + correo +
                "\n Edad: " + edad;
    }
}
