package javaes.wordpress.com.domain.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import javaes.wordpress.com.domain.Contact;

/**
 * @author https://javaes.wordpress.com/
 *
 */
@Component
public class ContactDAO extends HibernateDAO<Contact> {

	public ContactDAO() {
		super(Contact.class);
	}

	public Contact getContactById(Long id) {
		// Hibernate consulta pelo id
		return super.get(id);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> findByName(String name) {
		Query q = getSession().createQuery("from Contact where lower(name) like lower(?)");
		q.setString(0, "%" + name + "%");
		List<Contact> list = q.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getContacts() {
		Query q = getSession().createQuery("from Contact");
		return q.list();
	}

	public void delete(Long id) {
		Contact c = getContactById(id);
		super.delete(c);
	}

	public void save(Contact contact) {
		super.save(contact);
	}

	public void update(Contact contact) {
		super.update(contact);
	}

	public void saveOrUpdate(Contact contact) {
		super.saveOrUpdate(contact);
	}
}
