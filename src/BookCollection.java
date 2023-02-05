import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class BookCollection {
    private ArrayList<Book> books = new ArrayList<>();

    BookCollection(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // skip header row
            while ((line = br.readLine()) != null) {
                String[] bookDetails = line.split(",");
                books.add(new Book(bookDetails[0], bookDetails[1], Long.parseLong(bookDetails[2]),
                        Integer.parseInt(bookDetails[3]), Integer.parseInt(bookDetails[4]), Integer.parseInt(bookDetails[5])));
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public HashSet<String> getAuthors() {
        HashSet<String> authors = new HashSet<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        return authors;
    }

    public ArrayList<Book> getLongBooks() {
        ArrayList<Book> longBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getPages() > 750) {
                longBooks.add(book);
            }
        }
        return longBooks;
    }

    public Book getBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Book[] mostPopular() {
        Collections.sort(books, (b1, b2) -> b2.getCopiesOnLoan() - b1.getCopiesOnLoan());
        Book[] mostPopular = new Book[10];
        for (int i = 0; i < 10; i++) {
            mostPopular[i] = books.get(i);
        }
        return mostPopular;
    }
}
