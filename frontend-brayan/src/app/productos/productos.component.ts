import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './productos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
})
export class ProductosComponent implements OnInit {
  productos: Producto[];
  fixing(productos): void {
    this.productos = productos;
  }
  constructor(private productoService: ProductoService) {}

  ngOnInit(): void {
    this.listarProductos();
  }


  listarProductos(): void {
    const palabraClave = document.getElementById('filter') as HTMLInputElement | null;
    this.productoService
      .getProductos(palabraClave.value)
      .subscribe((productos) => this.fixing(productos));
  }

  delete(producto: Producto): void {
    Swal.fire({
      title: 'Esta seguro?',
      text: `Seguro que desea eliminar al producto ${producto.nombre}!`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!',
    }).then((result) => {
      if (result.isConfirmed) {
        this.productoService.delete(producto.id).subscribe((response) => {
          this.productos = this.productos.filter((pro) => pro !== producto);
          Swal.fire(
            'Eliminado!',
            `Producto ${producto.nombre} eliminado con exito.`,
            'success'
          );
        });
      }
    });
  }
}
