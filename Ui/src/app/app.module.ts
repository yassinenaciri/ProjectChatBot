import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './Login/login.component';
import { InscriptionComponent } from './Inscription/inscription.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    InscriptionComponent
  ],
  imports: [

    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
