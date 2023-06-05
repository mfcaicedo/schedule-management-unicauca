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
    alert("DESCARGANDO DE TABLAAA");
    if (tablas.length !== 0) {
      tablas.forEach((tabla: ElementRef, index: number) => {
        const doc = new jsPDF();
        const table = tabla.nativeElement;
        const titulo = tabla.nativeElement.previousElementSibling.textContent.trim();//Esto supone que el elemento h2precede directamente a la tabla en la estructura del HTML.
        
        doc.text(titulo, 10, 10);
        (doc as any).autoTable({ html: table });
        doc.save(`Reporte_${index}_${titulo}.pdf`);
        alert("FinalizadaImpresion");
      });
    }
  }

  generarPDFsDeImagenes(imagenes: ElementRef[]): void {
    alert("DESCARGANDO IMG"+ imagenes.length);
    if (imagenes.length !== 0) {
      imagenes.forEach((imagen: ElementRef, index: number) => {
        const doc = new jsPDF();
        const imagenElement = imagen.nativeElement;
///const titulo = imagen.nativeElement.previousElementSibling.textContent.trim();
  
        const canvas = document.createElement('canvas');
        const context = canvas.getContext('2d');
  
        if (context) {
          canvas.width = imagenElement.width;
          canvas.height = imagenElement.height;
          context.drawImage(imagenElement, 0, 0);
  
          const imageData = canvas.toDataURL('image/jpeg');
  
          doc.addImage(imageData, 'JPEG', 10, 10, imagenElement.width, imagenElement.height);
          ///.save(`Reporte_${index}_${titulo}.pdf`);
        } else {
          // Handle the case where context is null
          console.error('Could not get canvas context');
        }
      });
    }
  }
  
  }
