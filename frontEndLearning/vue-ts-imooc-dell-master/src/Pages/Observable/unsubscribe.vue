<template></template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator';
import { Observable, of } from 'rxjs';

export default class extends Vue {
  mounted() {
    function f4() {
      function subscribe(observer: any) {
        var intervalID = setInterval(() => {
          observer.next('launch.....');
        }, 1000);

        return {
          unsubscribe: () => clearInterval(intervalID),
        };
      }

      var subscription = subscribe({ next: (x) => console.log(x) });
      setTimeout(() => subscription.unsubscribe(), 5000);
    }
    f4();
  }
}
</script>

<style lang="scss" scoped></style>
