<template>
    <div class="walk">
        <b-container fluid="md" class="mt-4">
            <CreateWalkCard/>
            <b-card-title>Current Walks</b-card-title>
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
            />
            <b-card-title>Future Walks I joined</b-card-title>
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
            <b-card-title>Walks hosted by my matches</b-card-title>
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
        </b-container>
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
        methods: {}
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
</style>