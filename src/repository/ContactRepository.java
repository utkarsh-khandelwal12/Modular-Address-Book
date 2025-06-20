package repository;

import model.Contact;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ContactRepository {
    private final Map<String, Contact> store = new ConcurrentHashMap<>();

    public void save(Contact contact) {
        store.put(contact.id, contact);
    }

    public Contact get(String id) {
        return store.get(id);
    }

    public void delete(String id) {
        store.remove(id);
    }

    public Collection<Contact> getAll() {
        return store.values();
    }

    public boolean exists(String id) {
        return store.containsKey(id);
    }

    public List<Contact> search(String query) {
        return store.values().stream()
                .filter(c -> c.name != null && c.name.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
