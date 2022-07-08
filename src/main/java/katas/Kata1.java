package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Movie;
import util.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: use map() to project an array of videos into an array of {id, title}-pairs
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys")
*/
public class Kata1 {
    public static List<Map> execute() {
        List<Movie> movies = DataUtil.getMovies();


        //movies.stream().map( (movie) ->  movie.getId()).forEach( a -> System.out.println(a.toString()) );

        List<Map> listMovies =  movies.stream().map( movie -> ImmutableMap.of( "Id", movie.getId(), "Title", movie.getTitle(), "Rating", movie.getRating(), "Url", movie.getUri())).collect(Collectors.toList());

        System.out.println( listMovies.toString());

        return listMovies;
    }
}
