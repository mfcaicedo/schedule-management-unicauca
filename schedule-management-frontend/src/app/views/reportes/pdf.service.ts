import { ElementRef, Injectable, Renderer2, RendererFactory2 } from '@angular/core';
import jsPDF from 'jspdf';
import 'jspdf-autotable';

@Injectable({
  providedIn: 'root',
})
export class PdfService {
  private renderer: Renderer2;
  constructor(rendererFactory: RendererFactory2) {
    this.renderer = rendererFactory.createRenderer(null, null);
  }
  generarPDFsDeTabla(tablas: ElementRef[]): void {
    if (tablas.length !== 0) {
      tablas.forEach((tabla: ElementRef, index: number) => {
        const doc = new jsPDF();
        const table = tabla.nativeElement;
        const titulo = tabla.nativeElement.previousElementSibling.textContent.trim();//Esto supone que el elemento h2precede directamente a la tabla en la estructura del HTML.
        
        doc.text(titulo, 10, 10);
        (doc as any).autoTable({ html: table });
        doc.save(`Reporte_${index}_${titulo}.pdf`);
        //this.alerta("FinalizadaImpresion");
      });
    }
  }

  generarPDFsDeComponenteCalendario(componentes: ElementRef[]): void {
   
  }
}