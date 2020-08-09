<template>
    <b-container fluid class="create-walk mb-4">
        <b-container class="second-container mt-3 mb-3 pb-3 bg-light">
            <b-card-title title-tag="h2" class="pt-3 mb-4">Create a Walk</b-card-title>
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

                <b-form-group label="Duration (in minutes, must be at least 1 minute)" label-for="input-4">
                    <b-form-input
                            id="input-4"
                            v-model="form.duration"
                            type="number"
                            required
                            placeholder="15"
                    ></b-form-input>
                </b-form-group>

                <b-form-group id="location-label" label="Location" label-for="input-5">
                    <b-form-input
                            @change="updateAddress"
                            id="input-5"
                            v-model="form.address"
                            type="text"
                            required
                            placeholder="Type an address or click on the map."
                    ></b-form-input>
                </b-form-group>
                                
                <HereMap @new-city="newCity" @new-location="newLocation" @new-coords="newCoords" lat="49.2827" lng="-123.1207" width="100%" height="500px" v-bind:newAddress="newAddress" />
                

                <b-form-group label="Description (Provide short description)" label-for="input-6" label-class="mt-3">
                    <b-form-textarea
                            id="input-6"
                            v-model="form.description"
                            placeholder="Let's hang out! My dog is really friendly."
                            required
                            rows="4"
                    ></b-form-textarea>
                </b-form-group>

                <b-button v-on:click="createWalk()" variant="primary">Create</b-button>
            </b-form>
        </b-container>
    </b-container>
</template>

<script>
    import axios from 'axios';
    import firebase from "firebase";
    import HereMap from "../components/HereMap.vue"

    export default {
        name: 'CreateWalk',
        components: {
            HereMap
        },
        data() {
            return {
                user_id: '',
                form: {
                    title: '',
                    date: '',
                    time: '',
                    duration: '15',
                    location: '', // this field holds the city name (e.g. Burnaby)
                    address: "", // this field holds the full address (e.g. 4500 Pender St., Burnaby, BC, Canada)
                    coords: "",
                    description: ''
                },
                newAddress: ""
            };
        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
            });
        },
        methods: {
            onSubmit(evt) {
                evt.preventDefault()
            },
            createWalk: function () {
                if (this.form.location == "That location could not be found.") {
                    alert("Please enter a valid location.");
                    return;
                }
                serverCreateWalk(
                    this.user_id,
                    this.form.title,
                    this.form.date,
                    this.form.time,
                    this.form.duration,
                    this.form.location,
                    this.form.description,
                    this
                );
            },
            newCity: function (cityName) {
                this.form.location = cityName;
            },
            newLocation: function (address) {
              this.form.address = address;
              document.getElementById("location-label").style.fontWeight = "bold";
              document.getElementById("input-5").style.fontWeight = "bold";
              setTimeout(() => {
                var locationLabel = document.getElementById("location-label")
                var locationInput = document.getElementById("input-5")
                locationInput.style.fontWeight = "normal";
                locationLabel.style.fontWeight = "normal";
              }, 1500);
            },
            newCoords: function (coords) {
//                console.log("parent: " + coords);
                this.form.coords = coords;
            },
            updateAddress: function (event) {
//                console.log("parent: " + event);
                if (event != "") {
                    this.newAddress = event;
                }
            }
        }
    }

    function serverCreateWalk(
        userId,
        title,
        date,
        time,
        duration,
        location,
        description,
        that
    ) {
        axios.post(`http://localhost:8090/api/v1/users/${userId}/walks`, {
            title: title,
            date: date,
            time: time,
            duration: duration,
            location: location,
            description: description
        })
            .then(res => {
                const path = `walks/${userId}/${res.data.id}`;
                that.$router.push(path)
            })
            .catch(err => window.alert("Error creating walk " + err));
    }
</script>

<style scoped>
    .create-walk {
        max-width: 600px;
    }
</style>