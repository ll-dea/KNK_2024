package Service;

import Model.User;
import database.DatabaseUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Base64;

//public class PasswordService {
//
//    public boolean changePassword(String currentPassword, String newPassword, String repeatPassword) {
//        if (currentPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
//            return false;
//        }
//
//        if (!newPassword.equals(repeatPassword)) {
//            User user = Session.getCurrentUser();
//            if (user != null) {
//                String firstName = user.getFirstName();
//                String lastName = user.getLastName();
//                String email = user.getEmail();
//                DatabaseUtil connector = new DatabaseUtil();
//                Connection connection = connector.getconnection();
//                try {
//
//
//
//                    Statement statement = connection.createStatement();
//                   String query = "UPDATE `users` SET `password` = '"+newPassword+"' WHERE (`email` = '"+email+"' );";
//                    int querycount = statement.executeUpdate(query);
//                    if (querycount>0){
//                        return true;
//                    }else return false;
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//             }
//
//
//        return true;
//        }
//        return false;
//    }
//}
//

public class PasswordService {

    // Method to hash a password using SHA-256 algorithm
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean changePassword(String currentPassword, String newPassword, String repeatPassword) {
        if (currentPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty()) {
            System.out.println("Empty passwords provided.");
            return false;
        }

        User user = Session.getCurrentUser();
        if (user == null) {
            System.out.println("No user session found.");
            return false;
        }

        String email = user.getEmail();
        String hashedCurrentPassword = hashPassword(currentPassword);
        String hashedNewPassword = hashPassword(newPassword);
        if (hashedCurrentPassword == null || hashedNewPassword == null) {
            System.out.println("Error in hashing passwords.");
            return false;
        }

        if (!hashedCurrentPassword.equals(user.getPassword())) {
            System.out.println("Current password is incorrect.");
            return false;
        }

        DatabaseUtil connector = new DatabaseUtil();
        try (Connection connection = connector.getconnection();
             Statement statement = connection.createStatement()) {
            String query = "UPDATE `users` SET `password` = '" + hashedNewPassword + "' WHERE `email` = '" + email + "'";
            int queryCount = statement.executeUpdate(query);
            if (queryCount > 0) {
                System.out.println("Password updated successfully.");
                return true;
            } else {
                System.out.println("Failed to update password.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception occurred: " + e.getMessage());
            return false;
        }
    }

}

