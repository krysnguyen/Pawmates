<template>
    <div>
        <b-navbar toggleable="md" type="dark" variant="info">
            <b-navbar-brand href="/">Pawmates</b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item v-if="this.user == null" to="/">
                        <router-link to="/">Home</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="this.user !== null" to="/match">
                        <router-link to="/match">Sniff</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="this.user == null" to="/login">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="this.user == null" to="/sign-up">
                        <router-link to="/sign-up">Sign-Up</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="this.user !== null" to="/mymatches">
                        <router-link to="/mymatches">My-Matches</router-link>
                    </b-nav-item>
                </b-navbar-nav>

                <!-- Right aligned nav items -->
                <b-navbar-nav class="ml-auto">
                    <b-nav-item-dropdown v-if="this.user !== null" right>
                        <!-- Using 'button-content' slot -->
                        <template v-slot:button-content>
                            {{first_name}}
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
    import axios from "axios";

    export default {
        name: 'TopHeader',
        data() {
            return {
                user: null,
                first_name: '',
                last_name: ''
            };
        },
        methods: {
            logout: function () {
                firebase.auth().signOut().then(() => {
                    this.$router.replace('login')
                })
            }
        },
        created: function () {
            firebase.auth().onAuthStateChanged(user => {
                this.user = user ? user : null;
                serverGetUser(this.user.uid, this);
            });
        }
    }

    function serverGetUser(userId, that) {
        axios.get('http://localhost:8090/api/v1/users/' + userId)
            .then(res => {
                that.first_name = res.data.firstName;
                that.last_name = res.data.lastName;
            })
    }
</script>

<style lang="scss" scoped>
    #nav a.router-link-exact-active {
        color: white;
    }
</style>
