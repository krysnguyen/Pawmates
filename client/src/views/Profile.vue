<template>
    <div class="container h-100 ">
        <div id="profile">
            <h1><b>Profile</b></h1>
            <div class="row">
                <div class="form-group">
                    <img src="../assets/puppy.jpg" height="150px" width="150px"/>
                </div>
                <div class="col-12 form-group">
                    <label class="col-form-label-lg">First Name</label>
                    <input type="text" v-model="first_name" name="firstName"/>
                </div>
                <div class="col-12 form-group">
                    <label class="col-form-label-lg">Email Address</label>
                    <input type="text" v-model="email" name="userEmail"/>
                </div>
                <div class="col-12 form-group">
                    <label class="col-form-label-lg">How many dog(s) do you have ?</label>
                    <input type="text" v-model="number_of_dogs" name="dognumber"/>
                </div>
                <div class="col-12 form-group">
                    <label class="col-form-label col-form-label-lg">What kind of dogs do you have ?</label>
                    <ejs-dropdownlist
                            id='dropdownlist1' :dataSource="dog_type_options" v-model='dog_types'
                            placeholder='Select the kind of dogs' popupWidth="450px"
                            popupHeight='200px'>
                    </ejs-dropdownlist>
                </div>
                <div class="col-12 form-group">
                    <label class="col-form-label col-form-label-lg">What kind of walks do you
                        prefer?</label>
                    <ejs-dropdownlist id='dropdownlist2' :dataSource="walktype"
                                      v-model='walk_types'
                                      placeholder='Select the kind of walk' popupWidth="450px"
                                      popupHeight="200px" sortOrder="Ascending"></ejs-dropdownlist>
                </div>
                <div class="col-12 form-group">
                    <label class="col-form-label col-form-label-lg">Is there anything interesting about you
                        that you would like to share ?</label>
                    <textarea class="form-control" rows="10" type="text" name="aboutyourself"
                              v-model="bio"></textarea>
                </div>
                <div class="col-12 form-group text-center">
                    <button type="button" v-on:click="createUser()">Save</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Vue from 'vue';
    import {DropDownListPlugin} from '@syncfusion/ej2-vue-dropdowns';
    import axios from 'axios';
    import * as dog_data from '../dogs.json';
    import firebase from "firebase";

    Vue.use(DropDownListPlugin);

    const walk_types = {
        TRAIL: 'Trail',
        NEIGHBORHOOD: 'Neighborhood'
    }

    export default {
        name: 'Profile',
        data() {
            return {
                imagePreview: '../assets/puppy.jpg',
                walktype: [
                    walk_types.TRAIL,
                    walk_types.NEIGHBORHOOD
                ],
                dog_type_options: dog_data['dogs'],
                first_name: 'Chad',
                last_name: 'Malla',
                email: '',
                number_of_dogs: '',
                dog_types: [],
                walk_types: [],
                bio: '',
                user_id: ''
            };

        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
                console.log(this.user_id);
            });
        },
        methods: {
            createUser: function () {
                serverCreateUser(
                    this.first_name,
                    this.last_name,
                    this.bio,
                    this.number_of_dogs,
                    this.dog_types,
                    this.walk_types,
                    this.user_id
                )
            }
        }
    }

    function serverCreateUser(
        firstName,
        lastName,
        bio,
        numberOfDogs,
        dogTypes,
        walkTypes,
        userId
    ) {
        axios.post('http://localhost:8090/api/v1/users', {
            firstName: firstName,
            lastName: lastName,
            bio: bio,
            numberOfDogs: numberOfDogs,
            dogTypes: dogTypes,
            walkTypes: walkTypes,
            userId: userId
        })
            .then(response => console.log(response))
            .catch(error => window.alert("Error creating user" + error));
    }
</script>
<style>
    @import url(https://cdn.syncfusion.com/ej2/material.css);
</style>