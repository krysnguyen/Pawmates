<template>
  <div id="app">
    <div id="nav">
      <top-header></top-header>
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link> |
      <router-link to="/profile">Profile</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/sign-up">Sign-Up</router-link>
    </div>
    <router-view/>
  </div>
</template>

<script>
import TopHeader from '@/components/TopHeader.vue'
export default {
  name: 'App',
  components: {
    'top-header': TopHeader
  }
}
</script>

<style lang="scss">

body {
    background-color: #F0F0F0;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
/*  width: 1024px;*/
/*  margin: auto;*/
}

#nav {
/*  padding: 30px;*/
}

h1 {
    padding: 0;
    margin-top: 0;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
