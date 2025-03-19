import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import 'bootstrap/dist/css/bootstrap.min.css';
import { AirportService, Airport } from '../../services/airport.service';

@Component({
  selector: 'app-search-flights',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './search-flights.component.html',
  styleUrls: ['./search-flights.component.css']
})
export class SearchFlightsComponent implements OnInit {
  city: string = '';
  airportCode: string = '';
  airportData: Airport[] = [];
  airportOptions: Airport[] = [];
  citySuggestions: string[] = [];

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef,
    private airportService: AirportService
  ) {}

  ngOnInit(): void {
    this.fetchAirports();
    this.route.queryParams.subscribe(params => {
      if (params['airportCode']) {
        this.airportCode = params['airportCode'];
      }
    });
  }

  fetchAirports(): void {
    this.airportService.getAirports().subscribe({
      next: (data: Airport[]) => {
        this.airportData = data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Ошибка при получении данных аэропортов:', err);
      }
    });
  }

  get allCities(): string[] {
    return [...new Set(this.airportData.map(item => item.city))];
  }

  handleCityChange(event: any): void {
    const value = event.target.value;
    this.city = value;
    this.citySuggestions = this.allCities.filter(c =>
      c.toLowerCase().includes(value.toLowerCase())
    );

    this.airportOptions = this.allCities.includes(value)
      ? this.airportData.filter(item => item.city === value)
      : [];

    this.airportCode = '';
    this.cdr.detectChanges();
  }

  handleSuggestionClick(suggestion: string): void {
    this.city = suggestion;
    this.citySuggestions = [];
    this.airportOptions = this.airportData.filter(item => item.city === suggestion);
    this.cdr.detectChanges();
  }

  handleSubmit(event: Event): void {
    event.preventDefault();
    if (!this.airportCode) {
      alert("Пожалуйста, выберите аэропорт из списка после выбора города.");
      return;
    }
    this.router.navigate([], { 
      queryParams: { airportCode: this.airportCode },
      queryParamsHandling: 'merge'
    });
  }

  trackByCity(index: number, item: string): string {
    return item;
  }

  trackByAirport(index: number, item: Airport): string {
    return item.airportCode;
  }
}
