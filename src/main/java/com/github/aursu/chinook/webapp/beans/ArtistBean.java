package com.github.aursu.chinook.webapp.beans;

import com.github.aursu.chinook.webapp.data.Artist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistBean {
    private List<Artist> artists;
}
