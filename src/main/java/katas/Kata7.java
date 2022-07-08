package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.BoxArt;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the id, title, and smallest box art url for every video
    DataSource: DataUtil.getMovieLists()
    Output: List of ImmutableMap.of("id", "5", "title", "Bad Boys", "boxart": "url)
*/
public class Kata7 {
  public static List<Map> execute() {
    List<MovieList> movieLists = DataUtil.getMovieLists();

    List<Map> listMovies =
        movieLists.stream()
            .map(listMoviesMap -> listMoviesMap)
            .flatMap(movies -> movies.getVideos().stream())
            .map(
                movie ->
                    ImmutableMap.of(
                        "id", movie.getId(),
                        "title", movie.getTitle(),
                        "url", findBoxArtUrlByWidth(movie)))
            .peek(movie -> System.out.println(movie))
            .collect(Collectors.toList());

    return listMovies;
  }

  public static String findBoxArtUrlByWidth(Movie movie) {
    var boxArt =
        movie.getBoxarts().stream()
            .reduce((minWidth,width)  -> (minWidth.getWidth() < width.getWidth()) ? minWidth: width);
    return boxArt.get().getUrl();
  }
}
