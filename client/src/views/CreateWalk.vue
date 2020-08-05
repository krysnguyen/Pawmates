<template>
  <b-container fluid class="create-walk">
    <b-container class="mt-4">
      <b-form @submit="onSubmit">
        <b-form-group
          label="Walk Title"
          label-for="input-1"
        >
          <b-form-input
            id="input-1"
            v-model="form.title"
            type="text"
            required
            placeholder="Afternoon Walk"
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Date" label-for="input-2">
          <b-form-input
            id="input-2"
            v-model="form.date"
            type="date"
            required
          ></b-form-input>
        </b-form-group>
        
        <b-form-group label="Time" label-for="input-3">
          <b-form-input
            id="input-3"
            v-model="form.time"
            type="time"
            required
          ></b-form-input>
        </b-form-group>
        
        <b-form-group label="Duration (min)" label-for="input-4">
          <b-form-input
            id="input-4"
            v-model="form.duration"
            type="number"
            required
            placeholder="15"
          ></b-form-input>
        </b-form-group>
        
        <b-form-group label="Location" label-for="input-5">
          <b-form-input
            id="input-5"
            v-model="form.location"
            type="text"
            required
            placeholder="to do. maybe use here API."
          ></b-form-input>
        </b-form-group>
        
        <b-form-group label="Description (optional)" label-for="input-6">
          <b-form-textarea
            id="input-6"
            v-model="form.description"
            placeholder="Let's hang out! My dog is really friendly."
            rows="4"
          ></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="primary">Create</b-button>
      </b-form>
    </b-container>
  </b-container>
</template>

<script>
//    import axios from 'axios';
//    import firebase from "firebase";

  export default {
    name: 'CreateWalk',
    components: {
      
    },
    data() {
      return {
        user_id: '',
        form: {
          title: '',
          date: '',
          time: '',
          duration: '',
          location: '',
          description: ''
        }
      };
    },
    created() {

    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        alert(JSON.stringify(this.form))
      }
    }
  }
</script>

<style scoped>
  .create-walk {
    max-width: 550px;
  }
</style>