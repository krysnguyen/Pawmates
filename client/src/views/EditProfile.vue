<template>
    <div class="EditProfile">
        <h1>CREATE ACCOUNT</h1>
        <div class="container-sm h-100 text-left ">
            <form>
                <div class="row">
                    <div class="col-md-6">
                        <label class="col-form-label-lg">First Name</label>
                        <div class="inputWithIcon">
                            <input type="text" class="form-control" v-model="first_name" name="firstName"/>
                            <i class="far fa-user fa-lg fa-lw"></i>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label class="col-form-label-lg">Gender</label>
                        <b-container>
                            <button type="button" class="btn btn-outline-secondary">Man</button>
                            <button type="button" class="btn btn-outline-secondary">Woman</button>
                            <button type="button" class="btn btn-outline-secondary">Other</button>
                        </b-container>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-form-label-lg">Last Name</label>
                        <div class="inputWithIcon">
                            <input type="text" class="form-control" v-model="last_name" name="lastName"/>
                            <i class="fas fa-user fa-lg fa-lw"></i>
                        </div>
                    </div>
                    <div class="form-group col-md-4">
                        <label class="col-form-label-lg">Birthday 'YYYY-MM-DD'</label>
                        <date-picker v-model="birthday" type="date"></date-picker>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label class="col-form-label-lg">Bio</label>
                        <div class="inputWithIcon">
                            <b-form-textarea
                                    id="textarea"
                                    v-model="bio"
                                    placeholder="Enter something..."
                                    rows="3"
                                    max-rows="6"
                            ></b-form-textarea>

                            <pre class="mt-3 mb-0">{{ bio }}</pre>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-form-label-lg">Pet's Name</label>
                        <div class="inputWithIcon">
                            <input type="text" class="form-control" v-model="pet_name" name="petName"/>
                            <i class="fas fa-dog fa-lg fa-lw"></i>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label class="col-form-label-lg">Pet's Breed</label>
                        <ejs-dropdownlist
                                id='dropdownlist1' :dataSource="dog_type_options" v-model='dog_types'
                                placeholder='Select the kind of dogs' popupWidth="450px"
                                popupHeight='500px'>
                        </ejs-dropdownlist>
                    </div>
                </div>
            </form>
        </div>
        <button type="button" class="btn btn-secondary" @click="signUp">Sign Up</button>
        <p><br> Already have an account? You can
            <b-link to="/login">login</b-link>
            here
        </p>
    </div>
</template>

<script>
    import Vue from 'vue';
    import DatePicker from 'vue2-datepicker';
    import {DropDownListPlugin} from '@syncfusion/ej2-vue-dropdowns';
    import * as dog_data from '../dogs.json';
    import firebase from "firebase";
    import axios from "axios";

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
                walktype: [
                    walk_types.TRAIL,
                    walk_types.NEIGHBORHOOD
                ],
                dog_type_options: dog_data['dogs'],
                first_name: '',
                last_name: '',
                number_of_dogs: '',
                dog_types: '',
                walk_types: '',
                bio: 'test',
                user_id: ''
            };

        },
        created() {
            firebase.auth().onAuthStateChanged(user => {
                this.user_id = user ? user.uid : null;
            });
        },
        methods: {
            updateUser: function () {
                serverUpdateUser(this)
            }
        }
    }

    function serverUpdateUser(that) {
        axios.put(`https://localhost:8090/api/v1/users/${that.user_id}`, {
            firstName: that.first_name,
            lastName: that.last_name,
            birthday: that.birthday,
            bio: that.bio,
            dogTypes: [that.dog_types],
            walkTypes: [that.walk_types],
        })
            .then(() => alert("Profile updated"))
            .catch(err => alert("Failed to update " + err))
    }
</script>
<style>
    .EditProfile {
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

    input {
        width: 60%;
        height: 45px;
        padding-left: 15px;
    }

    date-picker {
        width: 80%;
    }

    button {
        cursor: pointer;
    }
</style>