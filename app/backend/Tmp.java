package backend;

import models.Word;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class Tmp {
    static Connection conn;

    public static void insertWord(Word word) {
        String insertTableSQL = "INSERT INTO Word"
                + "(id, normalized_value, showed_count, unknown_count) VALUES"
                + "(?,?,?,?)";

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, Integer.parseInt(Long.toString(System.currentTimeMillis()).substring(5)));
            preparedStatement.setString(2, word.getNormalizedValue());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        String DB_DRIVER = "org.postgresql.Driver";
        String DB_CONNECTION = "jdbc:postgresql://46.101.250.217:5432/linguahack3words";
        String DB_USER = "postgres";
        String DB_PASSWORD = "k6EJRRL8";
        String sql = "SELECT * FROM text";
        conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        Set<String> words = new HashSet<>();
        int i = 0;

        try (Statement ps = conn.createStatement()) {
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
//                Integer id = rs.getInt("id");
//                String file_name = rs.getString("file_name");
//                String text = rs.getString("text");
                String normalized_text = rs.getString("normalized_text");
//                Integer showed_count = rs.getInt("showed_count");

                for (String nw: normalized_text.split(" ")) {
                    if (!words.contains(nw)) {
                        words.add(nw);
                        i++;

                        Word word = new Word();
                        word.setId(i);
                        word.setNormalizedValue(nw);
                        word.setShowedCount(0);
                        word.setUnknownCount(0);

                        insertWord(word);
                    }
                }
                System.out.println("Processed + " + i + " times.");
            }
            rs.close();
            System.out.println("Successfully selected current word_count");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to retrieve current word count");
        }
    }
}
