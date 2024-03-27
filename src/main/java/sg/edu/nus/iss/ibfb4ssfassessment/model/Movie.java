package sg.edu.nus.iss.ibfb4ssfassessment.model;

import java.util.Date;

public class Movie {

    // unique
    int movieId;
    String title;
    String year;
    String rated;
    long releaseDate;
    String runTime;
    String genre;
    String director;
    double rating;
    Date formattedReleaseDate;
    int count;
    
    // constructor
    public Movie() {
    }

    // getters and setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getFormattedReleaseDate() {
        return formattedReleaseDate;
    }

    public void setFormattedReleaseDate(Date formattedReleaseDate) {
        this.formattedReleaseDate = formattedReleaseDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Movie [movieId=" + movieId + ", title=" + title + ", year=" + year + ", rated=" + rated
                + ", releaseDate=" + releaseDate + ", runTime=" + runTime + ", genre=" + genre + ", director="
                + director + ", rating=" + rating + ", formattedReleaseDate=" + formattedReleaseDate + ", count="
                + count + "]";
    }

    
    
    

}
