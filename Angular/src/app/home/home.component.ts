import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { Http } from "@angular/http";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  tables:any[];
  private router:Router;
  constructor(private http:Http) 
  {
     http.get('http://localhost:8080/tables/').subscribe(response => {
      this.tables=response.json();
      console.log(response.json());
    });

   }
  ngOnInit() {
    this.getData();
    //console.log(this.cards);
  }
  getData(){
 
  }

deleteTable(table){
   this.http.delete('http://localhost:8080/tables/'+table.id)
   .subscribe(response => {
      console.log(response);
      let index = this.tables.indexOf(table);
      this.tables.splice(index,1);
    });
}
}
