package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
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

        // get the movie object
        Movie movie = databaseService.getMovieById(Integer.parseInt(movieId));

        // calculate the age
        httpSession.getAttribute("login");

        Long age = 14L;

        // success or error due to age
        if ("R".equals(movie.getRating())) {
            if (age >= 18) {
                return "BookSuccess";
            } else {
                return "BookError";
            }
        } else if ("PG-13".equals(movie.getRating())){
            if (age >= 13) {
                return "BookSuccess";
            } else {
                return "BookError";
            }
        } else {
            return "BookSuccess";
        }

    }

    // TODO: Task 9
    // ... ...

}
