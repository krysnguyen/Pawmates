<script src="https://www.gstatic.com/firebasejs/7.16.1/firebase-app.js"></script>
<template>
    <div class="container h-100 ">
        <!-- <div class="row h-100">
            <div class="col-md-9 h-100 d-flex justify-content-center align-items-center">
                <div class="col-md-8 rounded px-5 py-4 shadow bg-white text-left"> -->
                <div id = "profile">
                    <h1><b>Profile</b></h1>
                    <div  class="row">
                        <div class="form-group">
                            <img src="../assets/puppy.jpg" height="150px" width="150px"/>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label-lg">First Name</label>
                            <input type="text" v-model="profile.firstName" name="firstName"/>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label-lg">Email Address</label>
                            <input type="text" v-model="profile.email" name="userEmail"/>
                        </div>    
                        <div class="col-12 form-group">
                            <label class="col-form-label-lg">How many dog(s) do you have ?</label>
                            <input type="text" v-model="profile.number_of_dogs" name="dognumber"/>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label col-form-label-lg">What kind of dogs do you have ?</label>
                            <ejs-dropdownlist
                                id='dropdownlist1' :dataSource="dog_type_options" v-model='profile.dog_types'
                                placeholder='Select the kind of dogs' popupWidth= "450px"
                                popupHeight= '200px'>
                            </ejs-dropdownlist>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label col-form-label-lg">What kind of walks do you prefer?</label>
                            <ejs-dropdownlist id='dropdownlist2' :dataSource="walktype"
                            v-model='profile.walk_types'
                            placeholder='Select the kind of walk' popupWidth= "450px" 
                            popupHeight= "200px" sortOrder="Ascending"></ejs-dropdownlist>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label col-form-label-lg">Is there anything interesting about you that you would like to share ?</label>
                            <textarea class="form-control" rows="10" type="text" name="aboutyourself" v-model="profile.bio"></textarea>
                        </div>
                        <div class="col-12 form-group text-center">
                            <button type="button" v-on:click="createUser()">Save</button>
                        </div>
                    </div>
                </div>
                <!-- </div>
            </div>
        </div> -->
    </div>
</template>

<script>
    import Vue from 'vue';
    import {DropDownListPlugin} from '@syncfusion/ej2-vue-dropdowns';
    import axios from 'axios';
    import * as dog_data from '../dogs.json';
    import { VueEditor } from "vue2-editor";
    import VueFirestore from 'vue-firestore'
    import firebase from 'firebase/app'
    import 'firebase/auth'
    import 'firebase/firestore'
    import 'firebase/storage'
    import {fb, db} from '../main';

    Vue.use(VueFirestore);
    Vue.use(DropDownListPlugin);

    const walk_types = {
        TRAIL: 'Trail',
        NEIGHBORHOOD: 'Neighborhood'
    }

    export default {
        name: 'Profile',
        components: {
            VueEditor
        },
        data() {
            console.log("hello")
            return {
                profile:{
                    email: '',
                    imagePreview:'../assets/puppy.jpg',
                    walktype: [
                        walk_types.TRAIL,
                        walk_types.NEIGHBORHOOD
                    ],
                    dog_type_options: dog_data['dogs'],
                    firstName: '',
                    last_name: '',
                    number_of_dogs: '',
                    dog_types: [] ,
                    walk_types: [],
                    bio: ''
                }    
            }
                
        },
        firestore(){
            const user = fb.auth().currentUser;
            console.log(user.uid);
            return {
                profile: db.collection('users').doc(user.uid),
            }
            
        },

        methods: {
            createUser: function() {
                serverCreateUser(
                    this.firstName,
                    this.last_name,
                    this.email,
                    this.bio,
                    this.number_of_dogs,
                    this.dog_types,
                    this.walk_types
                )
            }
        },
        
        created(){
            var user = firebase.auth().currentUser;
            this.profile.name = user.email; 
            
        }
    }

    function serverCreateUser(
        firstName,
        lastName,
        bio,
        numberOfDogs,
        dogTypes,
        walkTypes
    ) {
        axios.post('http://localhost:8090/api/v1/users', {
                    firstName: firstName,
                    lastName: lastName,
                    bio: bio,
                    numberOfDogs: numberOfDogs,
                    dogTypes: dogTypes,
                    walkTypes: walkTypes
                })
                .then(response => console.log(response))
                .catch(error => window.alert("Error creating user" + error));
    }
</script>
<style>
@import url(https://cdn.syncfusion.com/ej2/material.css);
</style>