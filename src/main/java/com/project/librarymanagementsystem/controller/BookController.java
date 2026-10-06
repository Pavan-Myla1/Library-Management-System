package com.project.librarymanagementsystem.controller;

import com.project.librarymanagementsystem.dto.BookRequestDTO;
import com.project.librarymanagementsystem.dto.BookResponseDTO;
import com.project.librarymanagementsystem.service.BookService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponseDTO addBook(@RequestBody BookRequestDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(
            @PathVariable Long id,
            @RequestBody BookRequestDTO bookDTO) {

        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {

        boolean deleted = bookService.deleteBook(id);

        if (deleted) {
            return "Book deleted successfully";
        }

        return "Book not found";
    }
}