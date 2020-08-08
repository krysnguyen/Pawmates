<template>
<div class="Profile">
    <div class="wrapper">
    <div class="left">
        <img src="../assets/puppy.jpg" width="100"/>
        <h4>Alex William</h4>
        <h4>&</h4>
        <h4>Rocky</h4>
        <p>UI Developer</p>
        <p>alex@gmail.com</p>
    </div>
    <div class="right">
        <h1>MY PROFILE</h1>
        <div class="info">
            <div class="row">
                <div class="col-md-6">
                    <label class="col-form-label-lg">First Name</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="first_name" name="firstName"/>
                        <i class="far fa-user fa-lg fa-lw"></i>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label class="col-form-label-lg">Last Name</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="last_name" name="lastName"/>
                        <i class="fas fa-user fa-lg fa-lw"></i>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label class="col-form-label-lg">Email Address</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="email" name="userEmail"/>
                        <i class="fas fa-envelope fa-lg fa-lw"></i>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label class="col-form-label-lg">Phone number</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="phoneNumber" name="phoneNumber"/>
                        <i class="fas fa-sms fa-lg fa-lw"></i>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label class="col-form-label col-form-label-lg">My age </label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="age" name="age"/>
                        <i class="far fa-calendar-times fa-lg fa-lw"></i>
                    </div>
                </div>
            </div>
            <h1>MY PET PROFILE</h1>
            <div class="row">
                <div class="form-group col-md-6">
                    <label class="col-form-label col-form-label-lg">My pet's name is</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="pet_name" name="petName"/>
                        <i class="fas fa-dog fa-lg fa-lw"></i>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label class="col-form-label col-form-label-lg">My pet's age is</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="pet_age" name="petAge"/>
                        <i class="fas fa-dog fa-lg fa-lw"></i>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label class="col-form-label col-form-label-lg">My pet type is a</label>
                    <ejs-dropdownlist
                            id='dropdownlist1' :dataSource="dog_type_options" v-model='dog_types'
                            placeholder='Select the kind of dogs' popupWidth="450px"
                            popupHeight='200px'>
                    </ejs-dropdownlist>
                </div>
                <div class="col-md-6 form-group">
                    <label class="col-form-label col-form-label-lg">The kind of walks I
                        prefer?</label>
                    <ejs-dropdownlist id='dropdownlist2' :dataSource="walktype"
                                        v-model='walk_types'
                                        placeholder='Select the kind of walk' popupWidth="450px"
                                        popupHeight="200px" sortOrder="Ascending"></ejs-dropdownlist>
                </div>
            </div>    
        </div>
        
        <div class="col-12 form-group">
            <label class="col-form-label col-form-label-lg">Something interesting about me and my pet
                that I would like to share</label>
            <textarea class="form-control" rows="5" type="text" name="aboutyourself"
                        v-model="bio"></textarea>
        </div>
        <div class="form-group col-12">
            <label class="col-form-label-lg">Photo Upload</label>
            <input type="file" @change="onFileSelected">
        </div>
        <div class="col-12 form-group text-center">
            <button type="button" v-on:click="createUser()">Save</button>
        </div>
      </div>
    </div>
</div>
</template>

<script>
    // import UserCollage from '../components/UserCollage.vue'
    import Vue from 'vue';
    import {DropDownListPlugin} from '@syncfusion/ej2-vue-dropdowns';
    import axios from 'axios';
    import * as dog_data from '../dogs.json';
    import firebase from "firebase";
     import {fb} from '../main';

    Vue.use(DropDownListPlugin);

    const walk_types = {
        TRAIL: 'Trail',
        NEIGHBORHOOD: 'Neighborhood'
    }

    export default {
        name: 'Profile',
        // components: {
        //     UserCollage
        // },
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
            },
            onFileSelected(event){
                let file = event.target.files[0];
                var storageRef = fb.storage().ref('users/'+file.name);
                storageRef.put(file);
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
    /* .vue-bg {
        background: #bce5d0;
        position: absolute;
        top:50%;
        left:50%
    } */
    .Profile{
         margin-top: 10px;
    }
    @import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');
    .inputWithIcon input[type=text]{
        padding-left: 40px;
    }

    .inputWithIcon{
        position: relative;
    }

    .inputWithIcon i{
        position:absolute;
        left:5px;
        top:5px;
        padding:9px 8px;
        color: #aaa;
    }
    input {
        width: 60%;
        height: 45px;
        padding-left: 15px;
    }

    *{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    list-style: none;
    /* font-family: 'Josefin Sans', sans-serif; */
    }

    body{
    background-color: #f3f3f3;
    }

    .wrapper{
    position: absolute;
    top: 90%;
    left: 50%;
    transform: translate(-50%,-50%);
    width: 950px;
    display: flex;
    box-shadow: 0 1px 20px 0 rgba(69,90,100,.08);
    }

    .wrapper .left{
    width: 35%;
    background: linear-gradient(to right,#01a9ac,#01dbdf);
    padding: 30px 25px;
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
    text-align: center;
    color: #fff;
    }

    .wrapper .left img{
    border-radius: 5px;
    margin-bottom: 10px;
    }

    .wrapper .left h4{
    margin-bottom: 10px;
    }

    .wrapper .left p{
    font-size: 12px;
    }

    .wrapper .right{
    width: 65%;
    background: #fff;
    padding: 30px 25px;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    }

    .wrapper .right .info,
    .wrapper .right .projects{
    margin-bottom: 25px;
    }

    .wrapper .right .info h3,
    .wrapper .right .projects h3{
        margin-bottom: 15px;
        padding-bottom: 5px;
        border-bottom: 1px solid #e0e0e0;
        color: #353c4e;
    text-transform: uppercase;
    letter-spacing: 5px;
    }

    .wrapper .right .info_data,
    .wrapper .right .projects_data{
    display: flex;
    justify-content: space-between;
    }

    .wrapper .right .info_data .data,
    .wrapper .right .projects_data .data{
    width: 45%;
    }

    .wrapper .right .info_data .data h4,
    .wrapper .right .projects_data .data h4{
        color: #353c4e;
        margin-bottom: 5px;
    }

    .wrapper .right .info_data .data p,
    .wrapper .right .projects_data .data p{
    font-size: 13px;
    margin-bottom: 10px;
    color: #919aa3;
    }

    .wrapper .social_media ul{
    display: flex;
    }

    .wrapper .social_media ul li{
    width: 45px;
    height: 45px;
    background: linear-gradient(to right,#01a9ac,#01dbdf);
    margin-right: 10px;
    border-radius: 5px;
    text-align: center;
    line-height: 45px;
    }

    .wrapper .social_media ul li a{
    color :#fff;
    display: block;
    font-size: 18px;
    }
</style>