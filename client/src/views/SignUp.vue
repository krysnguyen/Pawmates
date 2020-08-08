<template>
<div class="SignUp">
    <h1>CREATE ACCOUNT</h1>
    <div class="container-sm h-100 text-left ">
        <!-- <div class="col-md-8 order-md-1"> -->
        <form>
            <div class="row">
                <div class="col-md-6">
                    <label class="col-form-label-lg">First Name</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="first_name" name="firstName"/>
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
                        <input type="text" class ="form-control" v-model="last_name" name="lastName"/>
                        <i class="fas fa-user fa-lg fa-lw"></i>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label class="col-form-label-lg">Birthday 'YYYY-MM-DD'</label>
                    <date-picker v-model="birthDate" type="date"></date-picker>
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
                    <label class="col-form-label-lg">Pet's Name</label>
                    <div class="inputWithIcon">
                        <input type="text" class ="form-control" v-model="pet_name" name="petName"/>
                        <i class="fas fa-dog fa-lg fa-lw"></i>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label class="col-form-label-lg">Profile Photo</label>
                    <input type="file" @change="onFileSelected">
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <label class="col-form-label-lg">Password</label>
                    <div class="inputWithIcon">
                        <input type="password" class ="form-control" v-model="password" name="password"/>
                        <i class="fas fa-key fa-lg fa-lw"></i>
                    </div>
                </div>
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
    import firebase from 'firebase/app';
    import 'firebase/auth';
    import DatePicker from 'vue2-datepicker';
    import 'vue2-datepicker/index.css';
    import {fb} from '../main';
    import {DropDownListPlugin} from '@syncfusion/ej2-vue-dropdowns';
    import * as dog_data from '../dogs.json';
    import axios from "axios";

    Vue.use(DropDownListPlugin);

    export default {
        name: 'SignUp',
        components: {
            DatePicker
        },
        data() {
            return {
                first_name: '',
                last_name: '',
                email: '',
                password: '',
                birthday: '',
                selectedFile: null,
                dog_type: '',
                dog_type_options: dog_data['dogs'],
                pet_name: ''
            }
        },
        methods: {
            signUp: function () {
                firebase.auth().createUserWithEmailAndPassword(this.email, this.password)
                    .then(res => serverCreateUser(this, res.user.uid))
                    .catch(err => alert('Sign up not successful' + err.message));
            },
            onFileSelected(event) {
                let file = event.target.files[0];
                const storageRef = fb.storage().ref('users/' + file.name);
                storageRef.put(file);
            }

        }
    }

    function serverCreateUser(that, userId) {
        axios.post('http://localhost:8090/api/v1/users', {
            firstName: that.first_name,
            lastName: that.last_name,
            email: that.email,
            birthday: that.birthday,
            dogType: [that.dog_type],
            userId: userId
        })
            .then(() => this.$router.replace('/match'))
            .catch(error => window.alert("Error creating user" + error));
    }
</script>

<style scoped lang="scss">
    .SignUp {
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
