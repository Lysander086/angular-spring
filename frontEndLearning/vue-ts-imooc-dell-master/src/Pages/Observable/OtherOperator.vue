<template></template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

import { of } from 'rxjs';
import { map, tap } from 'rxjs/operators';

@Component
export default class extends Vue {
  mounted() {
    /* map */
    function f5() {
      const observable = of(1, 2, 3);
      const opt = map((num) => 'hello world');
      const newObservable = opt(observable);
      newObservable.subscribe((data) => console.log(data));
    }
    f5();

    /* tap */
    function f6() {
      /* 生产数据 */
      const observable = of(1, 2, 3);
      const newObservable = observable.pipe(
        tap((num) => console.log(num)),
        map((num) => 'hello world')
      );
      /* 消费数据 */
      newObservable.subscribe((data) => console.log(data));
    }
    f6();
  }
}
</script>

<style lang="scss" scoped></style>
