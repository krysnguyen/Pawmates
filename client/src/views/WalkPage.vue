<template>
  <div class="walk-page">
    <b-container fluid="sm" class="p-4 mt-4 mb-4 bg-light">
      <b-row class="walk-row">
        <b-col>
          <b-card-title :title="walk.user.fname + ' ' + walk.user.lname + ' and ' + walk.user.dogname" title-tag="h2">
          </b-card-title>
        </b-col>
      </b-row>
      <b-row class="walk-row mb-3" align-h="center">
        <b-col cols="10" sm="7">
          <b-button v-on:click="joinwalk" block pill variant="primary">Join Walk!</b-button>
        </b-col>
      </b-row>
      <b-row class="walk-row">
        <b-col cols="12" md="8" class="mb-3">
          <b-img src="https://media.wired.com/photos/59269cd37034dc5f91bec0f1/master/w_314,c_limit/GoogleMapTA.jpg" fluid-grow thumbnail alt="Map image" class="map"></b-img>
        </b-col>
        <b-col class="pl-2 mb-3">
          <b-card class="details-card pt-md-4 pr-md-3 pb-md-3 pl-md-3 h-100">
            <b-card-img src="https://placedog.net/150/150?id=5" alt="Profile image" class="profile-image rounded-circle pb-3 mb-md-4">
            </b-card-img>
            <b-card-title :title="walk.title">
            </b-card-title>
            <b-card-sub-title :sub-title="walk.location" class="mb-3 mb-md-4"></b-card-sub-title>
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
//    import axios from 'axios';
//    import firebase from "firebase";

  export default {
    name: 'WalkPage',
    components: {
      
    },
    data() {
      return {
        user_id: '',
        walk: {id: 2,
           title: "Let's walk",
           date: 'August 15, 2020',
           time: '6:00 PM',
           duration: '30',
           location: 'Burnaby',
           description: 'I like golden retrievers. This is a long description for testing purposes and this sentence makes the description even longer.',
           user: {fname: 'Cooper',
             lname: 'Birks',
             dogname: 'Buddy'
           }
        }
      };
    },
    created() {

    },
    methods: {
      joinwalk: function () {
        return
      }
    }
  }
</script>

<style scoped>
  .walk-page .walk-row {
    max-width: 900px;
    margin: auto;
  }
  
  .walk-page .card,
  .walk-page .map {
    border-radius: 36px;
    box-shadow: 0px 0px 2px #888;
  }
  
  .walk-page .card-body {
    padding: 10px;
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
</style>
