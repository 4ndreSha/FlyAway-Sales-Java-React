import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import 'bootstrap/dist/css/bootstrap.min.css';

interface Airport {
  city: string;
  airportCode: string;
  airportName: string;
}

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
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.fetchAirports();
    this.route.queryParams.subscribe(params => {
      if (params['airportCode']) {
        this.airportCode = params['airportCode'];
      }
    });
  }

  async fetchAirports() {
    try {
      const response = await fetch('/api/airports');
      if (!response.ok) {
        throw new Error('Ошибка при получении данных аэропортов');
      }
      const data = await response.json();
      this.airportData = data;
      this.cdr.detectChanges();
    } catch (err) {
      console.error(err);
    }
  }

  get allCities(): string[] {
    return [...new Set(this.airportData.map(item => item.city))];
  }

  handleCityChange(event: any) {
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

  handleSuggestionClick(suggestion: string) {
    this.city = suggestion;
    this.citySuggestions = [];
    this.airportOptions = this.airportData.filter(item => item.city === suggestion);
    this.cdr.detectChanges();
  }

  handleSubmit(event: Event) {
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