import Vue from 'vue'
import firebase from 'firebase'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false


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



new Vue({
  router,
  template: '<App/>',
  components: { App }
}).$mount('#app')

