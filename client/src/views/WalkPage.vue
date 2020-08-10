<template>
    <div class="walk-page">
        <b-container fluid="sm" class="p-4 mt-4 mb-4 bg-light">
            <b-row class="walk-row">
                <b-col>
                    <b-card-title title-tag="h2">
                        {{walk.user.firstName + ' ' + walk.user.lastName}}
                    </b-card-title>
                </b-col>
            </b-row>
            <b-row class="walk-row mb-3" align-h="center">
                <b-col cols="10" sm="7">
                    <b-button v-if="user_id !== walk.user.userId" v-on:click="joinWalk()" block pill variant="primary">
                        Join Walk!
                    </b-button>
                </b-col>
            </b-row>
            <b-row class="walk-row">
                <b-col cols="12" md="8" class="mb-3">
                
                    <b-card class="map-card mb-0" style="height: 100%">
                        <HereMap v-if="dataReady" :lat="lat" :lng="lng" width="100%" height="550px" />
                    </b-card>
                
                </b-col>
                <b-col class="pl-2 mb-3">
                    <b-card class="details-card pt-md-4 pr-md-3 pb-md-3 pl-md-3 h-100">
                        <b-card-img :src="[walk.user.images.length > 0 ? walk.user.images[0] : defaultImage]" alt="Profile image"
                                    class="profile-image rounded-circle pb-3 mb-md-4">
                        </b-card-img>
                        <b-card-title :title="walk.title">
                        </b-card-title>
                        <b-card-sub-title :sub-title="walk.address" class="mb-3 mb-md-4"></b-card-sub-title>
                        <div class="details">
                            {{ walk.date }} <br/>
                            {{ walk.time }} <br/>
                            {{ walk.duration }} minutes
                        </div>
                    </b-card>
                </b-col>
            </b-row>
            <b-row class="walk-row">
                <b-col>
                    <b-card>
                        {{ walk.description }}
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    import axios from 'axios';
    import firebase from "firebase";
    import HereMap from "../components/HereMap.vue"

    export default {
        name: 'WalkPage',
        components: {
          HereMap
        },
        data() {
            return {
                user_id: '',
                walk: {},
                lat: '',
                lng: '',
                dataReady: false,
                defaultImage: 'https://firebasestorage.googleapis.com/v0/b/pawmates-71be7.appspot.com/o/puppy.jpg?alt=media&token=7ee37f7b-bc88-47ed-8db0-ee256beaf906'
            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
            });
            serverGetWalk(this.$route.params.userId, this.$route.params.walkId, this);
        },
        methods: {
            joinWalk: function () {
                serverJoinWalk(this.user_id, this.walk.id, this.walk.user.userId, this.$router);
            }
        }
    }

    function serverGetWalk(userId, walkId, that) {
        axios.get(`http://localhost:8090/api/v1/users/${userId}/walks/${walkId}`)
            .then(res => {
                that.walk = res.data;
                const latlng = that.walk.coords.split(",");
                that.lat = latlng[0];
                that.lng = latlng[1];
                that.dataReady = true;
            })
            .catch(err => alert(`Can't get walk from the database: ${err}`));
    }

    function serverJoinWalk(userId, walkId, walkUserId, router) {
        axios.put(`http://localhost:8090/api/v1/users/${walkUserId}/walks/${walkId}/join`, {
            id: userId
        })
            .then(() => router.push('/walk'))
            .catch(err => alert("Could not join walk " + err))
    }
</script>

<style scoped>
    .walk-page .walk-row {
        max-width: 900px;
        margin: auto;
    }

    .walk-page .details {
        line-height: 2;
    }

    .walk-page .profile-image {
        display: block;
        margin-left: auto;
        margin-right: auto;
        width: 75%;
        max-width: 150px;
    }

    .walk-page .details-card {
        font-size: 1.1em;
    }
    
    .walk-page .map-card .card-body {
        padding: 0;
    }
    
    .walk-page .card {
        box-shadow: 0px 0px 15px #bbb;
    }
</style>
