import { TestBed } from '@angular/core/testing';

import { ColorProgramaDiccionarioService } from './color-programa-diccionario.service';

describe('ColorProgramaDiccionarioService', () => {
  let service: ColorProgramaDiccionarioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ColorProgramaDiccionarioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
