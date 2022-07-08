package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the largest rating using reduce()
    DataSource: DataUtil.getMovies()
    Output: Double
*/
public class Kata5 {
    public static Double execute() {
        List<Movie> movies = DataUtil.getMovies();


        Optional<Double> listMovies =  movies.stream().map(movie -> movie.getRating()).reduce((maxRating, num) -> (maxRating > num) ? maxRating : num);

        System.out.println( listMovies.get());


        return listMovies.get();
    }
}
