import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../productos.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Producto } from '../producto';
import Swal from 'sweetalert2';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['form.component.css']
})
export class FormComponent implements OnInit {
  public producto: Producto = new Producto();
  public usuario: Usuario = new Usuario();

  usuarios: Usuario[];

  public titulo: string = 'Crear Producto';
  public errores: string[];

  constructor(
    private productoService: ProductoService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.productoService
      .getUsuarios()
      .subscribe((usuarios) => this.config(usuarios));
  }
  cargarProducto(): void {
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id'];
      if (id) {
        this.productoService
          .getProducto(id)
          .subscribe((producto) => (this.producto = producto));
      }
    });
  }
  configMapper(producto: any): void {
    this.producto = producto;
    this.usuario = producto.usuario
  }

  config(usuarios: any): void {
    this.usuarios = usuarios;
    this.cargarProducto();
    this.producto.usuario = this.usuario;
  }

  /* Crear un nuevo producto y recibir respuesta*/
  public create(): void {
    this.producto.usuario.id = this.usuario.id;
    this.productoService.create(this.producto).subscribe((response) => {
      this.router.navigate(['/productos']);
      Swal.fire(
        'Producto Guardado',
        `Producto ${this.producto.nombre} creado con exito`,
        'success'
      );
    }, err => {
      this.errores = err.error.errors as string[];
      console.log(err.error.errors)
      console.log(err.status)
    });
  }
/* Actualiza un producto y recibir respuesta*/
  update(): void {
    this.productoService.update(this.producto).subscribe((producto) => {
      this.router.navigate(['/productos']);
      Swal.fire(
        'Producto Actualizado',
        `Producto ${this.producto.nombre} editado con exito`,
        'success'
      );
      }, err => {
        this.errores = err.error.errors as string[];
        console.log(err.error.errors)
        console.log(err.status)
      });
  }
}
