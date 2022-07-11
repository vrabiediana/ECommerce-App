import {Injectable} from "@angular/core";
import {Product} from "../interfaces/product";
import {Observable, of} from "rxjs";
import {HttpClient, HttpClientModule} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url = "/api/products"

  constructor(private http: HttpClient) {
  }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url);
  }

  addProduct(newProduct: Product) {
  }
}
