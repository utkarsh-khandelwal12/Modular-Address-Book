package service;

import model.Contact;
import repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ContactService implements ContactServiceInterface {
    private final ContactRepository repo = new ContactRepository();

    public List<Contact> create(List<Contact> contacts) {
        for (Contact c : contacts) {
            c.id = UUID.randomUUID().toString();
            repo.save(c);
        }
        return contacts;
    }

    public List<Contact> update(List<Contact> updates) {
        List<Contact> result = new ArrayList<>();
        for (Contact u : updates) {
            if(Objects.nonNull(u)
                    && Objects.nonNull(u.id)) {
                Contact existing = repo.get(u.id);
                if (existing != null) {
                    if (u.name != null) existing.name = u.name;
                    if (u.phone != null) existing.phone = u.phone;
                    if (u.email != null) existing.email = u.email;
                    repo.save(existing);
                    result.add(existing);
                } else {
                    System.out.println("Contact does not exist for id : " + u.id);
                }
            } else {
                System.out.println("Id is not provided. Invalid request ");
            }
        }
        return result;
    }

    public int delete(List<String> ids) {
        int count = 0;
        for (String id : ids) {
            if (repo.exists(id)) {
                repo.delete(id);
                count++;
            } else {
                System.out.println("Contact does not exist for id : " + id);
            }
        }
        return count;
    }

    public List<Contact> search(String query) {
        if(Objects.nonNull(query)
                && !query.isEmpty()) {
            List<Contact> contacts = repo.search(query);
            if(Objects.nonNull(contacts) && !contacts.isEmpty()) {
                return contacts;
            } else {
                System.out.println("Contact does not exist");
                return new ArrayList<>();
            }
        } else {
            System.out.println("Invalid request");
            return new ArrayList<>();
        }
    }
}
