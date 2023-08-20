package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String, Movie> dbMovie = new HashMap<>();
    Map<String, Director> dbDirector = new HashMap<>();
    Map<String, List<String>> dbMovieDirectorPair = new HashMap<>();
    public void addMovie(Movie movie) {
        dbMovie.put(movie.getName(), movie);

    }

    public void addDirector(Director director) {
        dbDirector.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(dbMovie.containsKey(movieName) && dbDirector.containsKey(directorName)){
            if(dbMovieDirectorPair.containsKey(directorName)){
                dbMovieDirectorPair.get(directorName).add(movieName);
            }else{
                List<String> movieList = new ArrayList<>();
                movieList.add(movieName);
                dbMovieDirectorPair.put(directorName, movieList);
            }
        }
    }

    public Movie getMovieByName(String movieName) {
        return dbMovie.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return dbDirector.get((directorName));
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        if(dbMovieDirectorPair.containsKey(directorName)){
            return dbMovieDirectorPair.get(directorName);
        }
        return new ArrayList<>();
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(dbMovie.keySet());
    }


    public void deleteDirectorByName(String directorname) {
        if(dbMovieDirectorPair.containsKey(directorname)){
            for(String movieName: dbMovieDirectorPair.get(directorname)){
                dbMovie.remove(movieName);
            }
            dbMovieDirectorPair.remove(directorname);
        }

        dbDirector.remove(directorname);
    }


    public void deleteAllDirectors() {
        for(String directorName: dbMovieDirectorPair.keySet()){
            for(String movieName: dbMovieDirectorPair.get(directorName)){
                dbMovie.remove(movieName);
            }
            dbMovieDirectorPair.remove(directorName);
            dbDirector.remove((directorName));
        }
    }
}