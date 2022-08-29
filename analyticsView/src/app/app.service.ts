import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {Result} from "./result";

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http: HttpClient) { }

  public getClasification(): Observable<Result[]>{
    return this.http.get<any[]>('http://localhost:8080/network/get');
  }

  public beginTraining(): Observable<any[]>{
    return this.http.get<any[]>('http://localhost:8080/network/train')
  }
}
