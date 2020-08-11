<template>
    <div class="myMatches">
        <h1>Welcome to the My Matches page</h1>

        <b-list-group v-for="match in this.matches" :key="match.userId">
            <b-list-group-item>
                <b-card header-border-variant="dark" :header="match.firstName + ' ' + match.lastName" align="center"
                        style="max-width: 770px; border-radius: 36px;">
                    <b-row class="row-in-card" no-gutters>
                        <b-col cols="2" align-self="center">
                            <b-card-img :src="match.images.length > 0 ? match.images[0] : defaultImage"
                                        alt="Profile image"
                                        class="rounded-circle" style="max-height: 200px; max-width: 200px"></b-card-img>
                        </b-col>
                        <b-col cols="7">
                            <b-card-body>
                                <b-card-text>
                                    {{match.bio}}
                                </b-card-text>
                            </b-card-body>
                        </b-col>
                    </b-row>
                    <router-link :to="{ name: 'User', params: {id: match.userId}}">
                        <b-button variant="primary" style="font-weight: bold; text-align: center;">
                            View Profile
                        </b-button>
                    </router-link>
                </b-card>
            </b-list-group-item>
        </b-list-group>
        <b-button v-on:click="loadMatches()">Search for new matches</b-button>
    </div>
</template>

<script>
    import firebase from 'firebase/app';
    import 'firebase/auth';
    import axios from "axios";

    export default {
        name: 'MyMatches',
        data() {
            return {
                user_id: null,
                matches: [],
                loading: true,
                defaultImage: 'https://firebasestorage.googleapis.com/v0/b/pawmates-71be7.appspot.com/o/puppy.jpg?alt=media&token=7ee37f7b-bc88-47ed-8db0-ee256beaf906'
            }
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
                // setTimeout(serverGetPotentialMatches, 3000, this);
                serverGetMyMatches(this);

            });
        },
        methods: {
            viewProfile: function (userId) {
                this.$router.push(`user/${userId}`)
            },
            loadMatches: function () {
                this.loading = true;
                serverGetMyMatches(this);
            }
        }

    }

    function serverGetMyMatches(that) {
        axios.get(`http://localhost:8090/api/v1/matches/${that.user_id}`)
            .then(response => {
                that.matches = response.data;
                that.loading = false;

            })
            .catch(err => console.log(err))
    }

</script>

<style>
    .card-header {
        font-size: 30px;
        font-weight: bold;
    }

</style>
