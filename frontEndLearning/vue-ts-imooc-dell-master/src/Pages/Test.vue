<template>
  <div class="root"></div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import { Observable } from 'rxjs'

@Component
export default class extends Vue {
  mounted() {
    // Create an Observable that will start listening to geolocation updates
    // when a consumer subscribes.
    const locations = new Observable((observer) => {
      let watchId: number
      // Simple geolocation API check provides values to publish
      if ('geolocation' in navigator) {
        watchId = navigator.geolocation.watchPosition(
          (position: Position) => {
            observer.next(position)
          },
          (error: PositionError) => {
            observer.error(error)
          }
        )
      } else {
        observer.error('Geolocation not available')
      }

      // When the consumer unsubscribes, clean up data ready for next subscription.
      return {
        unsubscribe() {
          navigator.geolocation.clearWatch(watchId)
        },
      }
    })

    // Call subscribe() to start listening for updates.
    const locationsSubscription = locations.subscribe({
      next(position: any) {
        console.log('Current Position: ', position)
      },
      error(msg: any) {
        console.log('Error Getting Location: ', msg)
      },
    })

    // Stop listening for location after 10 seconds
    setTimeout(() => {
      locationsSubscription.unsubscribe()
    }, 10000)
  }
}
</script>
