import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = environment.baseUrl;
  private url = this.baseUrl + 'api/categories';
  selectedCat: Category;
  catList: any;

  constructor(private http: HttpClient, private router: Router) { }

  categoryIndex() {
    return this.http.get<Category[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('CategoryService.categoryIndex()');
      })
    );
  }


}
