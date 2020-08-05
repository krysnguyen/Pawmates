<template>
  <div class="walk">
    <b-container fluid="md" class="mt-4">
      <CreateWalkCard/>
      <WalkListing 
        v-for="walk in walks"
        v-bind:key="walk.id"
        v-bind:title="walk.title"
        v-bind:date="walk.date"
        v-bind:time="walk.time"
        v-bind:duration="walk.duration"
        v-bind:location="walk.location"
        v-bind:description="walk.description"
      />
    </b-container>
  </div>
</template>

<script>
//    import axios from 'axios';
//    import firebase from "firebase";
  import WalkListing from '../components/WalkListing.vue'
  import CreateWalkCard from '../components/CreateWalkCard.vue'

  export default {
    name: 'Walk',
    components: {
      WalkListing,
      CreateWalkCard
    },
    data() {
      return {
        user_id: '',
        walks: [
          {id: 1,
           title: "Let's walk",
           date: 'August 15, 2020',
           time: '6:00 PM',
           duration: '30',
           location: 'Burnaby',
           description: 'I like golden retrievers.'},
          {id: 2,
           title: "Let's walk",
           date: 'August 15, 2020',
           time: '6:00 PM',
           duration: '30',
           location: 'Burnaby',
           description: 'I like golden retrievers. This is a long description for testing purposes and this sentence makes the description even longer. Wow!'},
          {id: 3,
           title: "Let's walk",
           date: 'August 15, 2020',
           time: '6:00 PM',
           duration: '30',
           location: 'Burnaby',
           description: 'I like golden retrievers.'}
        ]
      };
    },
    created() {

    },
    methods: {

    }
  }
</script>

<style scoped>
  .walk >>> .card {
    border-radius: 36px;
  }
</style>