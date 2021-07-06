import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(public messageService: MessageService) {}

  // markz: play ts here
  ngOnInit() {
    function printCoordinate(pt: { x: number; y?: number }) {
      console.log('The coordinate\'s x value is ' + pt.x);
      console.log('The coordinate\'s y value is ' + pt.y);
    }

    printCoordinate({ x: 1 });

  }

}
