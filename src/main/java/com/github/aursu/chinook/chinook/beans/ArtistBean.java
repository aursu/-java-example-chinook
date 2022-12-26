package com.github.aursu.chinook.chinook.beans;

import com.github.aursu.chinook.chinook.data.Artist;
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
