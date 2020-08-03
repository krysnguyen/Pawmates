<template>
    <div>
        <b-card
                img-src="https://picsum.photos/600/300/?image=25"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem;"
                class="mb-2"
        >
            <b-card-title>
                {{this.potential_matches[0].firstName + ' ' + this.potential_matches[0].lastName}}
            </b-card-title>

            <b-card-text>
                Has {{this.potential_matches[0].numberOfDogs}} dogs and looking for people to walk their dogs with.
            </b-card-text>
            <b-button v-on:click="viewProfile()">View Profile</b-button>
            <b-button v-on:click="like()">Like</b-button>
            <b-button v-on:click="dislike()">Dislike</b-button>
        </b-card>
    </div>
</template>

<script>
    import axios from 'axios';
    import firebase from "firebase";

    export default {
        name: 'Match',
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
            serverGetPotentialMatches(this);
        },
        methods: {
            viewProfile: function () {
                const path = 'user/' + this.potential_matches[0].userId;
                console.log(path);
                this.$router.push(path)
            },
            like: function () {
                serverLikeUser(this.user_id, this.potential_matches[0].userId);
            },

            dislike: function () {
                serverDislikeUser(this.user_id, this.potential_matches[0].userId)
            }
        }
    }

    function serverGetPotentialMatches(that) {
        axios.get('http://localhost:8090/api/v1/users')
            .then(response => {
                that.potential_matches = response.data;
            })
            .catch(err => console.log(err))
    }

    function serverLikeUser(userId, likedUserId) {
        const path = 'http://localhost:8090/api/v1/users/' + userId + '/likes';
        axios.post(path, {
            id: likedUserId
        })
    }

    function serverDislikeUser(userId, dislikedUserId) {
        const path = 'http://localhost:8090/api/v1/users/' + userId + '/dislikes';
        axios.post(path, {
            id: dislikedUserId
        })
    }
</script>

<style>
    div {
        align-content: center;
    }
</style>