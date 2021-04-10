<template>
  <div class="root" style="background-color: red; height: 100px; width: 100px">
    <input type="text" id="name" />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator';

import { Observable, of } from 'rxjs';

@Component
export default class extends Vue {
  mounted() {
    function func2() {
      let observable: Observable<unknown>;
      observable = new Observable((observer: any) => {
        try {
          let time = 0;
          observer.next(1);
          observer.next(2);
          observer.next(3);
          const intervalId = setInterval(() => {
            console.log(`wait ${++time}s`);
          }, 900);
          setTimeout(() => {
            observer.next(4);
            clearInterval(intervalId);
          }, 2000);
        } catch (e) {
          observer.error(e);
        }
        // observer.complete(); // 注意不能立即调用complete函数，不然会终止消息传输
        // setTimeout(() => observer.complete(), 3000);
      });

      const observer = {
        next: (num: any) => console.log(num),
        error: (e: any) => console.log(e),
        complete: () => console.log('complete!!!'),
      };

      observable.subscribe(observer);
    }
    func2();
  }
}
</script>

<style lang="scss" scoped></style>
