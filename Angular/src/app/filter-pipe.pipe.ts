import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterPipe'
})
export class FilterPipePipe implements PipeTransform {

    transform(items: any[], args: any[]): any {
        return items.filter(item => item.reservationDate.indexOf(args[0]) !== -1);
    }
  /*transform(value: any, args?: any): any {
    return null;
  }*/

}
