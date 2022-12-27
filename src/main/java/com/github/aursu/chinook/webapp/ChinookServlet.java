package com.github.aursu.chinook.webapp;

import com.github.aursu.chinook.webapp.beans.ArtistBean;
import com.github.aursu.chinook.webapp.dao.ArtistDao;
import com.github.aursu.chinook.webapp.data.Artist;
import java.io.*;
import java.util.List;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.DataSource;

@WebServlet(name = "chinookServlet", value = {"/artists", "/add_artist"})
public class ChinookServlet extends HttpServlet {
    @Resource(name = "jdbc/Chinook")
    private DataSource ds;

    private ArtistDao artistDao;

    public void init() {
        artistDao = new ArtistDao(ds);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/artists")) {
            showArtists(request, response);
        } else if (requestURI.endsWith("/add_artist")) {
            addArtist(request, response);
        }
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