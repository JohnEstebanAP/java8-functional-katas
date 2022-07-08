package katas;

import com.google.common.collect.ImmutableMap;
import model.BoxArt;
import model.Movie;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
    Goal: Retrieve the url of the largest boxart using map() and reduce()
    DataSource: DataUtil.getMovieLists()
    Output: String
*/
public class Kata6 {
    public static String execute() {
        List<Movie> movies = DataUtil.getMovies();

        var listMovies =  movies.stream().map(movie -> movie.getBoxarts())
        .flatMap(boxArts -> boxArts.stream())
                .map(boxArt -> boxArt.getUrl()).peek( urls -> System.out.println(urls))
                .reduce((maxUrl, url) -> (maxUrl.length() > url.length()) ? maxUrl:url);

        return listMovies.get();
    }
}