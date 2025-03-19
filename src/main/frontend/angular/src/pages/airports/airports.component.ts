import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { AirportsMapComponent } from '../../components/airports-map/airports-map.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { AirportService } from '../../services/airport.service';

@Component({
  selector: 'app-airports',
  standalone: true,
  imports: [CommonModule, HeaderComponent, AirportsMapComponent, FooterComponent],
  templateUrl: './airports.component.html'
})
export class AirportsComponent implements OnInit {
  airports: any[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(private airportService: AirportService) {}

  ngOnInit(): void {
    this.loading = true;
    this.airportService.getAirports().subscribe({
      next: (data: any[]) => {
        this.airports = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = err.message;
        this.loading = false;
      }
    });
  }
}
