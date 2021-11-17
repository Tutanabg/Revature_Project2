import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  address1: string = "1312 S Sossaman Rd "
  phone1: string = "555-867-5301";
  email1: string = "shop1@email.com"
  owner1: string = "Thomas Latham"
  
  address2: string = "23 E Braodway Rd."
  phone2: string = "555-867-5302";
  email2: string = "shop2@email.com"
  owner2: string = "Michael Than"

  address3: string = "1234 Washington Street"
  phone3: string = "555-867-5303";
  email3: string = "shop3@email.com"
  owner3: string = "Chris Wang"

  address4: string = "342 Southern Ave."
  phone4: string = "555-867-5304";
  email4: string = "shop4@email.com"
  owner4: string = "Tutan Negash Abegaz"

  address5: string = "1123 E. Garnet Ave"
  phone5: string = "555-867-5305";
  email5: string = "shop5@email.com"
  owner5: string = "Shamim Rahman"

}
