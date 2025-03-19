import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../components/header/header.component';
import { AirportsMapComponent } from '../components/airports-map/airports-map.component';
import { FooterComponent } from '../components/footer/footer.component';

@Component({
  selector: 'app-airports',
  standalone: true,
  imports: [CommonModule, HeaderComponent, AirportsMapComponent, FooterComponent],
  template: `
    <app-header></app-header>
    <app-airports-map></app-airports-map>
    <div class="container mt-4 min-vh-100">
      <h2>Список аэропортов</h2>
      <p *ngIf="loading">Загрузка...</p>
      <p *ngIf="error" style="color: red;">Ошибка: {{ error }}</p>
      <ng-container *ngIf="airports.length > 0; else noAirports">
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Код аэропорта</th>
              <th>Название</th>
              <th>Город</th>
              <th>Координаты</th>
              <th>Часовой пояс</th>
            </tr>
          </thead>
          <tbody>
            <tr *ngFor="let airport of airports">
              <td>{{ airport.airportCode }}</td>
              <td>{{ airport.airportName }}</td>
              <td>{{ airport.city }}</td>
              <td>{{ airport.coordinates }}</td>
              <td>{{ airport.timezone }}</td>
            </tr>
          </tbody>
        </table>
      </ng-container>
      <ng-template #noAirports>
        <p *ngIf="!loading">Аэропорты не найдены.</p>
      </ng-template>
    </div>
    <app-footer></app-footer>
  `
})
export class AirportsComponent implements OnInit {
  airports: any[] = [];
  loading: boolean = false;
  error: string | null = null;

  async ngOnInit() {
    this.loading = true;
    try {
      const response = await fetch('/api/airports');
      if (!response.ok) {
        throw new Error('Ошибка при получении данных аэропортов');
      }
      this.airports = await response.json();
    } catch (err: any) {
      this.error = err.message;
    } finally {
      this.loading = false;
    }
  }
}
