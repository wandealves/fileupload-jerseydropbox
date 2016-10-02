package javaes.wordpress.com.rest;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;

import javaes.wordpress.com.domain.Contact;
import javaes.wordpress.com.domain.ContactService;
import javaes.wordpress.com.domain.Response;
import javaes.wordpress.com.service.UploadService;

/**
 * @author https://javaes.wordpress.com/
 *
 */
@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ContactResource {
	@Autowired
	private ContactService contactService;
	@Autowired
	private UploadService uploadService;

	@GET
	public List<Contact> get() {
		List<Contact> contact = contactService.getContacts();
		return contact;
	}

	@GET
	@Path("{id}")
	public Contact get(@PathParam("id") long id) {
		Contact c = contactService.getContact(id);
		return c;
	}

	@GET
	@Path("/name/{name}")
	public List<Contact> getByNome(@PathParam("name") String name) {
		List<Contact> contacts = contactService.findByName(name);
		return contacts;
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		contactService.delete(id);
		return Response.Ok("Contato deletado com sucesso");
	}

	@POST
	public Response post(Contact c) {
		contactService.save(c);
		return Response.Ok("Contato salvo com sucesso");
	}

	@PUT
	public Response put(Contact c) {
		contactService.save(c);
		return Response.Ok("Contato atualizado com sucesso");
	}

	@POST
	@Path("/photo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postPhoto(final FormDataMultiPart multiPart) {
		if (multiPart != null && multiPart.getFields() != null) {
			Set<String> keys = multiPart.getFields().keySet();
			for (String key : keys) {
				// Obtem a InputStream para ler o aqruivo
				FormDataBodyPart field = multiPart.getField(key);
				InputStream in = field.getValueAs(InputStream.class);
				try {
					// Salvar o arquivo
					String fileName = field.getFormDataContentDisposition().getFileName();
					boolean response = uploadService.upload(fileName, in);

					if (response) {
						return Response.Ok("Foto recebida com sucesso!");
					} else {
						return Response.Error("Erro ao enviar o arquivo");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return Response.Error("Erro ao enviar o arquivo");
				}
			}
		}

		return Response.Error("Requisição Inválida");
	}
}
