package sg.edu.nus.iss.ibfb4ssfassessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;
import sg.edu.nus.iss.ibfb4ssfassessment.service.FileService;

// TODO: Put in the necessary code as described in Task 1 & Task 2
@SpringBootApplication
public class IbfB4SsfAssessmentApplication implements CommandLineRunner {

	@Autowired
	FileService fileService;

	@Autowired
	DatabaseService databaseService;

	public static void main(String[] args) {
		SpringApplication.run(IbfB4SsfAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Movie> movieList = fileService.readFile("movies.json");

		for (Movie movie : movieList) {
			System.out.println(movie.toString());
			databaseService.saveRecord(movie);
		}

		System.out.println("Number of movies: " + databaseService.getNumberOfMovies());

		System.out.println(databaseService.getMovieById(12333).toString());

		System.out.println(databaseService.getAllMovies().toString());
	}

}
