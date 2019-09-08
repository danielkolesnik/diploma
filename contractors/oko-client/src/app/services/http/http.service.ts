import { Injectable } from '@angular/core';
import { LocalStorageService } from '../local-storage.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
// import { PostRequestOptionsInterface } from './interfaces/post-request-options';
// import { PutRequestOptionsInterface } from './interfaces/put-request-options';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private readonly baseUrl =
    environment.apiBaseUrl +
    environment.apiPrefix +
    environment.apiVersion;
  private httpHeaders = new HttpHeaders({
    'Authorization': `Bearer ${this.localStorageSerive.getUser().token}`,
  });
  constructor(private localStorageSerive: LocalStorageService, private http: HttpClient) { }

  public get<T>(url: string, paramsObject?: any): Observable<T> {
    Object.keys(paramsObject).forEach(
      key => !paramsObject[key] && delete paramsObject[key],
    );

    return this.http.get<T>(`${this.baseUrl}/${url}`, {
      params: paramsObject,
      headers: this.httpHeaders,
    });
  }

  public post<T>(
    url: string,
    requestBody: any,
  ): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}/${url}`, requestBody, {
      headers: this.httpHeaders,
    });
  }

  public put<T>(
    url: string,
    requestBody: any,
  ): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${url}`, requestBody, {
      headers: this.httpHeaders,
    });
  }

  public delete<T>(
    url: string,
  ): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${url}`, {
      headers: this.httpHeaders,
    });
  }
}
