import Vue, { AsyncComponent } from 'vue'

import VueRouter, { RouteConfig } from 'vue-router'
// import Login from './Pages/Login';

const importByPageName = (page: string): AsyncComponent => () =>
  import(
    /* webpackPrefetch: true */ /* webpackChunkName: "[request]" */ /* webpackMode: "lazy" */ `../src/Pages/${page}`
  )

const routes: RouteConfig[] = [
  {
    path: '/',
    name: 'home',
    alias: '/',
    component: importByPageName('Home'),
  },
  {
    path: '/login',
    name: 'login',
    component: importByPageName('Login'),
  },
  {
    path: '/observables',
    name: 'observables',
    component: importByPageName('observables'),
  },
  {
    path: '/promise',
    name: 'promise',
    component: importByPageName('promise'),
  },
]

const router = new VueRouter({ routes })
Vue.use(VueRouter)

export default router
