package javaes.wordpress.com.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author https://javaes.wordpress.com/
 *
 */
@Entity
@XmlRootElement
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id",unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idContact;
	@Column(nullable=false, length=150)
	private String name;
	@Column(nullable=true,length=20)
	private String telephone;
	@Column(nullable=true, length=120)
	private String email;

	public Contact() {

	}

	public Contact(long id, String name, String telephone, String email) {
		super();
		this.idContact = id;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
	}

	public Long getIdContact() {
		return idContact;
	}

	public void setIdContact(Long id) {
		this.idContact = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [idContact=" + idContact + ", name=" + name + ", telephone=" + telephone + ", email=" + email
				+ "]";
	}
}
