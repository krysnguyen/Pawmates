<template>
    <div class="myMatches">
        <h1>Welcome to the My Matches page</h1>

        <b-list-group v-for="match in this.matches" :key="match.userId">
            <b-list-group-item>
                <b-card no-body class="overflow-hidden" bg-variant="dark" text-variant="white">
                    <b-row no-gutters>
                        <b-col md="2">
                            <b-card-img src="https://picsum.photos/400/400/?image=5" alt="Image"
                                        class="rounded-0"></b-card-img>
                        </b-col>
                        <b-col md="7">
                            <b-card-body>
                                <b-card-title>
                                    {{match.firstName + ' ' + match.lastName}}
                                </b-card-title>
                                <b-card-text>
                                    {{match.bio}}
                                </b-card-text>

                                <router-link :to="{ name: 'User', params: {id: match.userId}}">
                                    <b-button variant="primary">
                                        View Profile
                                    </b-button>
                                </router-link>
                            </b-card-body>
                        </b-col>
                    </b-row>
                </b-card>
            </b-list-group-item>
        </b-list-group>
        <b-button v-on:click="loadMatches()">Search for new matches</b-button>
    </div>
</template>

<script>
    import firebase from "firebase";
    import axios from "axios";

    export default {
        name: 'MyMatches',
        data() {
            return {
                user_id: null,
                matches: [],
                loading: true
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
                console.log(that.matches);
                that.loading = false;

            })
            .catch(err => console.log(err))
    }

</script>
