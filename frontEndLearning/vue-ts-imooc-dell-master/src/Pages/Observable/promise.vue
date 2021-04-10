<template>
  <div
    class="root"
    style="background-color: beige; height: 100px; width: 100px"
  >
    <input type="text" id="name" />
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator';

import { Observable, of } from 'rxjs';

@Component
export default class extends Vue {
  mounted() {

  }
}
</script>

<style lang="scss" scoped></style>
