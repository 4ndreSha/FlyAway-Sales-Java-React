import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../components/header/header.component';
import { FooterComponent } from '../components/footer/footer.component';

@Component({
  selector: 'app-flight-details',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  template: `
    <app-header></app-header>
    <div class="container mt-4 vh-100" *ngIf="flight; else loadingTemplate">
      <h2>Детали рейса</h2>
      <p *ngIf="message" class="text-success">{{ message }}</p>
      <div class="card p-3">
        <p><strong>Номер рейса:</strong> {{ flight.flightNo }}</p>
        <p>
          <strong>Время вылета:</strong>
          {{ flight.scheduledDeparture | date:'short' }}
        </p>
        <p>
          <strong>Время прибытия:</strong>
          {{ flight.scheduledArrival | date:'short' }}
        </p>
        <p><strong>Аэропорт вылета:</strong> {{ flight.departureAirport }}</p>
        <p><strong>Аэропорт прибытия:</strong> {{ flight.arrivalAirport }}</p>
        <p><strong>Статус:</strong> {{ flight.status }}</p>
        <button class="btn btn-primary" (click)="handleBooking()">Забронировать</button>
      </div>
    </div>
    <ng-template #loadingTemplate>
      <p class="text-center">Загрузка...</p>
    </ng-template>
    <app-footer></app-footer>
  `
})
export class FlightDetailsComponent implements OnInit {
  flight: any = null;
  message: string = '';
  flightId: string = '';

  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit() {
    this.flightId = this.route.snapshot.paramMap.get('flightId')!;
    this.fetchFlightDetails();
  }

  async fetchFlightDetails() {
    try {
      const response = await fetch(`/api/flights/${this.flightId}`);
      if (!response.ok) {
        throw new Error('Рейс не найден');
      }
      this.flight = await response.json();
    } catch (error) {
      console.error('Ошибка загрузки данных о рейсе:', error);
    }
  }

  async handleBooking() {
    try {
      const response = await fetch('/api/bookings/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify({ flightId: this.flightId })
      });
      const result = await response.text();

      if (result === 'User not authenticated') {
        this.router.navigate(['/login'], { state: { from: `/flight/${this.flightId}` } });
      } else {
        this.message = 'Бронирование успешно!';
      }
    } catch (error) {
      console.error('Ошибка при бронировании:', error);
    }
  }
}
