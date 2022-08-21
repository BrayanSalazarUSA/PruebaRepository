import { Usuario } from "./usuario";

export class Producto {
  id:number;
  nombre: string;
  cantidad: number;
  fechaIngreso:string;
  usuario:Usuario;
}
