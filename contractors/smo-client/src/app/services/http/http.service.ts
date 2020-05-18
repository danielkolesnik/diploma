import { Injectable } from '@angular/core';
import { LocalStorageService } from '../local-storage.service';
import {HttpClient, HttpEvent, HttpHeaders} from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { PostRequestOptionsInterface } from './interfaces/post-request-options';
import { PutRequestOptionsInterface } from './interfaces/put-request-options';
import {HttpParams} from "@angular/common/http/src/params";
import {HttpResponse} from "@angular/common/http/src/response";

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private readonly baseUrl =
    environment.apiBaseUrl +
    environment.apiPrefix +
    environment.apiVersion;
  constructor(private localStorageService: LocalStorageService, private http: HttpClient) { }

  public get<T>(url: string, paramsObject?: any): Observable<T> {
    if (paramsObject) {
      Object.keys(paramsObject).forEach(
        key => !paramsObject[key] && delete paramsObject[key],
      );
    }

    return this.http.get<T>(`${this.baseUrl}/${url}`, {
      params: paramsObject,
    });
  }

  public post<T>(
    url: string,
    requestBody: any
  ): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}/${url}`, requestBody);
  }

  public _post<T>(
    url: string,
    requestBody: any,
    options?: any
  ): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/${url}`, requestBody, options);
  }

  public put<T>(
    url: string,
    requestBody: any,
  ): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${url}`, requestBody);
  }

  public delete<T>(
    url: string,
  ): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${url}`);
  }
}
