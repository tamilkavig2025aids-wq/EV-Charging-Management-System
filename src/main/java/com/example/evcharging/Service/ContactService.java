package com.example.evcharging.Service;

import com.example.evcharging.Entity.Contact;
import com.example.evcharging.Repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getById(Long id) {
        return contactRepository.findById(id);
    }

    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}