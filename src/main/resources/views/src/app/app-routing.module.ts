import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import AddBookComponent from './components/add-book/add-book.component';
import BooksComponent from './components/books/books.component';
import HomeComponent from './components/home/home.component';
import UpdateBookComponent from './components/update-book/update-book.component';

const routes: Routes = [
  {
    path : '',
    component : HomeComponent,
  },
  {
    path : 'books',
    component : BooksComponent,
  },
  {
     path : 'books/:id',
     component : UpdateBookComponent,
  },
  {
    path : "addbook",
    component : AddBookComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
