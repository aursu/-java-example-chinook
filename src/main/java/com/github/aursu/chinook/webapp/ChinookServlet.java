package com.github.aursu.chinook.webapp;

import com.github.aursu.chinook.webapp.beans.AlbumBean;
import com.github.aursu.chinook.webapp.beans.ArtistBean;
import com.github.aursu.chinook.webapp.dao.AlbumDao;
import com.github.aursu.chinook.webapp.dao.ArtistDao;
import com.github.aursu.chinook.webapp.data.Album;
import com.github.aursu.chinook.webapp.data.Artist;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "chinookServlet", value = {
        "/artists",
        "/add_artist",
        "/delete_artist",
        "/edit_artist",
        "/show_albums_of"
})
public class ChinookServlet extends HttpServlet {
    @Resource(name = "jdbc/Chinook")
    private DataSource ds;

    private ArtistDao artistDao;
    private AlbumDao albumDao;

    public void init() {
        artistDao = new ArtistDao(ds);
        albumDao = new AlbumDao(ds);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/artists")) {
            showArtists(request, response);
        } else if (requestURI.endsWith("/add_artist")) {
            addArtist(request, response);
        } else if (requestURI.endsWith("/delete_artist")) {
            deleteArtist(request, response);
        } else if (requestURI.endsWith("/edit_artist")) {
            editArtist(request, response);
        } else if (requestURI.endsWith("/show_albums_of")) {
            showAlbums(request, response);
        }
    }

    private void showAlbums(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int artistId = Integer.parseInt(request.getParameter("id"));

        Artist artist = artistDao.findById(artistId);
        List<Album> albums = albumDao.findByArtist(artistId);

        request.setAttribute("albumBean", new AlbumBean(albums, artist));
        request.getRequestDispatcher("/albums_by_artist.jsp").forward(request, response);
    }

    private void editArtist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equals("POST")) {
            updateArtist(request, response);
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            Artist artist = artistDao.findById(id);

            if (artist == null) {
                request.setAttribute("artistId", id);
                request.getRequestDispatcher("/artist_not_found.jsp").forward(request, response);
            } else {
                request.setAttribute("artist", artist);
                request.getRequestDispatcher("/edit_artist.jsp").forward(request, response);
            }
        }
    }

    private void updateArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("artistId"));
        String artistName = request.getParameter("artistname");

        artistDao.update(new Artist(id, artistName));
        response.sendRedirect("artists");
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        artistDao.deleteById(id);
        response.sendRedirect("artists");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void addArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        artistDao.add(request.getParameter("artistname"));
        response.sendRedirect("artists");
    }

    private void showArtists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Artist> artists = artistDao.findAll();
        request.setAttribute("artistBean", new ArtistBean(artists));
        request.getRequestDispatcher("/artists.jsp").forward(request, response);
    }
}