import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import Book from "../model/Book";
import error, { BookErrorType } from "../model/BookError";
import HttpResponse from "../model/HttpResponse";


@Injectable({
    providedIn : "root"
})
export default class BookService {

    private BASE_URL = "http://localhost:8080/api";

    constructor( private httpClient : HttpClient ){}

    getBooks():Observable<Book[]> {
        return this.httpClient.get<Book[]>(`${this.BASE_URL}/books`);
    }

    addBook( book : Book ) : Observable<HttpResponse> {
        return this.httpClient.post<HttpResponse>(`${this.BASE_URL}/books` , book );
    }

    getBook( bookCode : string ) : Observable<Book> {
        return this.httpClient.get<Book>(`${this.BASE_URL}/${bookCode}`);
    }

    deleteBook( bookCode : string ) : Observable<HttpResponse> {
        return this.httpClient.delete<HttpResponse>(`${this.BASE_URL}/books/${bookCode}`);
    }

    updateBook( book : Book ) : Observable<HttpResponse> {
        return this.httpClient.put<HttpResponse>(`${this.BASE_URL}/books/${book.bookCode}`, book );
    }

    validate( target : string , value : string | number ) : void {

        let key = target as BookErrorType;

        target = target.charAt(0).toUpperCase() + target.substring(1);

        if( value == "" || value == undefined ){
            error[key] = { hasError : true , msg : `${target} must not be empty!` }
        }else{
            error[key] = { hasError : false , msg : '' };
        }      
    }

}