import { TestBed } from '@angular/core/testing';

import { OfertaAcademicaService } from './oferta-academica.service';

describe('OfertaAcademicaService', () => {
  let service: OfertaAcademicaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OfertaAcademicaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
