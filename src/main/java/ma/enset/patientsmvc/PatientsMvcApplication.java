package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientsMvcApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

		//@Bean
		CommandLineRunner commandLineRunnuer(PatientRepository patientRepository){

		return args -> {
			patientRepository.save(
					new Patient(null,"Amine",new Date(),false,120));

			patientRepository.save(
					new Patient(null,"Hayet",new Date(),true,321));

			patientRepository.save(
					new Patient(null,"Momo",new Date(),true,650));

			patientRepository.save(
					new Patient(null,"Safiya",new Date(),false,125));

			patientRepository.findAll().forEach(p ->{
				System.out.println(p.getNom());
			} );

		};
		}
		//@Bean
	        CommandLineRunner saveUsers(SecurityService securityService){
			return args -> {
				securityService.saveNewUser("Amine","1234","1234");
				securityService.saveNewUser("Hayet","1234","1234");
				securityService.saveNewUser("Mohamed","1234","1234");

				securityService.saveNewRole("USER","");
				securityService.saveNewRole("ADMIN","");

				securityService.addRoleToUser("Amine","USER");
				securityService.addRoleToUser("Amine","ADMIN");
				securityService.addRoleToUser("Hayet","USER");
				securityService.addRoleToUser("Mohamed","USER");
			};
		}
}
