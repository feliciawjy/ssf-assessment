package sg.edu.nus.iss.ibfb4ssfassessment.repo;

import java.io.StringReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        return hashOps.size(Util.KEY_MOVIE);
    }

    // read all
    public List<Movie> getMovieList() {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        List<Movie> movies = new LinkedList<>();
        Map<String, String> movieMap = hashOps.entries(Util.KEY_MOVIE);

        for (String s : movieMap.values()) {
            Movie movie = parseMovieFromJsonString(s);
            movies.add(movie);
        }

        return movies;
    }

    // read one
    // public Movie getMovie(Integer index) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'getMovie'");
    // }

    // read one by id
    public Movie getMovieById(int id) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        String movieString = hashOps.get(Util.KEY_MOVIE, Integer.toString(id));
        return parseMovieFromJsonString(movieString);
    }

    // Update
    // increment count after booking
    public void updateMovie(Movie movie) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.put(Util.KEY_MOVIE, String.valueOf(movie.getMovieId()), toJson(movie).toString());
    }

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

    private Movie parseMovieFromJsonString(String movieString) {

        JsonObject jObject = Json.createReader(new StringReader(movieString)).readObject();
        Movie movie = new Movie();
        movie.setMovieId(jObject.getInt("Id"));
        movie.setTitle(jObject.getString("Title"));
        movie.setYear(jObject.getString("Year"));
        movie.setRated(jObject.getString("Rated"));
        movie.setReleaseDate(jObject.getJsonNumber("Released").longValue());
        movie.setRunTime(jObject.getString("Runtime"));
        movie.setGenre(jObject.getString("Genre"));
        movie.setDirector(jObject.getString("Director"));
        movie.setRating(jObject.getJsonNumber("Rating").doubleValue());
        movie.setCount(jObject.getInt("Count"));

        movie.setFormattedReleaseDate(new Date(movie.getReleaseDate()));

        return movie;

    }

}
