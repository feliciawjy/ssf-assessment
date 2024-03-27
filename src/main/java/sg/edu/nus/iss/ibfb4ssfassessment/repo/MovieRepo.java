package sg.edu.nus.iss.ibfb4ssfassessment.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;
import sg.edu.nus.iss.ibfb4ssfassessment.util.Util;

@Repository
public class MovieRepo {

    @Autowired
    @Qualifier(Util.REDIS_ONE)
    RedisTemplate<String, String> redisTemplate;

    // Create
    public void addMovie(Movie movie) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();

        hashOps.putIfAbsent(Util.KEY_MOVIE, String.valueOf(movie.getMovieId()), toJson(movie).toString());
    }

    // Read
    // get length
    public long getNumberOfMovies() {
        HashOperations <String, String, String> hashOps = redisTemplate.opsForHash();
        return hashOps.size(Util.KEY_MOVIE);
    }
    
    // read all

    // read one
    public Movie getMovie(Integer index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMovie'");
    }
    // Update
    // Delete

    private JsonObject toJson(Movie movie) {
        return Json.createObjectBuilder()
                .add("Id", movie.getMovieId())
                .add("Title", movie.getTitle())
                .add("Year", movie.getYear())
                .add("Rated", movie.getRated())
                .add("Released", movie.getReleaseDate())
                .add("Runtime", movie.getRunTime())
                .add("Genre", movie.getGenre())
                .add("Director", movie.getDirector())
                .add("Rating", movie.getRating())
                .add("Count", movie.getCount())
                .build();

    }



}
