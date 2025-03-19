import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../../components/header/header.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { FlightService } from '../../services/flight.service';
import { BookingService } from '../../services/booking.service';

@Component({
  selector: 'app-flight-details',
  standalone: true,
  imports: [CommonModule, HeaderComponent, FooterComponent],
  templateUrl: './flight-details.component.html'
})
export class FlightDetailsComponent implements OnInit {
  flight: any = null;
  message: string = '';
  flightId: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private flightService: FlightService,
    private bookingService: BookingService
  ) {}

  ngOnInit(): void {
    this.flightId = this.route.snapshot.paramMap.get('flightId')!;
    this.fetchFlightDetails();
  }

  fetchFlightDetails(): void {
    this.flightService.getFlightDetails(this.flightId).subscribe({
      next: (data) => {
        this.flight = data;
      },
      error: (err) => {
        console.error('Ошибка загрузки данных о рейсе:', err);
      }
    });
  }

  handleBooking(): void {
    this.bookingService.createBooking(this.flightId).subscribe({
      next: (result) => {
        if (result === 'User not authenticated') {
          this.router.navigate(['/login'], { state: { from: `/flight/${this.flightId}` } });
        } else {
          this.message = 'Бронирование успешно!';
        }
      },
      error: (err) => {
        console.error('Ошибка при бронировании:', err);
      }
    });
  }
}
