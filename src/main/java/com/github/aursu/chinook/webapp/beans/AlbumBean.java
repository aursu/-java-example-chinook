package com.github.aursu.chinook.webapp.beans;

import com.github.aursu.chinook.webapp.data.Album;
import com.github.aursu.chinook.webapp.data.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumBean {
    private List<Album> albums;
    private Artist artist;
}
