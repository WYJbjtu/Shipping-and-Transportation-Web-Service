import {createRouter, createWebHistory} from 'vue-router'

const Login = () => import('../views/Login')
const Register = () => import('../views/Register')
const Layout = () => import('../layout/Layout')
const Person = () => import('../views/Person')
const User = () => import('../views/User')
const Wuliu = () => import('../views/WuLiu')
const Order = () => import('../views/Order')
const OrderList = () => import('../views/OrderList')
const Expresspoints = () => import('../views/Expresspoints')

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        children: [
            {
                path: 'person',
                name: 'Person',
                component: Person
            },
            {
                path: 'user',
                name: 'User',
                component: User
            },
            {
                path: 'order',
                name: 'Order',
                component: Order
            },
            {
                path: 'orderList',
                name: 'OrderList',
                component: OrderList
            },
            {
                path: 'expresspoints',
                name: 'Expresspoints',
                component: Expresspoints
            },
            {
                path: 'wuliu',
                name: 'Wuliu',
                component: Wuliu
            },
        ],
        //路由自动跳转
        redirect: "/login"
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
