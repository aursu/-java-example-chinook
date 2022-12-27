package com.github.aursu.chinook.webapp.dao;

import com.github.aursu.chinook.webapp.data.Album;
import com.github.aursu.chinook.webapp.data.Artist;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AlbumDao {
    private final DataSource ds;

    public List<Album> findAll() {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from Album");
             ResultSet rs = ps.executeQuery()
        ) {
            List<Album> result = new ArrayList<>();
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId"),
                        artistId = rs.getInt("ArtistId");
                String title = rs.getString("Title");

                result.add(new Album(albumId, title, artistId));
            }
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Album> findByArtist(int artistId) {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from Album where ArtistId = ?")
        ) {
            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            List<Album> result = new ArrayList<>();
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");

                result.add(new Album(albumId, title, artistId));
            }
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
