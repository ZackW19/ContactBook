package pl.zwa;

import java.sql.*;

public class Main_test {

    public static void main(String[] args) {

        MysqlConnector connector = MysqlConnector.getInstance();

        try {
            //1.
            //Statement statement = connector.getConnection().createStatement();
            //INSERT
            //statement.execute( "INSERT INTO book VALUES(0,'KsiazkazJavy','Jan Nowak', 123, '2017-01-30')");
            //SELECT
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE pages < 200 ");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE pages > 200 ORDER BY author LIMIT 1 ");

            //2.
            //podobnie jak w SSIS - ? :)
            //zdecydowanie lepszy sposób niż poprzedni
            PreparedStatement statement = connector.getConnection().prepareStatement(
                    "SELECT * FROM book WHERE pages > ? AND title LIKE ? ORDER BY ?"
            );
            statement.setInt(1, 200);
            statement.setString(2, "%Java%");  //case sensitive!
            statement.setString(3, "author");

            // /ResultSet jest potrzebny do odczytania Selecta
            ResultSet resultSet = statement.executeQuery();

            //3.
            PreparedStatement statement1 = connector.getConnection().prepareStatement(
                    "INSERT INTO book VALUES (?,?,?,?,?)"
                   );
            statement1.setInt(1,0);
            statement1.setString(2,"moja Java");
            statement1.setString(3, "Adam Kowalski");
            statement1.setInt(4, 239);
            statement1.setString(5, "2014-01-22");

            statement1.execute();

            //petla przelatuje po kazdym rekordzie i go wyswietla az napotka koniec
            while (resultSet.next()) {
                System.out.println("Tytuł: " + resultSet.getString("title") + ", Autor: " + resultSet.getString("author"));
                System.out.println("Stron: " + resultSet.getInt("pages"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
