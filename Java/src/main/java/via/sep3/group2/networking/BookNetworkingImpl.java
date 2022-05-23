package via.sep3.group2.networking;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.sep3.group2.persistance.BookDAO;
import via.sep3.group2.shared.BookDTO;
import via.sep3.grpc.book.Book;
import via.sep3.grpc.book.BookServiceGrpc;

import java.util.List;

@GrpcService
public class BookNetworkingImpl extends BookServiceGrpc.BookServiceImplBase {

    private BookDAO bookDAO;

    @Autowired
    public BookNetworkingImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public void createBook(Book.BookMessage request, StreamObserver<Book.EmptyBookMessage> responseObserver){

        BookDTO bookDTO= new BookDTO(request.getIsbn(),request.getTitle(),request.getAuthor(),request.getEdition(),request.getDescription()
                                    ,request.getPrice(),request.getUrl());
        bookDAO.CreateBook(bookDTO);
        Book.EmptyBookMessage reply = Book.EmptyBookMessage.newBuilder().build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllBooks(Book.EmptyBookMessage request, StreamObserver<Book.ListOfBooks> responseObserver){

        List<BookDTO> books= bookDAO.getAllBooks();
        Book.ListOfBooks.Builder builder =  Book.ListOfBooks.newBuilder();

        for (BookDTO book:books
             ) {
            builder.addBooks(book.buildBookMessage());
        }
        Book.ListOfBooks reply = builder.build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();


    }

    @Override
    public void getBookByIsbn(Book.BookIsbnMessage request, StreamObserver<Book.BookMessage> responseObserver){

     BookDTO   book=bookDAO.getBookByIsbn(request.getIsbn());

            Book.BookMessage reply = Book.BookMessage.newBuilder().setIsbn(book.getIsbn()).setTitle(book.getTitle()).setAuthor(book.getAuthor())
                    .setEdition(book.getEdition()).setDescription(book.getDescription()).setPrice(book.getPrice()).setUrl(book.getUrl()).build();



        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }


}
