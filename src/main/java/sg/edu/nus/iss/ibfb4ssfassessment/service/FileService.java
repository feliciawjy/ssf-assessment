package sg.edu.nus.iss.ibfb4ssfassessment.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Movie;

@Service
public class FileService {

    // TODO: Task 1
    public List<Movie> readFile(String fileName) throws FileNotFoundException{

        FileInputStream fis = new FileInputStream(fileName);
        JsonReader reader = Json.createReader(fis);
        JsonArray jsonArray = reader.readArray();

        List<Movie> movieList = new ArrayList<>();

        jsonArray.forEach(mov -> {

            JsonObject jObject = mov.asJsonObject();
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

            // set formatted date
            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            // set date
            movie.setFormattedReleaseDate(new Date(movie.getReleaseDate()));

            movieList.add(movie);

        });

        return movieList;
    }

}
