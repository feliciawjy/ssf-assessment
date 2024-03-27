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
    public String bookMovie() {
        return null;

    }

    // TODO: Task 9
    // ... ...

}
