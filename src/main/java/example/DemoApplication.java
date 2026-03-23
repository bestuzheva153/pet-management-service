package example;

import example.dao.CatDao;
import example.dao.OwnerDao;
import example.interfaces.GenericDao;
import example.model.Cat;
import example.model.Owner;
import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class DemoApplication {
		public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}