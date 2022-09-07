import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import Book from "src/app/model/Book";
import BookError from "src/app/model/BookError";
import BookService from "src/app/service/book.service";

@Component({
    selector : 'update-book',
    templateUrl : './update-book.component.html',
})
export default class UpdateBookComponent implements OnInit {

    constructor( private bookService : BookService , private route : ActivatedRoute , private router : Router ){}

    book : Book  = new Book();
    error = BookError;

    ngOnInit() : void {
        let bookCode = this.route.snapshot.params["id"];

    }

}