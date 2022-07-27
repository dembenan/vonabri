package ci.palmafrique.vonabri;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cloudinary.Cloudinary;

import ci.palmafrique.vonabri.utils.Utilities;

@SpringBootApplication
@Configuration
@EnableScheduling
public class VonabriApplication {

	public static void main(String[] args) throws Exception {
		

		SpringApplication.run(VonabriApplication.class, args);
		
		System.out.println("********password  azerty1234=== >>"+Utilities.encrypt("azerty1234"));
		
       // FTPClient client = new FTPClient();
        //String filename = "/Users/dembelesiaka/card.jpeg";

        // Read the file from resources folder.
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        try (InputStream is = classLoader.getResourceAsStream(filename)) {
//            client.connect("ftp://74.208.183.242");
//            boolean login = client.login("api-vonabri.cip-palm_mmtxbldsc2q", "Rb1k3b70");
//            if (login) {
//                System.out.println("Login success...");
//                // Store file to server
//                client.storeFile(filename, is);
//                client.logout();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                client.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

	}
	

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
		Map<String, String> config = new HashMap<String, String>();
    	config.put("cloud_name", "palmafrique");
        config.put("api_key", "168178223131865");
        config.put("api_secret", "lnvJ_EqJmLdfJJL35n0j3YdXKL8");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }

}
