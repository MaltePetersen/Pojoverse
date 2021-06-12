package de.fh.kiel.advancedjava.pojomodel;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "Pojoverse",
        version = "1.0",
        description = """
                This application is able to store, update, delete, visualize simple POJO's (Plain old Java objects).
                The pojos are modeled, stored and visualized in a neo4j graph database.
                You can login into the db here: http://localhost:7474/browser/
                A user can interact with the system through this REST-APi.                                
                 """,
        license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html"),
        contact = @Contact(url = "https://github.com/MaltePetersen", name = "Malte Petersen", email = "malte.petersen11@gmail.com")
), externalDocs = @ExternalDocumentation(description = "Gitlab", url = "https://gitlab.iue.fh-kiel.de/advanced-java-2021/pojo-malte"))
@SpringBootApplication
public class PojoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PojoApplication.class, args);
    }


}
