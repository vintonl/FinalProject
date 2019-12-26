import { Pipe, PipeTransform } from '@angular/core';
import { Gear } from '../models/gear';
import { Category } from '../models/category';
import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';

@Pipe({
  name: 'gearCategory'
})
export class GearCategoryPipe implements PipeTransform {

  // String may need to be Category for table?
  transform(gearList: Gear[], category: string): Gear[] {
    if( category === 'all') {
      return gearList;
    }
    const result: Gear[] = [];
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < gearList.length; i++) {
      const gear = gearList[i];
      // tslint:disable-next-line: prefer-for-of
      for (let j = 0; j < gear.categories.length; j++) {
        if (gear.categories[j].name === category) {
          result.push(gear);
        }
      }
    }
    return result;
  }

}
