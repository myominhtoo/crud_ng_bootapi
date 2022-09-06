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
        isBlank : false
    } 


    constructor( private bookService : BookService ){}

    ngOnInit() : void {
        this.status.isFetching = true;

        this.bookService.getBooks().subscribe({
            next : ( datas ) => {

               this.status.isFetching = false;

               if( datas.length == 0 ) this.status.isBlank = true;

               this.books = datas;
            },
            error : ( e ) => console.error(e)
        })
    }

}