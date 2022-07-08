package katas;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import util.DataUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
    Goal: Create a datastructure from the given data:

    We have 2 arrays each containing lists, and videos respectively.
    Each video has a listId field indicating its parent list.
    We want to build an array of list objects, each with a name and a videos array.
    The videos array will contain the video's id and title.
    In other words we want to build the following structure:

    [
        {
            "name": "New Releases",
            "videos": [
                {
                    "id": 65432445,
                    "title": "The Chamber"
                },
                {
                    "id": 675465,
                    "title": "Fracture"
                }
            ]
        },
        {
            "name": "Thrillers",
            "videos": [
                {
                    "id": 70111470,
                    "title": "Die Hard"
                },
                {
                    "id": 654356453,
                    "title": "Bad Boys"
                }
            ]
        }
    ]

    DataSource: DataUtil.getLists(), DataUtil.getVideos()
    Output: the given datastructure
*/
public class Kata10 {
    public static List<Map> execute() {
        List<Map> lists = DataUtil.getLists();
        List<Map> videos = DataUtil.getVideos();


       List joinListVideos = lists.stream().map(list ->  ImmutableMap.of("name", list.get("name"), "videos", findVidosByIdList(list.get("id"), videos) ))
               .collect(Collectors.toList());

        System.out.println(joinListVideos);

       return joinListVideos;
    }

    public static List<ImmutableList<Object>> findVidosByIdList(Object id, List<Map> videos) {

        List videosList  = videos.stream().filter(idList ->  idList.get("listId").equals(id)).map( videoList -> ImmutableMap.of("id", videoList.get("id"), "title", videoList.get("title"))).collect(Collectors.toList());
        return videosList;
    }
}
