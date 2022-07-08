package ci.palmafrique.vonabri;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import ci.palmafrique.vonabri.utils.Utilities;

@SpringBootApplication
@EnableScheduling
public class VonabriApplication {

	public static void main(String[] args) throws Exception {
		

		SpringApplication.run(VonabriApplication.class, args);
		
		System.out.println("********password  azerty1234=== >>"+Utilities.encrypt("azerty1234"));

	}
	


}
