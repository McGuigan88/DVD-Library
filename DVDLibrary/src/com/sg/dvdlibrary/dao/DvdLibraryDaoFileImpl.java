package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.io.*;
import java.util.*;

public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    private Map<String, DVD> dvds = new HashMap<>();

    public static final String DVDLIBRARYFILE = "DVD.txt";
    public static final String DELIMITER = ",";

    private DVD unmarshallDvd(String Library){

        String[] dvdSection = Library.split(DELIMITER);

        // Given the pattern above, the dcd title is in index 0 of the array.
        String title = dvdSection[0];

        // Which we can then use to create a new Student object to satisfy
        // the requirements of the Student constructor.
        DVD dvdFromFile = new DVD(title);


        // Index 1 - release date
        dvdFromFile.setReleaseDate(dvdSection[1]);

        // Index 2 - age rating
        dvdFromFile.setAgeRating(dvdSection[2]);

        // Index 3 - Director Name
        dvdFromFile.setDirectorName(dvdSection[3]);

        // Index 3 - Studio
        dvdFromFile.setStudio(dvdSection[4]);

        // Index 3 - User rating
        dvdFromFile.setUserRating(dvdSection[5]);

        return dvdFromFile;
    }

    private void loadLibrary() throws DvdLibraryDaoException{
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVDLIBRARYFILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load dvd data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDvd;
        // Go through file line by line, decoding each line into a
        // dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a dvd
            currentDvd = unmarshallDvd(currentLine);

            // We are going to use the student id as the map key for our dvd object.
            // Put currentDvd into the map using student id as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    private String marshallDvd(DVD aDvd){

        // title
        String dvdAsText = aDvd.getTitle() + DELIMITER;

        // release date
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // age rating
        dvdAsText += aDvd.getAgeRating() + DELIMITER;

        // director name
        dvdAsText += aDvd.getDirectorName() + DELIMITER;

        // studio name
        dvdAsText += aDvd.getStudio() + DELIMITER;

        // user rating
        dvdAsText += aDvd.getUserRating();;

        // return dvd
        return dvdAsText;
    }

    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVDLIBRARYFILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDvds();
        for (DVD currentDvd : dvdList) {
            dvdAsText = marshallDvd(currentDvd);
            out.println(dvdAsText);
            out.flush();
        }
        // Clean up
        out.close();
    }

    @Override
    public DVD addDvd(String title, DVD dvd) throws DvdLibraryDaoException {
        loadLibrary();
        DVD newDVD = dvds.put(title, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public List<DVD> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    @Override
    public DVD getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public DVD removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        DVD removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }


}

