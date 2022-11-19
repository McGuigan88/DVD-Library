package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public interface DvdLibraryDao {
    DVD addDvd(String title, DVD dvd) throws DvdLibraryDaoException;

    List<DVD> getAllDvds() throws DvdLibraryDaoException;

    DVD getDvd(String title) throws DvdLibraryDaoException;

    DVD removeDvd(String title) throws DvdLibraryDaoException;

}
