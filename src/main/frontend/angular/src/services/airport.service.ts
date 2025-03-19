import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Airport {
  city: string;
  airportCode: string;
  airportName: string;
  coordinates?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AirportService {
  constructor(private http: HttpClient) {}

  getAirports(): Observable<Airport[]> {
    return this.http.get<Airport[]>('/api/airports');
  }
}
