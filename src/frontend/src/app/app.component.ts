import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <nav class="navbar navbar-expand-md navbar-dark bg-dark py-auto px-3">
      <a class="navbar-brand" href="#">E-Commerce</a>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/products">Products</a>
          </li>
        </ul>
      </div>
      <div class="collapse navbar-collapse flex-row-reverse">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/login">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/signup">Sign Up</a>
          </li>
        </ul>
      </div>
    </nav>
    <app-products-list></app-products-list>
  `
})
export class AppComponent {
  title = 'frontend';
}
