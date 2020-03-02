import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Input } from "@angular/core";
import { Router } from "@angular/router";
import { Http } from "@angular/http";
import { HttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-card',
  templateUrl: './available-table.component.html',
  styleUrls: ['./available-table.component.css'],
  providers: [DatePipe]
})
export class CardComponent implements OnInit {
   http:HttpClient;
  tables:any[];
  myDate:any;
  private router:Router;
  constructor(http:Http,private datePipe: DatePipe) 
  {
     this.myDate = new Date();
     this.myDate = datePipe.transform(this.myDate, 'yyyy-MM-dd');
     http.get('http://localhost:8080/tables/available/'+this.myDate).subscribe(response => {
      this.tables=response.json();
      console.log(response.json());
    });

   }

   ngOnInit() {

  }

}
