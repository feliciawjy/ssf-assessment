package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.repo.MovieRepo;

@Service
public class DatabaseService {

    @Autowired
    MovieRepo repo;

    // TODO: Task 2 (Save to Redis Map)
    public void saveRecord(Movie movie) {

    }

    // TODO: Task 3 (Map or List - comment where necesary)
    public long getNumberOfEvents() {

        return (Long) null;
    }

    public Movie getMovie(Integer index) {
        return repo.getMovie(index);
    }

    // TODO: Task 4 (Map)
    public Movie getMovieById(Integer movieId) {

        return null;
    }

    // TODO: Task 5
    public List<Movie> getAllMovies() {

        return null;
    }
}
