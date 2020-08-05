import Vue from 'vue'
import VueRouter from 'vue-router'

import firebase from 'firebase/app'
import 'firebase/auth'

import Home from '../views/Home.vue'
import Profile from '../views/Profile.vue'
import Login from '../views/Login.vue'
import SignUp from '../views/SignUp.vue'
import Match from '../views/Match.vue'
import MyMatches from '../views/MyMatches.vue'
import Walk from '../views/Walk.vue'
import CreateWalk from '../views/CreateWalk.vue'
import User from "../views/User";
import WalkPage from "../views/WalkPage.vue"

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/my-matches',
        name: 'MyMatches',
        component: MyMatches,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/profile',
        name: 'Profile',
        component: Profile,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/user/:id',
        name: 'User',
        component: User,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/match',
        name: 'Match',
        component: Match,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/walk',
        name: 'Walk',
        component: Walk,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/walk/:id',
        name: 'Walk Page',
        component: WalkPage,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '/createwalk',
        name: 'CreateWalk',
        component: CreateWalk,
        meta: {
            requiresAuth: true
        }
    },
    {
        path: '*',
        redirect: '/login'
    },
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/sign-up',
        name: 'SignUp',
        component: SignUp

    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    const currentUser = firebase.auth().currentUser;
    const requiresAuth = to.matched.some(record => record.meta.requiresAuth);

    if (requiresAuth && !currentUser) next('login');
    else if (!requiresAuth && currentUser) next('match');
    else next();
});

export default router
