package service;

import model.Contact;

import java.util.List;

public interface ContactServiceInterface {
    List<Contact> create(List<Contact> contacts);
    List<Contact> update(List<Contact> updates);
    int delete(List<String> ids);
    List<Contact> search(String query);
}
