package pl.zwa.models.dao;

import pl.zwa.models.ContactModel;

import java.util.List;

public interface ContactDao {
    void addContact(ContactModel model);  //dodaj kontakt
    void removeContact(String number);   //usuń
    List<ContactModel> getAllContacts();  //wyświetl wszystkie kontakty w formie listy
    ContactModel getContactByNumber(String number);

}
