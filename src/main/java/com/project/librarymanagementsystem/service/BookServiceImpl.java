package com.project.librarymanagementsystem.service;

import com.project.librarymanagementsystem.dto.BookRequestDTO;
import com.project.librarymanagementsystem.dto.BookResponseDTO;
import com.project.librarymanagementsystem.model.Book;
import com.project.librarymanagementsystem.repository.BookRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDTO addBook(BookRequestDTO bookDTO) {

        Book book = new Book();

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setPrice(bookDTO.getPrice());

        Book savedBook = bookRepository.save(book);

        return convertToResponseDTO(savedBook);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDTO getBookById(Long id) {

        Book book = bookRepository.findById(id).orElse(null);

        if (book != null) {
            return convertToResponseDTO(book);
        }

        return null;
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookDTO) {

        Book existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {

            existingBook.setTitle(bookDTO.getTitle());
            existingBook.setAuthor(bookDTO.getAuthor());
            existingBook.setCategory(bookDTO.getCategory());
            existingBook.setPrice(bookDTO.getPrice());

            Book updatedBook = bookRepository.save(existingBook);

            return convertToResponseDTO(updatedBook);
        }

        return null;
    }

    @Override
    public boolean deleteBook(Long id) {

        if (bookRepository.existsById(id)) {

            bookRepository.deleteById(id);

            return true;
        }

        return false;
    }

    private BookResponseDTO convertToResponseDTO(Book book) {

        return new BookResponseDTO(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getPrice()
        );
    }
}