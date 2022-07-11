import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  template: `
    <div class="card text-center pt-4">
      <div class="card-title ">
        <h1 class="card-title">Sign Up</h1>
      </div>
      <hr/>
      <div class="card-body">
        <form>

        </form>
      </div>
    </div>
  `,
  styles: [
  ]
})
export class SignupComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
