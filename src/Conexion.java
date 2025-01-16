import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    private static final String url = "jdbc:mysql://localhost:3306/ejemplo_crud";
    private static final String usuario = "root";
    private static final String password = "123456";

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, usuario, password);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
