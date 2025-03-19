import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';
import { SearchFlightsComponent } from '../components/search-flights/search-flights.component';
import { FlightResultsTwoColumnsComponent } from '../components/flight-results-two-columns/flight-results-two-columns.component';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent, SearchFlightsComponent, FlightResultsTwoColumnsComponent],
  template: `
    <app-header></app-header>
    <div class="min-vh-100">
      <!-- SearchFlightsComponent должен иметь Output (onSearch) для передачи параметров поиска -->
      <app-search-flights (onSearch)="handleSearch($event)"></app-search-flights>
      <p *ngIf="loading">Загрузка...</p>
      <p *ngIf="error" style="color: red;">Ошибка: {{ error }}</p>
      <ng-container *ngIf="flights">
        <app-flight-results-two-columns [flightsData]="flights"></app-flight-results-two-columns>
      </ng-container>
    </div>
    <app-footer></app-footer>
  `
})
export class SearchComponent implements OnInit {
  flights: any = null;
  loading: boolean = false;
  error: string | null = null;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    // При изменении query-параметров (например, airportCode) выполняется поиск
    this.route.queryParams.subscribe(params => {
      const airportCode = params['airportCode'];
      if (airportCode) {
        this.handleSearch({ airportCode });
      }
    });
  }

  async handleSearch(searchParams: any) {
    this.loading = true;
    this.error = null;
    try {
      const response = await fetch('/api/flights/search', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(searchParams)
      });
      if (!response.ok) {
        throw new Error('Ошибка при поиске рейсов');
      }
      this.flights = await response.json();
    } catch (err: any) {
      this.error = err.message;
    } finally {
      this.loading = false;
    }
  }
}
