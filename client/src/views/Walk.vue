<template>
    <div class="walk">
        <b-container fluid="md" class="mt-3">
            <CreateWalkCard class="mb-3" />
            <div class="pt-2 mb-3">
                <b-card-title class="mb-2">My Walks Happening Now</b-card-title>
                <hr>
                <WalkListing
                        v-for="walk in current_walks"
                        v-bind:key="walk.id"
                        v-bind:walk_id="walk.id"
                        v-bind:title="walk.title"
                        v-bind:date="walk.date"
                        v-bind:time="walk.time"
                        v-bind:duration="walk.duration"
                        v-bind:location="walk.location"
                        v-bind:description="walk.description"
                        v-bind:user_id="walk.user.userId"
                        v-bind:first_name="walk.user.firstName"
                        v-bind:last_name="walk.user.lastName"
                        happening="now"
                />
            </div>
            <div class="pt-2 mb-3">
                <b-card-title class="mb-2">Future Walks I've Joined</b-card-title>
                <hr>
                <WalkListing
                        v-for="walk in my_future_walks"
                        v-bind:key="walk.id"
                        v-bind:walk_id="walk.id"
                        v-bind:title="walk.title"
                        v-bind:date="walk.date"
                        v-bind:time="walk.time"
                        v-bind:duration="walk.duration"
                        v-bind:location="walk.location"
                        v-bind:description="walk.description"
                        v-bind:user_id="walk.user.userId"
                        v-bind:first_name="walk.user.firstName"
                        v-bind:last_name="walk.user.lastName"
                />
            </div>
            <div class="pt-2 mb-3">
                <b-card-title class="mb-2">Walks Hosted By My Matches</b-card-title>
                <hr>
                <WalkListing
                        v-for="walk in my_matches_walks"
                        v-bind:key="walk.id"
                        v-bind:walk_id="walk.id"
                        v-bind:title="walk.title"
                        v-bind:date="walk.date"
                        v-bind:time="walk.time"
                        v-bind:duration="walk.duration"
                        v-bind:location="walk.location"
                        v-bind:description="walk.description"
                        v-bind:user_id="walk.user.userId"
                        v-bind:first_name="walk.user.firstName"
                        v-bind:last_name="walk.user.lastName"
                />
            </div>
        </b-container>
        <b-button v-on:click="searchForWalks" class="mt-1 mb-4">Refresh Walk Listings</b-button>
    </div>
</template>

<script>
    import axios from 'axios';
    import WalkListing from '../components/WalkListing.vue'
    import CreateWalkCard from '../components/CreateWalkCard.vue'
    import firebase from "firebase";

    export default {
        name: 'Walk',
        components: {
            WalkListing,
            CreateWalkCard
        },
        data() {
            return {
                user_id: '',
                current_walks: [],
                my_future_walks: [],
                my_matches_walks: [],

            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
                serverGetWalks(this);
            });
        },
        methods: {
            searchForWalks: function () {
                serverGetWalks(this);
            }
        }
    }

    function serverGetWalks(that) {
        axios.get(`http://localhost:8090/api/v1/users/${that.user_id}/walks`)
            .then(res => {
                if (res.data.currentWalks !== null) {
                    that.current_walks = res.data.currentWalks;
                }
                if (res.data.myFutureWalks !== null) {
                    that.my_future_walks = res.data.myFutureWalks;
                }
                if (res.data.myMatchesWalks !== null) {
                    that.my_matches_walks = res.data.myMatchesWalks;
                }
            })
    }
</script>

<style scoped>
    .walk >>> .card {
        border-radius: 36px;
        max-width: 770px;
    }
    .walk hr {
        margin-top: 0;
        margin-bottom: 1em;
    }
</style>