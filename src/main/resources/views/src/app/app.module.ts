import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import AddBookComponent from './components/add-book/add-book.component';
import BooksComponent from './components/books/books.component';
import HomeComponent from './components/home/home.component';
import UpdateBookComponent from './components/update-book/update-book.component';
import NavBarComponent from './layouts/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    AddBookComponent,
    BooksComponent,
    UpdateBookComponent,
    HomeComponent,
    NavBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
