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
  /**
   * trae un arreglo de referencias a tablas HTML por cada una 
   * crera un documento de PDF y  toma la referncia de la etiqueta que se encuentre sobbre la tabla 
   * y la asume como el titulo 
   * y lo descarga en el pdf
   * @param tablas  arreglo de referencias HTML
   */
  generarPDFsDeTabla(tablas: ElementRef[]): void {
    //alert("DESCARGANDO DE TABLAAA");
    if (tablas.length !== 0) {
      tablas.forEach((tabla: ElementRef, index: number) => {
        const doc = new jsPDF();
        const table = tabla.nativeElement;
        const titulo = tabla.nativeElement.previousElementSibling.textContent.trim();//Esto supone que el elemento h2precede directamente a la tabla en la estructura del HTML.
        
        doc.text(titulo, 10, 10);
        (doc as any).autoTable({ html: table });
        //doc.save(`Reporte_${index}_${titulo}.pdf`);
        doc.save(`REPORTE DE ${titulo}.pdf`);
        //alert("FinalizadaImpresion");
      });
    }
  }
/**
   * trae un arreglo de referencias a imagenes HTML por cada una 
   * crera un documento de PDF 
   * y lo descarga en el pdf
   * NO SE ESTA USANDO EN NINGUNA PARTE DEL CODIGO
   * POR QUE NO HE LOGRADO TRAER ETIQUETAS DE SUBCOMPONENTES ENTONCES 
   * NOOOOOOOO ESTA PROBADO 
 * @param imagenes 
 */
  _generarPDFsDeImagenes(imagenes: ElementRef[]): void {
    //alert("DESCARGANDO IMG"+ imagenes.length);
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
  
  generarPDFsDeCanvaElement(canvas: HTMLCanvasElement, nombreArchivo:string): void {
    //alert("ACCEDIENDO A PDF SERVICE ...iMAGEN");
    const doc = new jsPDF();  
    // Convierte la imagen de Canvas en una imagen base64
    const imgData = canvas.toDataURL('image/jpeg');
  
    doc.addImage(imgData, 'JPEG', 10, 20, 190, 0); // Agrega la imagen
  
    doc.save(nombreArchivo+`.pdf`);
  }
  }
