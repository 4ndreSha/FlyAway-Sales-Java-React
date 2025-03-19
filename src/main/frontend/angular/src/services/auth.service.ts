import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) {}
  private apiUrl = '/api/auth';

  checkAuthStatus(): Observable<any> {
    return this.http.get<any>('/api/auth/status', { withCredentials: true });
  }

  logout(): Observable<any> {
    return this.http.post<any>('/api/auth/logout', null, { withCredentials: true });
  }

  login(credentials: { email: string; password: string }): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, credentials, {
      withCredentials: true,
      responseType: 'text' as 'json'
    });
  }

  register(formData: any): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/register`, formData, {
      withCredentials: true,
      responseType: 'text' as 'json'
    });
  }
}
