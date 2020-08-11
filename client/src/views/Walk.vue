<template>
    <div class="walk">
        <b-container fluid="md" class="mt-3">
            <CreateWalkCard class="mb-3" v-if="imageLoading == false"
                :image="images.length > 0 ? images[0] : defaultImage"
            />
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
                        v-bind:image="walk.user.images.length > 0 ? walk.user.images[0] : defaultImage"
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
                        v-bind:image="walk.user.images.length > 0 ? walk.user.images[0] : defaultImage"
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
                        v-bind:image="walk.user.images.length > 0 ? walk.user.images[0] : defaultImage"
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
    import firebase from 'firebase/app';
    import 'firebase/auth';

    export default {
        name: 'Walk',
        components: {
            WalkListing,
            CreateWalkCard
        },
        data() {
            return {
                user_id: null,
                current_walks: [],
                my_future_walks: [],
                my_matches_walks: [],
                images: [],
                defaultImage: 'https://firebasestorage.googleapis.com/v0/b/pawmates-71be7.appspot.com/o/puppy.jpg?alt=media&token=7ee37f7b-bc88-47ed-8db0-ee256beaf906',
                imageLoading: true

            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                if (user) {
                    this.user_id = user.uid;
                    serverGetUser(this.user_id, this)
                    serverGetWalks(this);
                }
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

    function serverGetUser(userId, that) {
        console.log(userId);
        axios.get('http://localhost:8090/api/v1/users/' + userId)
            .then(res => {
                that.images = res.data.images;
                that.imageLoading = false;
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