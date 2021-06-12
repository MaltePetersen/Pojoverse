package de.fh.kiel.advancedjava.pojomodel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
        title = "Pojo",
        version = "0.0",
        description = "A project Java course FH Kiel",
        license = @License(name = "Apache 2.0", url = "https://github.com/MaltePetersen"),
        contact = @Contact(url = "https://github.com/MaltePetersen", name = "Malte", email = "malte.petersen11@gmail.com")
))
@SpringBootApplication
public class PojoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PojoApplication.class, args);
    }


}
