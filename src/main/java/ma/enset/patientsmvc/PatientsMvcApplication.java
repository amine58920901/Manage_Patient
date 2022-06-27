package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientsMvcApplication.class, args);
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
}
