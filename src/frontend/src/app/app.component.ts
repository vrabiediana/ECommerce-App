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
            <a class="nav-link"
               routerLink="/products"
               routerLinkActive="active"
               ariaCurrentWhenActive="page">Products</a>
          </li>
        </ul>
      </div>
      <div class="collapse navbar-collapse flex-row-reverse">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="/login">Login</a>
          </li>
          <li class="nav-item">
            <a class="nav-link"
               routerLink="/signup"
               routerLinkActive="active"
               ariaCurrentWhenActive="page">Sign Up</a>
          </li>
        </ul>
      </div>
    </nav>
    <router-outlet></router-outlet>
  `
})

export class AppComponent {
  title = 'frontend';

}
