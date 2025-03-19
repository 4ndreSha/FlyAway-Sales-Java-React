import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightService {
    private apiUrl = '/api/flights';

    constructor(private http: HttpClient) {}

    getFlightDetails(flightId: string): Observable<any> {
        return this.http.get<any>(`/api/flights/${flightId}`);
    }

    searchFlights(searchParams: any): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}/search`, searchParams, {
        headers: { 'Content-Type': 'application/json' }
        });
    }
}
