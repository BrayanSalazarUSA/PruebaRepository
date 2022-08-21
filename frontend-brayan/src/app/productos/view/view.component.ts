import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../productos.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css'],
})
export class ViewComponent implements OnInit {
  public usuario: Usuario = new Usuario();
  constructor(
    private productoService: ProductoService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      let id = params['id'];
      if (id) {
        this.productoService
          .getUsuario(id)
          .subscribe((usuario) => (this.usuario = usuario));
      }
    });
  }
}
