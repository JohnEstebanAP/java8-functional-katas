package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import model.Bookmark;
import model.Movie;
import model.MovieList;
import util.DataUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Retrieve each video's id, title, middle interesting moment time, and smallest box art url
    DataSource: DataUtil.getMovies()
    Output: List of ImmutableMap.of("id", 5, "title", "some title", "time", new Date(), "url", "someUrl")
*/
public class Kata9 {
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
                        "time", new Date(),
                        "url", findBoxArtUrlmin(movie)))
            .peek(movie -> System.out.println(movie))
            .collect(Collectors.toList());

    return listMovies;
  }

  public static String findBoxArtUrlmin(Movie movie) {
    var boxArt =
        movie.getBoxarts().stream()
                .reduce((minUrl,url)  -> (minUrl.getUrl().length() < url.getUrl().length()) ? minUrl: url);

    return boxArt.get().getUrl();
  }
}
