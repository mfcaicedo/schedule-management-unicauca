import { AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-paginador',
  templateUrl: './paginador.component.html',
  styleUrls: ['./paginador.component.scss']
})
export class PaginadorComponent implements OnInit,OnChanges{

  paginas:number[]=[];
  @Input('totalItems') totalItems!: number;
  @Input('totalNumberPage') totalNumberPage:number=1
  @Input('pageSize') pageSize: number=5;
  @Output() pageChanged = new EventEmitter<number[]>();
  currentPage:number=1;
  selectPageSize:number[]=[5,10,15,20,30]

  constructor(){

  }

  ngOnInit(){

    // this.paginas = this.fillPaginas();

  }
  ngAfterViewInit(): void {

  }
  ngOnChanges(changes: SimpleChanges): void {
      if(changes['totalItems']){
        this.totalItems=changes['totalItems'].currentValue
        this.paginas = this.fillPaginas();
      }
      if(changes['totalNumberPage']){
        this.totalNumberPage=changes['totalNumberPage'].currentValue
      }
      if(changes['pageSize']){
        this.pageSize=changes['pageSize'].currentValue
        // console.log("Cambio PageSize ",this.pageSize)
      }

      this.paginas = this.fillPaginas();
      // console.log("total Items ",this.totalItems, " total paginas ",this.totalNumberPage , " Items por pagina ",this.pageSize)
  }
  fillPaginas(){
    // const size = Math.ceil(this.totalItems/this.pageSize);
    const size =this.totalNumberPage
    const array: number[]=[];
    for (let index = 0; index < size; index++) {
      array[index]=index+1;

    }
    return array;
  }


  onChangePage(page:number){
    this.currentPage=page;
    console.log("Emitiendo desde paginador",this.currentPage , " page size ",this.pageSize)
    this.pageChanged.emit([this.currentPage,this.pageSize]);
  }

  onPreviusPage(){
    if(this.currentPage!=1){
      this.currentPage -=1;
    }
    this.pageChanged.emit([this.currentPage,this.pageSize]);
  }
  onNextPage(){
    this.currentPage += 1;
    this.pageChanged.emit([this.currentPage,this.pageSize]);
  }
  onSelectingPageSize(size:number){
    // console.log("size seleccionado ",size)
    this.pageSize=size
    this.pageChanged.emit([this.currentPage,this.pageSize])
    this.fillPaginas()
  }
}
