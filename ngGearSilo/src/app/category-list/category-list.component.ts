import { Component, OnInit } from '@angular/core';
import { CategoryService } from './../services/category.service';
import { Category } from '../models/category';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  // Fields
  catList: Category[] = [];
  selectedCat: Category;
  keyword: string = null;
  list = false;
  searchResult = false;
  searchedCategory: Category[] = [];



  // Constructor

  constructor(private catSvc: CategoryService, private router: Router, private authService: AuthService) { }

  ngOnInit() {

    this.loadCategory();
  }

  loadCategory() {
    this.catSvc.categoryIndex().subscribe(
      (catsLoaded) => {
        this.catList = catsLoaded;
      },
      (catsNotLoaded) => {
        console.log('Issue with loading categories!');
      });
  }

  displayCategory(cat: Category) {
    this.selectedCat = cat;
    this.catSvc.selectedCat = cat;
  }

}
