import { Component } from "@angular/core";
import { NgForm } from "@angular/forms";
import Book , { BookKeys } from "src/app/model/Book";
import BookService from "src/app/service/book.service";
import BookError , { BookErrorType } from "src/app/model/BookError";
import { Router } from "@angular/router";

@Component({
    selector : 'component',
    templateUrl : './add-book.component.html',
})
export default class AddBookComponent {

    constructor( private bookService : BookService , private router : Router  ){}

    book : Book = new Book();
    error = BookError;

    private validate( target : string , value : string | number ) : void {

        let key = target as BookErrorType;

        target = target.charAt(0).toUpperCase() + target.substring(1);

        if( value == "" || value == undefined ){
            this.error[key] = { hasError : true , msg : `${target} must not be empty!` }
        }else{
            this.error[key] = { hasError : false , msg : '' };
        }      

    }


    handleAddBook( form : NgForm ) : void {

        for( let key of BookKeys ){

            let bookKey = key as keyof Book;//making each key from BookKeys to be type of Book
            this.validate( key , this.book[bookKey]);
        }

        if( form.valid ){
            
            this.bookService.addBook( this.book )
            .subscribe({
                next : ( res ) => {
                    if( res.ok ){
                        alert("Succesfully Created!");
                        this.router.navigate(
                            ["/books"],
                            // { queryParams : {
                            //     msg : "Successfully Added"
                            // }}
                        )
                    }
                    else{
                       alert(res.msg);
                    }
                },
                error : (e) => console.error( e )
            });
        }  

    }

}