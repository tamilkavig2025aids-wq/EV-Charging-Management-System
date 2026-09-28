package com.example.evcharging.Controller;

import com.example.evcharging.Dto.ContactRequest;
import com.example.evcharging.Entity.Contact;
import com.example.evcharging.Service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> create(
            @RequestBody ContactRequest request) {

        Contact contact = new Contact();

        contact.setName(request.getName());
        contact.setType(request.getType());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setAddress(request.getAddress());

        return ResponseEntity.ok(
                contactService.save(contact));
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAll() {
        return ResponseEntity.ok(contactService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(
            @PathVariable Long id) {

        return contactService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        contactService.delete(id);

        return ResponseEntity.ok(
                "Contact deleted successfully");
    }
}