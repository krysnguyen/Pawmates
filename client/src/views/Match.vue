<template>
    <div>
        <b-button v-if="this.loading === true" variant="primary" disabled>
            <b-spinner small type="grow"></b-spinner>
            Searching for your next pawmate...
        </b-button>

        <b-card
                :img-src="potential_match.images.length > 0 ? potential_match.images[0] : defaultImage"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 40rem;"
                class="text-center"
                v-else-if="this.loading === false"
        >
            <b-card-title>
                {{this.potential_match.firstName + ' ' + this.potential_match.lastName}}
            </b-card-title>

            <b-card-text style="color:rgb(112, 112, 112);">
                {{this.potential_match.bio}}
            </b-card-text>
            <b-button variant="primary" v-on:click="viewProfile()">View Profile</b-button><br><br>
            <b-button v-on:click="like()">Like</b-button>
            <b-button v-on:click="dislike()">Dislike</b-button>
        </b-card>
        <b-button v-if="this.loading === true" v-on:click="loadMatches()">Search for potential matches</b-button>
    </div>
</template>

<script>
    import axios from 'axios';
    import firebase from 'firebase/app';
    import 'firebase/auth';

    export default {
        name: 'Match',
        data() {
            return {
                potential_matches: [],
                potential_match: {},
                user_id: '',
                loading: true,
                defaultImage: 'https://firebasestorage.googleapis.com/v0/b/pawmates-71be7.appspot.com/o/puppy.jpg?alt=media&token=7ee37f7b-bc88-47ed-8db0-ee256beaf906'
            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
                if (this.user_id !== null) {
                    serverGetPotentialMatches(this);
                }
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
                serverLikeUser(this.user_id, this.potential_matches[0].userId)
                .then(response => {

                    if ((response.data.matches.indexOf(this.potential_matches[0].userId))!= -1){
                        alert("It's a match! Go to My PawMates to view matched profiles or Messaging to chat.");
                    }
                    this.updateNextMatch();
                })
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
        return axios.put(path, {
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

<style scoped>
    div {
        align-content: center;
    }
</style>