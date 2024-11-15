package com.example.elibproj;

public class Book {
    private String title;
    private String author;
    private int pages;
    private String pdfFilePath;

    public Book(String title, String author, int pages, String pdfFilePath) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.pdfFilePath = pdfFilePath;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public String getPdfFileName() {
        // Extract the file name from the file path
        return pdfFilePath != null ? pdfFilePath.substring(pdfFilePath.lastIndexOf('/') + 1) : "No PDF";
    }
}