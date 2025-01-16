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
    private JTextArea textArea1;
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
                    throw new RuntimeException();
                }
                mC.modificarUsuario();

            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mC.leerUsuarios();
                textArea1.setText(leerUsuarios());
            }
        });
        eliminarButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mC.eliminarUsuario(Integer.parseInt(idtextField1.getText()));
            }
        });
    }

    private String leerUsuarios() {
        String query = "SELECT * FROM usuarios";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
                System.out.println(rs.getString("correo"));
                System.out.println(rs.getInt("edad"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query;
    }
}
