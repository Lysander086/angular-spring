import { Component, OnInit } from '@angular/core';
import { Hero } from '../hero';
import { HeroService } from '../hero.service';
import { TESTING } from '../config';

import { MessageService } from '../message.service';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.scss'],
})
export class HeroesComponent implements OnInit {
  // In TypeScript, each member is public by default.
  // default: []
  heroes: Hero[];

  // strictNullChecks
  selectedHero?: Hero;

  /**
   * @narration:
   *  (a)Resource
   *  private HeroService heroService;
   * @param heroService
   * @param messageService
   */
  constructor(
    private heroService: HeroService,
    private messageService: MessageService
  ) {
    this.setDefaultValues();
  }

  setDefaultValues() {
    this.heroes = [];
  }

  ngOnInit() {
    this.getHeroes();
    if (TESTING) this.selectedHero = this.heroes[0];
  }

  onSelect(hero: Hero): void {
    if (this.selectedHero === hero) {
      this.selectedHero = undefined;
    } else {
      this.selectedHero = hero;
      this.messageService.add(`HeroesComponent: Selected hero id=${hero.id}`);
    }
  }

  getHeroes(): void {
    this.heroService.getHeroes().subscribe((heroes) => {
      this.heroes = heroes;
    });
  }
}
