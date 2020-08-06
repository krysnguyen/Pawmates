<template>
    <div>
        <b-button v-if="this.loading === true" variant="primary" disabled>
            <b-spinner small type="grow"></b-spinner>
            Searching for your next pawmate...
        </b-button>

        <b-card
                img-src="https://picsum.photos/600/300/?image=25"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 40rem;"
                class="mb-2"
                v-else-if="this.loading === false"
        >
            <b-card-title>
                {{this.potential_match.firstName + ' ' + this.potential_match.lastName}}
            </b-card-title>

            <b-card-text>
                Has {{this.potential_match.numberOfDogs}} dogs and looking for people to walk their dogs with.
            </b-card-text>
            <b-button v-on:click="viewProfile()">View Profile</b-button>
            <b-button v-on:click="like()">Like</b-button>
            <b-button v-on:click="dislike()">Dislike</b-button>
        </b-card>
        <b-button v-if="this.loading === true" v-on:click="loadMatches()">Search for potential matches</b-button>
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
                potential_match: {},
                user_id: '',
                loading: true
            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
                serverGetPotentialMatches(this);

            });

        },
        methods: {
            viewProfile: function () {
                const path = 'user/' + this.potential_matches[0].userId;
                this.$router.push(path)
            },
            loadMatches: function () {
              serverGetPotentialMatches(this);
            },
            updateNextMatch: function () {
                if (this.potential_matches.length > 1) {
                    this.potential_matches.shift();
                    this.potential_match = this.potential_matches[0];
                } else {
                    this.loading = true;
                }

            },
            like: function () {
                serverLikeUser(this.user_id, this.potential_matches[0].userId);
                this.updateNextMatch();
            },

            dislike: function () {
                serverDislikeUser(this.user_id, this.potential_matches[0].userId)
                this.updateNextMatch();
            }
        }
    }

    function serverGetPotentialMatches(that) {
        axios.get(`http://localhost:8090/api/v1/users/${that.user_id}/potential`)
            .then(response => {
                if (response.data.length > 0) {
                    that.potential_matches = response.data;
                    that.potential_match = that.potential_matches[0];
                    that.loading = false;
                }
            })
            .catch(err => console.log(err))
    }

    function serverLikeUser(userId, likedUserId) {
        const path = `http://localhost:8090/api/v1/users/${userId}/likes`;
        axios.put(path, {
            id: likedUserId
        })
    }

    function serverDislikeUser(userId, dislikedUserId) {
        const path = `http://localhost:8090/api/v1/users/${userId}/dislikes`;
        axios.put(path, {
            id: dislikedUserId
        })
    }
</script>

<style>
    div {
        align-content: center;
    }
</style>