import { Component, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import Book, { BookKeys } from "src/app/model/Book";
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

    status = {
        isUpdating : false,
        hasError : false,
        msg : "",
    }

    ngOnInit() : void {
        let bookCode = this.route.snapshot.params["id"];
        this.handleFetchBook( bookCode )
    }

    handleFetchBook( bookCode : string ){
        this.bookService.getBook( bookCode )
        .subscribe({
            next : ( res ) => {
               if( res == null ){
                 this.router.navigate(["/books"] , {
                    queryParams : {
                        msg : "Something went wrong with book code!",
                        hasError : true
                    }
                 });
               }

               this.book = res;
            },
            error : ( e ) => console.log( e )
        })
    }

    handleUpdateBook( form : NgForm ){

        for( let key of BookKeys ){
            let bookKey = key as keyof Book;//making each key from BookKeys to be type of Book
            this.bookService.validate( key , this.book[bookKey]);
        }


        if( form.valid ){

            this.status.isUpdating = true;
            this.bookService.updateBook( this.book )
            .subscribe({
                next : ( res ) => {
                    this.status.isUpdating = false;

                    if( res.ok ){
                        this.router.navigate(["/books"] , {
                            queryParams : {
                                msg : "Successfully Updated"
                            }
                        })
                    }
                    else{
                        this.status.hasError = true;
                        this.status.msg = res.msg;
                    }
                },
                error : ( e ) => console.error( e )
            })

        }

    }

}