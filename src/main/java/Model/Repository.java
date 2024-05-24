//package Model;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Repository {
//    private Connection connection;
//
//    public Repository(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void shtoOrar(OrariKonsultimeve orari) throws SQLException {
//        String query = "INSERT INTO oraret_konsultimeve (lenda, data, ora, salla) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, orari.getLenda());
//            statement.setString(2, orari.getData().toString());
//            statement.setString(3, orari.getOra());
//            statement.setString(4, orari.getSalla());
//            statement.executeUpdate();
//        }
//    }
//
//    public List<OrariKonsultimeve> lexoTëGjithaOraret() throws SQLException {
//        List<OrariKonsultimeve> oraret = new ArrayList<>();
//        String query = "SELECT * FROM oraret_konsultimeve";
//        try (PreparedStatement statement = connection.prepareStatement(query);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                String lenda = resultSet.getString("lenda");
//                LocalDate data = LocalDate.parse(resultSet.getString("data"));
//                String ora = resultSet.getString("ora");
//                String salla = resultSet.getString("salla");
//                OrariKonsultimeve orari = new OrariKonsultimeve(lenda, data, ora, salla);
//                oraret.add(orari);
//            }
//        }
//        return oraret;
//    }
//
//    public void fshijOrar(OrariKonsultimeve orari) throws SQLException {
//        String query = "DELETE FROM oraret_konsultimeve WHERE lenda=? AND data=? AND ora=?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, orari.getLenda());
//            statement.setString(2, orari.getData().toString());
//            statement.setString(3, orari.getOra());
//            statement.executeUpdate();
//        }
//    }
//
//    public void perditesoOrar(OrariKonsultimeve orari) throws SQLException {
//        String query = "UPDATE oraret_konsultimeve SET salla=? WHERE lenda=? AND data=? AND ora=?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setString(1, orari.getSalla());
//            statement.setString(2, orari.getLenda());
//            statement.setString(3, orari.getData().toString());
//            statement.setString(4, orari.getOra());
//            statement.executeUpdate();
//        }
//    }
//
//}
