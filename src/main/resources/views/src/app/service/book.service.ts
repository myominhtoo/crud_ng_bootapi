import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import Book from "../model/Book";
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

}