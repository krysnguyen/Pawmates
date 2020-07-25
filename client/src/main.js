import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import firebase from 'firebase'
import AOS from 'aos'
import 'aos/dist/aos.css'

import App from './App.vue'
import router from './router'

Vue.config.productionTip = false

let app = '';

// Your web app's Firebase configuration
var firebaseConfig = {
  apiKey: "AIzaSyCXDcQ_HiZUXI4YtsgV6Y2X6zvTgtGjau4",
  authDomain: "pawmates-71be7.firebaseapp.com",
  databaseURL: "https://pawmates-71be7.firebaseio.com",
  projectId: "pawmates-71be7",
  storageBucket: "pawmates-71be7.appspot.com",
  messagingSenderId: "942626469502",
  appId: "1:942626469502:web:586975d01c48e28e720a2f"
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);
const db = firebase.firestore();
// eslint-disable-next-line no-unused-vars
var storage = firebase.storage();
export default db;
firebase.auth().onAuthStateChanged(() => {
  if (!app) {  
    new Vue({
      created () {
        AOS.init()
      },
      router,
      template: '<App/>',
      components: { App }
    }).$mount('#app')
  }
});