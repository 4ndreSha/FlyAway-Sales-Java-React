import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { SearchFlightsComponent } from '../../components/search-flights/search-flights.component';
import { FlightResultsTwoColumnsComponent } from '../../components/flight-results-two-columns/flight-results-two-columns.component';
import { FlightService } from '../../services/flight.service';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent, SearchFlightsComponent, FlightResultsTwoColumnsComponent],
  templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {
  flights: any = null;
  loading: boolean = false;
  error: string | null = null;

  constructor(private route: ActivatedRoute, private flightService: FlightService) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const airportCode = params['airportCode'];
      if (airportCode) {
        this.handleSearch({ airportCode });
      }
    });
  }

  handleSearch(searchParams: any) {
    this.loading = true;
    this.error = null;
    this.flightService.searchFlights(searchParams).subscribe({
      next: (data) => {
        this.flights = data;
      },
      error: (err) => {
        this.error = 'Ошибка при поиске рейсов';
        console.error(err);
      },
      complete: () => {
        this.loading = false;
      }
    });
  }
}
