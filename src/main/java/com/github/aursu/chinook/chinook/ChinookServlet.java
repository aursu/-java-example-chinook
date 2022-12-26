package com.github.aursu.chinook.chinook;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "chinookServlet", value = "/artists")
public class ChinookServlet extends HttpServlet {
    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        showArtists(request, response);
    }

    private void showArtists(HttpServletRequest request, HttpServletResponse response) {

    }
}