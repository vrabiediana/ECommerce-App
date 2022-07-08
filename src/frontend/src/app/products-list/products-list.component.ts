import { Component, OnInit } from '@angular/core';
import {map, Observable} from "rxjs";
import {Product} from "../product";
import {ProductService} from "../product.service";

@Component({
  selector: 'app-products-list',
  template: `
    <div class="card text-center pt-5">
        <div class="card-title ">
            <h1 class="card-title">Products</h1>
        </div>

    </div>
    <ul>
        <app-product-item *ngFor="let product of products | async" [product]="product"></app-product-item>
    </ul>
  `,
  styles: [
  ]
})
export class ProductsListComponent implements OnInit {
  products: Observable<Product[]>

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.products = this.productService.getProducts().pipe(
      map((products) => {
        return products.map((product) => {
          return product;
        })
      })
    );
  }

}
