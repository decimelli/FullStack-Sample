package com.decimelli.backend.api.book;

import com.decimelli.backend.model.Book;
import com.decimelli.backend.service.BookService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Date;

@Path("/books")
@Singleton
public class BookEndpoint {

    @Inject
    BookService bookService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book) {
        book.setCreated(new Date());
        bookService.addBook(book);
        return Response.ok(book, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooks() {
        return Response.ok(bookService.getAllBooks()).build();
    }
}
