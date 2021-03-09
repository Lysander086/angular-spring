import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  //state:存在全局变量的位置
  state: {
    posiData:[],
    orderData:[],
    tradeData:[],
    balance:0,
  },
  //对state中的变量进行修改是通过mutations所定义的函数方法进行的
  mutations: {
    updatePosi(state,posiInfo){
      state.posiData = posiInfo;
    },
    updateOrder(state,orderInfo){
      state.orderData = orderInfo;
    },
    updateTrade(state,tradeInfo){
      state.tradeData = tradeInfo;
    },
    updateBalance(state,balance){
      state.balance = balance;
    },
  },
  //与mutations类似，异步操作
  // actions: {
  // },
  //与state类似
  // modules: {
  // }
})
