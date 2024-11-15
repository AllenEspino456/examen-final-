package zooologico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Data_animal {
    private final String URL = "jdbc:mysql://localhost:3306/zoologico";
    private final String USER = "root";
    private final String PASSWORD = "eva01";

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    
    public void insertAnimal(Animal animal) {
        String sql = "INSERT INTO Animal (No_animal, Nombre, Nacimiento, Especie) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, animal.getNo_animal());
            pstmt.setString(2, animal.getNombre());
            pstmt.setString(3, animal.getNacimiento());
            pstmt.setString(4, animal.getEspecie());
            pstmt.executeUpdate();
            System.out.println("Animal insertado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Actualizar los datos de un animal
    public void updateAnimal(Animal animal) {
        String sql = "UPDATE Animal SET Nombre = ?, Nacimiento = ?, Especie = ? WHERE No_animal = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, animal.getNombre());
            pstmt.setString(2, animal.getNacimiento());
            pstmt.setString(3, animal.getEspecie());
            pstmt.setInt(4, animal.getNo_animal());
            pstmt.executeUpdate();
            System.out.println("Animal actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Eliminar un animal
    public void deleteAnimal(int no_animal) {
        String sql = "DELETE FROM Animal WHERE No_animal = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, no_animal);
            pstmt.executeUpdate();
            System.out.println("Animal eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Seleccionar un animal por su número
    public Animal selectAnimal(int no_animal) {
        String sql = "SELECT No_animal, Nombre, Nacimiento, Especie FROM Animal WHERE No_animal = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, no_animal);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Animal animal = new Animal();
                animal.setNo_animal(rs.getInt("No_animal"));
                animal.setNombre(rs.getString("Nombre"));
                animal.setNacimiento(rs.getString("Nacimiento"));
                animal.setEspecie(rs.getString("Especie"));
                return animal;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
