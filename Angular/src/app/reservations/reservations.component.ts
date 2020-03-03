import { Component, OnInit } from '@angular/core';
import { Http } from "@angular/http";

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {

  reservations:any[];
  constructor(private http:Http) 
  {
     http.get('http://localhost:8080/reservations/').subscribe(response => {
      this.reservations=response.json();
      console.log(response.json());
    });

  }

  ngOnInit() {
  }

  

}
