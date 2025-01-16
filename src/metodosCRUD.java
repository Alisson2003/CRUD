import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class metodosCRUD{
    public void insertarUsuarios(int i, String nombre, String correo, int edad) {
        String query = "INSERT INTO usuarios (nombre, correo, edad) values (?,?,?)";
        try (Connection con = Conexion.getConnection();
        PreparedStatement ps = con.prepareStatement(query)){
            ps.setString(1, nombre);
            ps.setString(2, correo);
            ps.setInt(3, edad);
            ps.executeUpdate();
            System.out.println("Insercion exitosa");
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }


/*
    public String leerUsuarios() {
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
        String leerUsu = leerUsu(nombre,correo, edad);
        textArea1.setText(leerUsu);

        return query;
    }


*/


    public void modificarUsuario(int id, String nombre, String correo, int edad) {

    }

    public void eliminarUsuario(int id) {
        String query = "DELETE FROM usuarios where id=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Eliminacion exitosa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificarUsuario() {
    }
}



