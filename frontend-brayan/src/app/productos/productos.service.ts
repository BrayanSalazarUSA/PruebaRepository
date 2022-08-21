import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Producto } from './producto';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { catchError, throwError } from 'rxjs';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {
  private urlEndPointProductos: string = 'http://localhost:8080/api/productos';
  private urlEndPointUsuario: string = 'http://localhost:8080/api/usuarios';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient, private router:Router) {}

  getProductos(palabraClave:string): Observable<Producto[]>{
    return this.http.get<Producto[]>(`${this.urlEndPointProductos}?palabraClave=${palabraClave}`);
  }

  getProducto(id:number): Observable<Producto>{
    return this.http.get<Producto>(`${this.urlEndPointProductos}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/productos'])
        Swal.fire('Error al editar', e.error.mensaje, 'error');
        return throwError(e);
      })
    );
  }

  getUsuario(id:number): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPointUsuario}/${id}`);
  }

  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.urlEndPointUsuario);
  }


  create(producto:Producto): Observable<Producto>{
    return this.http.post<Producto>(this.urlEndPointProductos, producto,
      {headers: this.httpHeaders}).pipe(
        catchError(e => {

          if(e.status==400){
            return throwError(e);
          }

          console.error(e.error.mensaje);
          Swal.fire('Error al crear el producto', e.error.mensaje, 'error')
          return throwError(e);
        })
      )
  }


  update(producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(
      `${this.urlEndPointProductos}/${producto.id}`,
      producto,
      { headers: this.httpHeaders }
    ).pipe(
      catchError(e => {

        if(e.status==400){
          return throwError(e);
        }

        console.error(e.error.mensaje);
        Swal.fire('Error al editar el producto', e.error.mensaje, 'error')
        return throwError(e);
      })
    )
  }

  delete(id: number): Observable<Producto> {
    return this.http.delete<Producto>(`${this.urlEndPointProductos}/${id}`, {
      headers: this.httpHeaders,
    });
  }

}
