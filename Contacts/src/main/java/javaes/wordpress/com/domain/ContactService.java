package javaes.wordpress.com.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javaes.wordpress.com.domain.dao.ContactDAO;

/**
 * @author https://javaes.wordpress.com/
 *
 */
@Component
public class ContactService{
	@Autowired
	private ContactDAO dao;

	// Lista todos os contatos do banco de dados
	public List<Contact> getContacts() {
		List<Contact> contacts = this.dao.getContacts();
		return contacts;
	}

	// Busca um contato pelo id
	public Contact getContact(Long id) {
		return this.dao.getContactById(id);
	}

	// Deleta o contato pelo id
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long id) {
		this.dao.delete(id);
	}

	// Salva ou atualiza o contato
	@Transactional(rollbackFor = Exception.class)
	public void save(Contact contact) {
		this.dao.saveOrUpdate(contact);
	}

	// Busca o contato pelo nome
	public List<Contact> findByName(String name) {
		return this.dao.findByName(name);
	}
}
