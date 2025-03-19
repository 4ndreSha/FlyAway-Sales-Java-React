import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  constructor(private http: HttpClient) {}

  createBooking(flightId: string): Observable<string> {
    return this.http.post<string>(
      '/api/bookings/create',
      { flightId },
      { withCredentials: true, responseType: 'text' as 'json' }
    );
  }
}
