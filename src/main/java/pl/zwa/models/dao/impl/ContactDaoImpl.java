package pl.zwa.models.dao.impl;

import pl.zwa.MysqlConnector;
import pl.zwa.models.ContactModel;
import pl.zwa.models.dao.ContactDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    private MysqlConnector mysqlConnector = MysqlConnector.getInstance();

    public void addContact(ContactModel model) {
        try {
            PreparedStatement statement = mysqlConnector.getConnection().prepareStatement
                    ("INSERT INTO contact VALUES (?,?,?,?)");
            statement.setInt(1, 0);
            statement.setString(2, model.getNumber());
            statement.setString(3, model.getName());
            statement.setString(4, model.getLastname());
            statement.execute();
            statement.close(); //ważne aby zamykać execute przez close
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeContact(String number) {
        try {
            PreparedStatement statement = mysqlConnector.getConnection().prepareStatement(
                    "DELETE FROM contact WHERE number = ?");
            statement.setString(1, number);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactModel getContactByNumber(String number) {
        ContactModel model = null;
        try {
            PreparedStatement statement = mysqlConnector.getConnection().prepareStatement(
                    "SELECT * FROM contact WHERE number = ?");
                    statement.setString(1, number);

                ResultSet set = statement.executeQuery();
            while (set.next()){
                model = new ContactModel(set.getString("number"),
                        set.getString("name"),
                        set.getString("lastname"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public List<ContactModel> getAllContacts() {
        List<ContactModel> contactModels = new ArrayList<ContactModel>();
        try {
            PreparedStatement statement = mysqlConnector.getConnection().prepareStatement(
                    "SELECT * FROM contact");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                contactModels.add(new ContactModel(set.getString("number"), set.getString("name"), set.getString("lastname")));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactModels;
    }

}

