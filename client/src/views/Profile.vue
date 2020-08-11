<template>
    <div class="Profile">
        <div class="wrapper">
            <div class="left">
                <img :src="[images.length > 0 ? images[0] : defaultImage]" width="100"/>
                <h4>{{this.first_name + ' ' + this.last_name}}</h4>
                <h4 v-if="this.pet_name !== null">&</h4>
                <h4>{{ this.pet_name }}</h4>
                <div class="p-1" v-for="image in this.images" v-bind:key="image">
                    <img :src="image" alt="" width="250px" height="250px">
                    <span class="delete-img" @click="deleteImage(image,images.indexOf(image))">X</span>
                </div>
            </div>
            <div class="right">
                <h1>MY PROFILE</h1>
                <div class="info">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="col-form-label-lg">First Name</label>
                            <div class="inputWithIcon">
                                <input type="text" class="form-control" v-model="first_name" name="firstName"/>
                                <i class="far fa-user fa-lg fa-lw"></i>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-form-label-lg">Last Name</label>
                            <div class="inputWithIcon">
                                <input type="text" class="form-control" v-model="last_name" name="lastName"/>
                                <i class="fas fa-user fa-lg fa-lw"></i>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-form-label-lg">Email Address</label>
                            <div class="inputWithIcon">
                                <input type="text" class="form-control" v-model="email" name="userEmail"/>
                                <i class="fas fa-envelope fa-lg fa-lw"></i>
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-form-label-lg">Phone number</label>
                            <div class="inputWithIcon">
                                <input type="text" class="form-control" v-model="phone_number" name="phoneNumber"/>
                                <i class="fas fa-sms fa-lg fa-lw"></i>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <p>Age: {{age}}</p>
                            <label class="col-form-label-lg">Birthday 'YYYY-MM-DD'</label>
                            <date-picker v-bind:date="birthday" v-model="birthday" type="date"></date-picker>
                        </div>
                    </div>
                    <h1>MY PET PROFILE</h1>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <label class="col-form-label col-form-label-lg">My pet's name is</label>
                            <div class="inputWithIcon">
                                <input type="text" class="form-control" v-model="pet_name" name="petName"/>
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
                            <ejs-dropdownlist id='dropdownlist2' :dataSource="walk_type"
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
                <div class="form-group">
                    <label class="col-form-label-lg">Photo Upload</label>
                    <input type="file" @change="onUpload" accept="image/*">
                </div>
                <div class="form-group d-flex">
                    <div class="p-1" v-for="image in images" v-bind:key="image">
                        <img :src="image" alt="" width="80px">
                        <span class="delete-img" @click="deleteImage(image,images.indexOf(image))">X</span>
                    </div>
                </div>
                <div class="col-12 form-group text-center">
                    <button type="button" v-on:click="updateUser()">Save</button>
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
    import firebase from 'firebase/app';
    import 'firebase/auth';
    import 'firebase/firestore';
    import 'firebase/storage';
    import {db, fb} from '../main';
    import DatePicker from 'vue2-datepicker';
    import 'vue2-datepicker/index.css';

    Vue.use(DropDownListPlugin);

    const walk_types = {
        TRAIL: 'Trail',
        NEIGHBORHOOD: 'Neighborhood'
    }

    export default {
        name: 'Profile',
        components: {
            DatePicker
        },
        data() {
            return {
                imagePreview: '../assets/puppy.jpg',
                walk_type: [
                    walk_types.TRAIL,
                    walk_types.NEIGHBORHOOD
                ],
                dog_type_options: dog_data['dogs'],
                first_name: '',
                last_name: '',
                email: '',
                age: '',
                phone_number: '',
                number_of_dogs: '',
                dog_types: '',
                pet_age: '',
                pet_name: '',
                walk_types: '',
                bio: '',
                user_id: '',
                images: [],
                birthday: '',
                file: null,
                defaultImage: 'https://firebasestorage.googleapis.com/v0/b/pawmates-71be7.appspot.com/o/puppy.jpg?alt=media&token=7ee37f7b-bc88-47ed-8db0-ee256beaf906'
            };

        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
                serverGetUser(this);
            });
        },
        firestore() {
            return {
                users: db.collection('users'),
            }
        },
        methods: {
            updateUser: function () {
                serverUpdateUser(this)
            },
            onUpload(event) {
                if (event.target.files[0]) {
                    this.file = event.target.files[0];
                    const storageRef = fb.storage().ref(`users/${this.user_id}/${this.file.name}`);
                    storageRef.put(this.file)
                        .then(snapshot => snapshot.ref.getDownloadURL()
                            .then(url => this.images.push(url)))
                        .catch(err => console.log("Image upload failed " + err));
                }
            },
            deleteImage(img, index) {
                let image = fb.storage().refFromURL(img);
                this.images.splice(index, 1);
                image.delete().then(function () {
                    console.log('image deleted');
                }).catch(function (error) {
                    // Uh-oh, an error occurred!
                    console.log('an error occurred' + error);
                });
            }
        }
    }

    function serverGetUser(that) {
        axios.get(`http://localhost:8090/api/v1/users/${that.user_id}`)
            .then(res => {
                that.first_name = res.data.firstName;
                that.last_name = res.data.lastName;
                that.email = res.data.email;
                that.age = res.data.age;
                that.birthday = res.data.birthday;
                that.bio = res.data.bio;
                that.pet_name = res.data.dogName;
                that.dog_types = res.data.dogTypes[0];
                that.walk_types = res.data.walkTypes[0];
                that.images = res.data.images;
            })
    }

    function serverUpdateUser(that) {
        axios.put(`http://localhost:8090/api/v1/users/${that.user_id}`, {
            firstName: that.first_name,
            lastName: that.last_name,
            birthday: that.birthday,
            bio: that.bio,
            age: that.age,
            dogName: that.pet_name,
            dogTypes: [that.dog_types],
            walkTypes: [that.walk_types],
            images: that.images
        })
            .then(() => {
                console.log("Profile updated");
                console.log(that.images)
            })
            .catch(err => alert("Failed to update " + err))
    }
