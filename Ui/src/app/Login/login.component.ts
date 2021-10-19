import { Component, ViewChild, OnInit, AfterViewInit, ElementRef } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit, AfterViewInit {
  @ViewChild('username', { static: false })
  username!: ElementRef;

  authenticationError = false;



  constructor(


  ) {}

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
    this.username.nativeElement.focus();
  }

  login(): void {
    ;
  }
}
