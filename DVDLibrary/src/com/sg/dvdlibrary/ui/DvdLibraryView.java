package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public class DvdLibraryView {
    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List full DVD collection");
        io.print("2. Add new DVD to collection");
        io.print("3. Search for DVD");
        io.print("4. Remove DVD from collection");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public DVD getNewDvdInfo() {
        String title = io.readString("Please enter the title of your DVD");
        String releaseDate = io.readString("Please enter the release date");
        String ageRating = io.readString("Please enter the age rating of the title");
        String directorName = io.readString("Please enter the director's name");
        String studio = io.readString("Please enter the studio who made this title");
        String userRating = io.readString("Please enter the user rating");
        DVD currentDvd = new DVD(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setAgeRating(ageRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);

        return currentDvd;
    }

    public void displayCreateDVDBanner(){
        io.print("=== Add DVD To Collection");
    }
    public void displayCreateSuccessBanner(){
        io.readString("DVD successfully added.  Please hit enter to continue");
    }

    public void displayDvdList(List<DVD> dvdList){
        for (DVD currentDvd : dvdList){
            String DvdInfo = String.format("%s : %s | %s | %s | %s | %s",
                    currentDvd.getTitle(),
                    currentDvd.getReleaseDate(),
                    currentDvd.getAgeRating(),
                    currentDvd.getDirectorName(),
                    currentDvd.getStudio(),
                    currentDvd.getUserRating());
            io.print(DvdInfo);
        }
        io.readString("Please hit enter to continue");
    }
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs in collection");
    }

    public void displayDisplayStudentBanner() {
        io.print("=== Display DVD");
    }

    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title");
    }

    public void displayDvd(DVD dvd){
        if (dvd != null) {
            io.print(dvd.getTitle() + " by " + dvd.getDirectorName());
            io.print("Release Date: " + dvd.getReleaseDate());
            io.print("Age Rating: " +dvd.getAgeRating());
            io.print("Studio: " + dvd.getStudio());
            io.print("User Rating: " + dvd.getUserRating());
            io.print("");
        } else {
            io.print("You don't own that DVD");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveResult(DVD dvdRecord) {
        if (dvdRecord != null) {
            io.print("Dvd successfully removed.");
        } else {
            io.print("You don't own that DVD");
        }
        io.readString("Please hit enter to continue");
    }

    public void displayExitBanner() {
        io.print("Leaving Collection!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
