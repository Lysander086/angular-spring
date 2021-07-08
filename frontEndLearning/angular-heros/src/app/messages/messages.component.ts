import {Component, OnInit} from '@angular/core';
import {MessageService} from '../message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  stylingClass = {
    color: 'red',
    background: 'blue'
  };

  myClassName = ""

  constructor(public messageService: MessageService) {
  }

  ngOnInit() {

  }

}
