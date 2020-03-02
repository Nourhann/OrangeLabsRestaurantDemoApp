import { Component, OnInit } from '@angular/core';
import { Http } from "@angular/http";

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  constructor(private http:Http) { }

  ngOnInit() {
  }
  
  addTable(capacityValue){
     let table = {capacity : capacityValue};
     this.http.post('http://localhost:8080/tables/',JSON.stringify(table))
     .subscribe(response => {

     })
  }
}
