package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    private final Map<String, Movie> movies = new HashMap<>();
    private final Map<String, Director> directors = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMovieNamesByDirectorName(String directorName) {
        return movies.values().stream()
                .filter(movie -> movie.getDirectorName().equals(directorName))
                .map(Movie::getName)
                .collect(Collectors.toList());
    }

    public List<String> findAllMovieNames() {
        return movies.values().stream()
                .map(Movie::getName)
                .collect(Collectors.toList());
    }


    public void deleteDirector(String directorName) {
        directors.remove(directorName);
        // Remove movies directed by this director
        movies.entrySet().removeIf(entry -> entry.getValue().getDirectorName().equals(directorName));
    }

    public void deleteAllDirectors() {
        directors.clear();
        // Remove movies directed by any director
        movies.entrySet().removeIf(entry -> entry.getValue().getDirectorName() != null);
    }
}
