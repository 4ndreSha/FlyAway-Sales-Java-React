import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = '/api/user';

  constructor(private http: HttpClient) {}

  getUserInfo(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/info`, { withCredentials: true });
  }

  getUserTickets(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/tickets`, { withCredentials: true });
  }

  updateUser(userData: any): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/update`, userData, {
      headers: { 'Content-Type': 'application/json' },
      withCredentials: true,
      responseType: 'text' as 'json'
    });
  }
}
