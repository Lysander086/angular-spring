import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HEROES } from '../mock-heroes';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss'],
})
export class HeroesComponent implements OnInit {
  heroes = HEROES;

  // strictNullChecks
  selectedHero?: Hero;

  constructor() {}

  ngOnInit() {}

  onSelect(hero: Hero): void {
    if (this.selectedHero === hero) {
      this.selectedHero = undefined;
    } else this.selectedHero = hero;
  }
}
