package javaes.wordpress.com.service;

import java.io.File;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UploadService {
	@Autowired
	private DropboxService dropboxService;

	public boolean upload(String fileName, InputStream in) {
		boolean upload = false;
		try {
			if (fileName == null || in == null) {
				throw new IllegalAccessException("Parâmetros inválidos");
			}
			// Pasta temporária da JVM
			File tmpDir = new File(System.getProperty("java.io.tmpdir"), "photos");
			if (!tmpDir.exists()) {
				tmpDir.mkdir();
			}
			upload = dropboxService.upload(in, fileName);
		} catch (Exception ex) {
		}
		return upload;
	}
}
