import { Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import * as L from 'leaflet';
import { AirportService, Airport } from '../../services/airport.service';

@Component({
  selector: 'app-airports-map',
  standalone: true,
  templateUrl: './airports-map.component.html'
})
export class AirportsMapComponent implements OnInit, OnDestroy {
  @ViewChild('mapContainer', { static: true }) mapContainer!: ElementRef<HTMLDivElement>;
  map!: L.Map;
  airports: Airport[] = [];

  constructor(private airportService: AirportService) {}

  // Функция экранирования HTML (аналог escapeHtml в React)
  escapeHtml(str: string): string {
    if (!str) return '';
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
  }

  ngOnInit(): void {
    // Установка кастомного маркера
    L.Marker.prototype.options.icon = L.icon({
      iconUrl: 'assets/leaflet/images/marker-icon.png',
      shadowUrl: 'assets/leaflet/images/marker-shadow.png',
      iconSize: [25, 41],
      iconAnchor: [12, 41],
      popupAnchor: [1, -34],
    });

    this.map = L.map(this.mapContainer.nativeElement).setView([55.7558, 37.6173], 4);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    this.airportService.getAirports().subscribe({
      next: (data: Airport[]) => {
        this.airports = data;
        data.forEach(airport => {
          if (airport.coordinates) {
            const trimmed = airport.coordinates.replace(/[()]/g, '');
            const parts = trimmed.split(',');
            if (parts.length === 2) {
              const lon = parseFloat(parts[0].trim());
              const lat = parseFloat(parts[1].trim());
              const popupContent = `
                <div class="p-2">
                  <div class="airport-title mb-2">${this.escapeHtml(airport.airportName)}</div>
                  <div class="d-flex align-items-center text-muted">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#0d6efd" class="bi bi-geo-alt me-2" viewBox="0 0 16 16">
                      <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
                      <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                    </svg>
                    <div>${this.escapeHtml(airport.city)}</div>
                  </div>
                  <a href="/search?airportCode=${encodeURIComponent(airport.airportCode)}"
                     class="btn btn-sm custom-btn mt-2">
                    Просмотреть рейсы
                  </a>
                </div>
              `;
              L.marker([lat, lon]).bindPopup(popupContent).addTo(this.map);
            }
          }
        });
      },
      error: (error) => {
        console.error('Ошибка при получении аэропортов:', error);
      }
    });
  }

  ngOnDestroy(): void {
    if (this.map) {
      this.map.remove();
    }
  }
}
