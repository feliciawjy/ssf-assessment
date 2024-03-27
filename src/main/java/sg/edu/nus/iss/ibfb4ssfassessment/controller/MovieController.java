package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.service.DatabaseService;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    DatabaseService databaseService;

    // TODO: Task 8
    @GetMapping("/list")
    public String displayMovies(Model model, HttpSession httpSession) {
        if (null != httpSession.getAttribute("login")) {
            List<Movie> movies = databaseService.getAllMovies();
            model.addAttribute("movies", movies);
            return "view2";
        } else {
            return "redirect:/login";
        }

    }

    // TODO: Task 9
    @GetMapping(path = "/book/{movieId}")
    public String bookMovie(@PathVariable("movieId") String movieId, Model model, HttpSession httpSession) {

        if (null != httpSession.getAttribute("login")) {

            Movie movie = databaseService.getMovieById(Integer.parseInt(movieId));

            Login login = (Login) httpSession.getAttribute("login");
            Date dob = login.getBirthDate();
            Date currentDate = new Date();

            // System.out.println("DOB: " + dob);
            // System.out.println("Current date: " + currentDate);

            long timeDifference = currentDate.getTime() - dob.getTime(); // millis
            long age = (timeDifference / (1000L * 60 * 60 * 24 * 365));

            // System.out.println("Age: " + age);
            // System.out.println("rating: " + movie.getRated());

            if ("R".equals(movie.getRated()) && age < 18) {
                return "BookError";
            } else if ("PG-13".equals(movie.getRated()) && age < 13) {
                return "BookError";
            } else {
                
                model.addAttribute("movie", movie);
                return "BookSuccess";
            }
        } else {
            return "redirect:/login";
        }

    }

    // TODO: Task 9
    // ... ...

}
