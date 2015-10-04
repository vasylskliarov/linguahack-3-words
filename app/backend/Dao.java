package backend;

import models.Text;
import models.Word;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by hlib on 03.10.15.
 */
public class Dao {

    private final static Long[] STUB_TEXT_IDS = {-695500318L, 994661804L, 687910408L};
    final Random random = new Random();

    public List<Text> getAllTexts() {
        return Text.findAll();
    }

    public Long getRandomTextId() {
        // call Text method to query random text id
        return getStubTextId();
    }

    private Long getStubTextId() {
        int index = random.nextInt(STUB_TEXT_IDS.length);
        return STUB_TEXT_IDS[index];
    }

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://46.101.250.217:5432/linguahack3words";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "k6EJRRL8";
    private static Connection dbConnection = getDBConnection();

    private static Connection getDBConnection() {

        Connection dbConnection = null;

        try {

            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {

            System.out.println(e.getMessage());

        }

        try {

            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

        return dbConnection;

    }

    public void insertWord(Word word) {

        String insertTableSQL = "INSERT INTO Word"
                + "(id, normalized_value, showed_count, unknown_count) VALUES"
                + "(?,?,?,?)";

        try {

            PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, Integer.parseInt(Long.toString(System.currentTimeMillis()).substring(5)));
            preparedStatement.setString(2, word.getNormalizedValue());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            System.out.println("Record is inserted into DBUSER table!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
