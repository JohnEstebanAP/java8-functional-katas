package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Create a datastructure from the given data:

    This time we have 4 seperate arrays each containing lists, videos, boxarts, and bookmarks respectively.
    Each object has a parent id, indicating its parent.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id, title, bookmark time, and smallest boxart url.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber",
                    "time": 32432,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/TheChamber130.jpg"
                },
                {
                    "id": 675465,
                    "title": "Fracture",
                    "time": 3534543,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/Fracture120.jpg"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard",
                    "time": 645243,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/DieHard150.jpg"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys",
                    "time": 984934,
                    "boxart": "http://cdn-0.nflximg.com/images/2891/BadBoys140.jpg"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos(), DataUtil.getBoxArts(), DataUtil.getBookmarkList()
    Output: the given datastructure
*/
public class Kata11 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();
        List<Map> boxArts = DataUtil.getBoxArts();
        List<Map> bookmarkList = DataUtil.getBookmarkList();

        List joinListVideos = lists.stream().map(list ->  ImmutableMap.of("name", list.get("name"), "videos", findVideosByIdList(list.get("id"), videos, bookmarkList,boxArts) ))
                .collect(Collectors.toList());

        System.out.println(joinListVideos);

        //return joinListVideos;
        return joinListVideos;
    }

    public static List<ImmutableList<Object>> findVideosByIdList(Object id, List<Map> videos,  List<Map> bookmarkList ,List<Map> boxArts) {

        List videosList  = videos.stream().filter(idList ->  idList.get("listId").equals(id))
                .map( videoList -> {
                    return ImmutableMap.of("id", videoList.get("id"),
                            "title", videoList.get("title"),
                            "time", findTimeByIdVideo(bookmarkList , videoList.get("id")),
                            "boxart", findUrlByIdVideo(boxArts, videoList.get("id"))
                    );
                }).collect(Collectors.toList());
        return videosList;
    }

    public static Object findTimeByIdVideo(List<Map> bookmarkList, Object id) {
        var dato  = bookmarkList.stream().filter(idList ->  idList.get("videoId").equals(id)).map( bookmarkTime -> bookmarkTime.get("time")).collect(Collectors.toList()).get(0);
        return dato;
    }

    public static Object findUrlByIdVideo(List<Map> boxArts, Object id) {
        var dato  = boxArts.stream().filter(idList ->  idList.get("videoId").equals(id)).map( boxArtsUrl -> boxArtsUrl.get("url")).collect(Collectors.toList()).get(0);
        return dato;
    }
}