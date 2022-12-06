package com.edu.bookms.api;

import com.edu.bookms.model.Book;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public class TestBookResource {
    public static String SERVER_URI = "http://localhost:8097/bookms";

    public static void main(String... s) {
//        System.out.println(" Find Book By Isbn ");
//        testFindBookByIsbn("xjijskmdjuyj");
//        System.out.println(" *********************** ");

        System.out.println(" Find Book By Id ");
        testFindBookById(1);
        System.out.println(" *********************** ");

//        System.out.println(" Retrieve All Books ");
//        testGetAllBooks();
//
//        System.out.println(" Add Book ");
//        //testAddBook();
//
//        System.out.println(" Edit Book ");
//      //  testEditBook();
//
//        System.out.println(" Delete Book ");
//        //testDeleteBook();
    }

//    private static void testAddBook(String isbn) {
//        RestTemplate restTemplate = new RestTemplate();
//        Book book = new Book();
//        restTemplate.getForObject(SERVER_URI + "/" + isbn, Book.class);
//        printBookData(book);
//    }

//    private static void testEditBook() {
//    }
//
//    private static void testDeleteBook() {
//    }


    private static void testFindBookByIsbn(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(SERVER_URI + "/" + isbn, Book.class);
        printBookData(book);
    }

    private static void testFindBookById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(SERVER_URI + "/api/book/" + id, Book.class);
        printBookData(book);
    }

    private static void printBookData(Book book) {
        System.out.println("ID: " + book.getId() + "\tISBN: " + book.getIsbn() + "\tAUTHOR: " + book.getAuthor()
                + "\n\tTITLE: " + book.getTitle() + "\tTOTAL COPIES: " + book.getTotalCopies() +
                "\n\t" + book.getIssuedCopies() + "\tPublished Date:  " + book.getPublishedDate());
    }

    private static void testGetAllBooks() {
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap> listMap = restTemplate.getForObject(SERVER_URI + "/api/book", List.class);
        for (LinkedHashMap map : listMap) {
            System.out.println("ID: " + map.get("id") + "\tISBN: " + map.get("isbn") + "\tAUTHOR: "
                    + map.get("author") + "\n\tTITLE: " + map.get("title") + "\tTOTAL COPIES: "
                    + map.get("totalCopies") + "\n\t" + map.get("issuedCopies")
                    + "\tPublished Date:  " + map.get("publishedDate"));
        }
    }
}
