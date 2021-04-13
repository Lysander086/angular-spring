<template>
  <div>
    <h1>home</h1>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import request from '../../request'
import moment from 'dayjs'
import { Button } from 'element-ui'
import echarts from 'echarts'
interface CourseItem {
  title: string
  count: number
}
interface DataStructure {
  [key: string]: CourseItem[]
}
interface LineData {
  name: string
  type: string
  data: number[]
}

Vue.use(Button)

@Component
export default class extends Vue {

}
</script>

<style scoped>
.home-page {
  padding: 50px;
  margin: 100px auto;
  border: 1px solid #ddd;
}
.btn-box {
  width: 400px;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  margin-bottom: 40px;
}
</style>
