import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import {SuiModule} from 'ng2-semantic-ui';
import { AppComponent } from './app.component';
import { CardComponent } from './available-table/available-table.component';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { AddItemComponent } from './add-item/add-item.component';
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    CardComponent,
    HomeComponent,
    NavBarComponent,
    AddItemComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
       path : 'home',
       component : HomeComponent
      },
       {
       path : 'additem',
       component : AddItemComponent
      },
        {
       path : 'reserveitem',
       component : CardComponent
      },
      
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
