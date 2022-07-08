package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve id, title, and a 150x200 box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": BoxArt)
*/
public class Kata4 {
    public static List<Map> execute() {
        List<MovieList> movieLists = DataUtil.getMovieLists();

        List<Map> listMovies =
                movieLists.stream()
                        .map(listMoviesMap -> listMoviesMap)
                        .flatMap(movies -> movies.getVideos().stream())
                        .map(movie ->  ImmutableMap.of(
                                "id", movie.getId(),
                                "title", movie.getTitle(),
                                "boxArt", findBoxArtByWidthHeight(movie)
                                )
                        ).collect(Collectors.toList());

        System.out.println(listMovies);

        return listMovies;
    }

    public static BoxArt findBoxArtByWidthHeight(Movie movie){
        var boxArt = movie.getBoxarts().stream().filter(condicion -> condicion.getWidth()==150 && condicion.getHeight()==200).collect(Collectors.toList());
        System.out.println(boxArt.get(0).getUrl());
        return boxArt.get(0);
    }

}
