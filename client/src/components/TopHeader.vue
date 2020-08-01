<template>
    <div>
        <b-navbar toggleable="md" type="dark" variant="info">
            <b-navbar-brand href="/">Pawmates</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item to="/">
                        <router-link to="/">Home</router-link>
                    </b-nav-item>
                    <b-nav-item to="/about">
                        <router-link to="/about">About</router-link>
                    </b-nav-item>
                    
                    <b-nav-item to="/match">
                        <router-link to="/match">Match</router-link>
                    </b-nav-item>
                    <b-nav-item to="/login">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                    <b-nav-item to="/sign-up">
                        <router-link to="/sign-up">Sign-Up</router-link>
                    </b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-item-dropdown right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            <em>User</em>
                        </template>
                        <b-dropdown-item to="/profile">Profile</b-dropdown-item>
                        <b-dropdown-item @click="logout">Sign Out</b-dropdown-item>
                    </b-nav-item-dropdown>
                </b-navbar-nav>
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
  import firebase from 'firebase/app';
  import 'firebase/auth';

  export default {
      name: 'Secure',
      data() {
          return {};
      },
      methods: {
          logout: function() {
              firebase.auth().signOut().then(() => {
                  this.$router.replace('login')
              })
          }
      }
  }
</script>

<style lang="scss" scoped>
    #nav a.router-link-exact-active {
        color: white;
    }
</style>
