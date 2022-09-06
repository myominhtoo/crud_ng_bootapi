import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import AddBookComponent from './components/addBook.component';
import BooksComponent from './components/books.component';
import UpdateBookComponent from './components/updateBook.component';

const routes: Routes = [
  {
    path : "books",
    component : BooksComponent,
  },
  {
    path : "addbook",
    component : AddBookComponent,
  },
  {
    path : "books/:id",
    component : UpdateBookComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
