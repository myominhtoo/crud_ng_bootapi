import { Component, OnInit } from "@angular/core";
import Book from "src/app/model/Book";
import BookService from "src/app/service/book.service";

@Component({
    selector : 'books',
    templateUrl : './books.component.html'
})
export default class BooksComponent implements OnInit {

    books : Book[] = [];
    
    status  = {
        isFetching : false,
        isBlank : false,
        isDeleting : false,
        deleteTarget : "",
    } 

    message = {
        hasError : false,
        text : "",
    }


    constructor( private bookService : BookService ){}

    ngOnInit() : void {
        this.status.isFetching = true;

        this.handleGetBooks();
    }

    handleGetBooks() : void {
        this.bookService.getBooks().subscribe({
            next : ( datas ) => {

               this.status.isFetching = false;

               if( datas.length == 0 ) this.status.isBlank = true;

               this.books = datas;
            },
            error : ( e ) => console.error(e)
        })
    }

    handleDeleteBook( bookCode  : string ) : void {
        if( confirm("Are you sure to delete?")){
            this.status.isDeleting = true;
            this.status.deleteTarget = bookCode;

            this.bookService.deleteBook( bookCode )
            .subscribe({
                next : ( res ) => {
                    this.status.isDeleting = false;

                    this.message.hasError = !res.ok;
                    this.message.text = res.msg;

                    console.log( this.message )

                    if( res.ok ) this.handleGetBooks();                   
                },
                error : ( e ) => console.error( e )
            })
        }
    }

}