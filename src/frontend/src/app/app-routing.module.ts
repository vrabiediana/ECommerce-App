import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductsListComponent} from "./products-list/products-list.component";
import {SignupComponent} from "./signup/signup.component";

const routes: Routes = [
  {path: 'products', title:"Products", component: ProductsListComponent},
  {path: 'signup', component: SignupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
