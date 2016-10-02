package javaes.wordpress.com.service;

import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

@Component
public class DropboxService {

	private String ACCESS_TOKEN = "xxxxxxxxxxxxxxx";
	private DbxClientV2 client;

	public DropboxService() {
		try {
			// Create Dropbox client
			DbxRequestConfig config = new DbxRequestConfig("dropbox/javaes");
			client = new DbxClientV2(config, ACCESS_TOKEN);
		} catch (Exception ex) {
		}
	}

	public boolean upload(InputStream in, String file) {
		boolean upload = false;
		try {
			FileMetadata metadata = client.files().uploadBuilder("/"+file).uploadAndFinish(in);
			if (metadata.getName() == null) {
				upload = false;
			} else {
				upload = true;
			}
		} catch (Exception ex) {

		}
		return upload;
	}
}
