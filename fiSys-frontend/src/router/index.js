import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        //txw 2020-11-13 将默认跳转的地址改为登录页面
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/',
        //txw 2020-11-13 设置主页面的路由并嵌套子页面
        name: 'Home',
        component: () => import('../views/Home.vue'),
        children: [
            {
                path: '/dashboard',
                //txw 2020-11-13 将默认跳转的地址改为登录页面
                name: 'Dashboard',
                component: () => import('../views/Dashboard.vue')
            },
            {
                path: '/pwdsetting',
                //txw 2020-11-16 添加修改密码的跳转路由
                name: 'PwdSetting',
                component: () => import('../views/PwdSetting.vue'),
                //txw 2020-11-16 增加元数据-是否需要认证
                meta:{requiredAuth:false}
            },
            {
                path: '/orderquery',
                //txw 2020-11-17 添加订单查询的跳转路由
                name: 'OrderQuery',
                component: () => import('../views/OrderQuery.vue'),
                //txw 2020-11-17 增加元数据-是否需要认证
                meta:{requiredAuth:false}
            },
            {
                path: '/hisorderquery',
                //txw 2020-11-18 添加历史订单查询的跳转路由
                name: 'HisOrderQuery',
                component: () => import('../views/HisOrderQuery.vue'),
                //txw 2020-11-18 增加元数据-是否需要认证
                meta:{requiredAuth:false}
            },
            {
                path: '/transfer',
                //txw 2020-11-17 添加银行转账页面的跳转路由
                name: 'Transfer',
                component: () => import('../views/Transfer.vue'),
                //txw 2020-11-17 增加元数据-是否需要认证
                meta:{requiredAuth:false}
            },
            {
                path: '/buy',
                //txw 2020-11-19 添加股票买入的跳转路由
                name: 'Buy',
                component: () => import('../views/Buy.vue'),
                //txw 2020-11-19 增加元数据-是否需要认证
                meta:{requiredAuth:false}
            },
        ]
    },
    {
        path: '/404',
        //txw 2020-11-16 设置友好的404错误页面
        name: '404',
        component: () => import('../views/404.vue')
    },
    {
        path: '*',
        //txw 2020-11-16 错误访问直接跳转至404页面
        redirect:'/404'
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

//2020-11-16 路由拦截器:判断请求路径是否合法而选择进入对应的页面
router.beforeEach((to, from, next) => {
    if (to.meta.requiredAuth){
        if (Boolean(sessionStorage.getItem("uid"))){
            next();
        }else {
            next({
                path:"/",
            })
        }
    }else {
        next();
    }
})

export default router
