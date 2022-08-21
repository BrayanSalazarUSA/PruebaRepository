import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductosComponent } from './productos/productos.component';
import { DirectivaComponent } from './directiva/directiva.component';
import { ProductoService } from './productos/productos.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FormComponent } from './productos/form/form.component';
import { ViewComponent } from './productos/view/view.component';

const routes: Routes = [

  {path:'', redirectTo:'/productos', pathMatch:'full'},
  {path:'productos', component:ProductosComponent},
  {path:'productos/form', component:FormComponent},
  {path:'productos/form/:id', component:FormComponent},
  {path:'usuario/:id', component:ViewComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ProductosComponent,
    DirectivaComponent,
    FormComponent,
    ViewComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),

  ],
  providers: [
    ProductoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