</script>
<style scoped>
    @import url(https://cdn.syncfusion.com/ej2/material.css);

    .Profile {
        margin-top: 40px;
    }

    .inputWithIcon input[type=text] {
        padding-left: 40px;
    }

    .inputWithIcon {
        position: relative;
    }

    .inputWithIcon i {
        position: absolute;
        left: 5px;
        top: 5px;
        padding: 9px 8px;
        color: #aaa;
    }

    .inputWithIcon {
        position: relative;
    }

    .inputWithIcon i {
        position: absolute;
        left: 5px;
        top: 5px;
        padding: 9px 8px;
        color: #aaa;
    }

    input {
        width: 60%;
        height: 45px;
        padding-left: 15px;
    }

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        list-style: none;
    }

    body {
        background-color: #f3f3f3;
    }

    .wrapper {
        position: absolute;
        top: 700px;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 950px;
        display: flex;
        box-shadow: 0 1px 20px 0 rgba(69, 90, 100, .08);
    }

    .wrapper .left {
        width: 35%;
        background: linear-gradient(to right, #ffcce6, #99ddff);
        padding: 30px 25px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
        text-align: center;
        color: #fff;
    }

    .wrapper .left img {
        border-radius: 5px;
        margin-bottom: 10px;
    }

    .wrapper .left h4 {
        margin-bottom: 10px;
        font-weight: 900;
        font-size: 35px;
    }

    .wrapper .left p {
        font-size: 12px;
    }

    .wrapper .right {
        width: 65%;
        background: #fff;
        padding: 30px 25px;
        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
    }

    .wrapper .right .info,
    .wrapper .right .projects {
        margin-bottom: 25px;
    }

    .img-wrapp {
        position: relative;
    }

    .img-wrapp span.delete-img {
        position: absolute;
        top: -14px;
        left: -2px;
    }

    .img-wrapp span.delete-img:hover {
        cursor: pointer;
    }
</style>