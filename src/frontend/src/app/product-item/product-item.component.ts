import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../product";

@Component({
  selector: 'app-product-item',
  template: `
    <li>
      {{product.name}}
    </li>
  `,
  styles: [
  ]
})
export class ProductItemComponent implements OnInit {
  @Input()
  product: Product

  constructor() { }

  ngOnInit(): void {
  }

}
