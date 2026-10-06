package com.project.librarymanagementsystem.service;

import com.project.librarymanagementsystem.dto.BookRequestDTO;
import com.project.librarymanagementsystem.dto.BookResponseDTO;

import java.util.List;

public interface BookService {

    BookResponseDTO addBook(BookRequestDTO bookDTO);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO getBookById(Long id);

    BookResponseDTO updateBook(Long id, BookRequestDTO bookDTO);

    boolean deleteBook(Long id);
}