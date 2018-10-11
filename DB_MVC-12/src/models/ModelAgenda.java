package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private String nombre;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            this.setEmail(email);
            this.setNombre(nombre);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

    }

    public void moverPrimerRegistro() throws SQLException {
        System.out.print("Programa accion moverPrimerRegistro");
        rs.first();
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }

    public void moverSiguienteRegistro() throws SQLException {
        System.out.print("Programa accion moverSiguienteRegistro");
        if (!rs.isLast()) {
            rs.next();
        }
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }

    public void moverAnteriorRegistro() throws SQLException {
        System.out.print("Programa accion moverAnteriorRegistro");
        if (!rs.isFirst()) {
            rs.previous();
        }
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }

    public void moverUltimoRegistro() throws SQLException {
        System.out.print("Programa accion moverUltimoRegistro");
        rs.last();
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }

    public void insertarRegistro(String nombre, String email) throws SQLException {
        System.out.print("Programa accion insertarRegistro");
        st.executeUpdate("INSERT INTO contactos(nombre,email) VALUES" + "('" + nombre + "','" + email + "');");
        this.conectarDB();
    }

    public void modificarRegistro(String nombre, String email) throws SQLException {
        System.out.print("Programa accion modificarRegistro");
        String actualEmail = this.getEmail();
        st.executeUpdate("UPDATE contactos SET nombre='" + nombre + "',email='" + email + "' WHERE email='" + actualEmail + "';");
        this.conectarDB();
    }

    public void eliminarRegistro(String email) throws SQLException {
        System.out.print("Programa accion eliminarRegistro");
        st.executeUpdate("DELETE FROM contactos WHERE email='" + email + "';"); 
        this.conectarDB();
    }
}
