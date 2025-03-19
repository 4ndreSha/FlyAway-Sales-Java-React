import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import 'bootstrap/dist/css/bootstrap.min.css';

interface Flight {
  flightId: number | string;
  flightNo: string;
  scheduledDeparture: string;
  scheduledArrival: string;
  status: string;
}

interface FlightsData {
  departures: Flight[];
  arrivals: Flight[];
}

@Component({
  selector: 'app-flight-results-two-columns',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './flight-results-two-columns.component.html',
  styleUrls: ['./flight-results-two-columns.component.css']
})
export class FlightResultsTwoColumnsComponent {
  @Input() flightsData!: FlightsData;

  statusOptions = ['All', 'Scheduled', 'On Time', 'Delayed', 'Departed', 'Arrived', 'Cancelled'];

  depSortOrder: 'asc' | 'desc' = 'asc';
  depStatusFilter: string = 'All';

  arrSortOrder: 'asc' | 'desc' = 'asc';
  arrStatusFilter: string = 'All';

  constructor(private router: Router) {}

  get filteredDepartures() {
    if (!this.flightsData?.departures) return [];
    return this.flightsData.departures
      .filter(flight => this.depStatusFilter === 'All' || flight.status === this.depStatusFilter)
      .sort((a, b) => {
        const timeA = new Date(a.scheduledDeparture).getTime();
        const timeB = new Date(b.scheduledDeparture).getTime();
        return this.depSortOrder === 'asc' ? timeA - timeB : timeB - timeA;
      });
  }

  get filteredArrivals() {
    if (!this.flightsData?.arrivals) return [];
    return this.flightsData.arrivals
      .filter(flight => this.arrStatusFilter === 'All' || flight.status === this.arrStatusFilter)
      .sort((a, b) => {
        const timeA = new Date(a.scheduledArrival).getTime();
        const timeB = new Date(b.scheduledArrival).getTime();
        return this.arrSortOrder === 'asc' ? timeA - timeB : timeB - timeA;
      });
  }

  navigateToFlight(flightId: number | string) {
    this.router.navigate(['/flight', flightId]);
  }
}
