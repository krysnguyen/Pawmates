<template>
    <b-container fluid="xl">
        <b-row align-v="stretch">
            <b-col>
                <Sidebar/>
            </b-col>
            <b-col cols="6" md="4">
                <b-button v-on:click="like()">Like</b-button>
                <b-button v-on:click="dislike()">Dislike</b-button>
            </b-col>
            <b-col cols="10" md="5">
                <MainArea/>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import Sidebar from "../components/Sidebar";
    import MainArea from "../components/MainArea";
    import axios from 'axios';
    import firebase from "firebase";

    export default {
        name: 'Match',
        components: {MainArea, Sidebar},
        data() {
            return {
                potential_matches: [],
                user_id: ''
            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
            });
            serverGetPotentialMatches();
        },
        methods: {
            like: function () {
                serverLikeUser(this.user_id);
            },

            dislike: function () {
                serverDislikeUser(this.user_id)
            }
        }
    }

    function serverGetPotentialMatches() {
        axios.get('http://localhost:8090/api/v1/users/potential')
            .then(response => this.potential_matches = response)
            .catch(err => console.log(err))
    }

    function serverLikeUser(likedUserId) {
        axios.post('http://localhost:8090/api/v1/users/{userId}/likes', {
            likedUserId: likedUserId
        })
    }

    function serverDislikeUser(dislikedUserId) {
        axios.post("http://localhost:8090/api/v1/users/{userId}/dislikes", {
            dislikedUserId: dislikedUserId
        })
    }
</script>

<style>
</style>