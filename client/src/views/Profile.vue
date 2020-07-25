<template>
    <div class="container-fluid h-100 ">
        <div class="row h-100">
            <div class="col-md-9 h-100 d-flex justify-content-center align-items-center">
                <div class="col-md-8 rounded px-5 py-4 shadow bg-white text-left">
                <div id = "profile">
                    <h1><b>Profile</b></h1>
                    <div  class="row">
                        <div class="form-group">
                            <img src="../assets/puppy.jpg" height="150px" width="150px"/>

                            <!-- <input type="file" @change="previewImage" accept="image/*" class ="form-control"/>
                            <img class="preview" :src="picture"/>
                            <button @click="onUpload">Upload</button> -->
                        </div>    
                        <div class="col-12 form-group">
                            <label class="col-form-label-lg">How many dog(s) do you have ?</label>
                            <input type="text" v-model="input.dognumber" name="dognumber"/>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label col-form-label-lg">What kind of dogs do you have ?</label>
                            <!-- <input type="text" name="walktype" v-model="input.walktype" /> -->
                            <ejs-dropdownlist id='dropdownlist1' :dataSource="dogkind" :fields="localDogkind"
                            v-model='dogkind'
                            placeholder='Select the kind of dogs' popupWidth= "450px"
                            popupHeight= '200px'></ejs-dropdownlist>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label col-form-label-lg">What kind of walks do you prefer?</label>
                            <!-- <input type="text" name="walktype" v-model="input.walktype" /> -->
                            <ejs-dropdownlist id='dropdownlist2' :dataSource="walktype" :fields="localWalktype"
                            v-model='walktype'
                            placeholder='Select the kind of walk' popupWidth= "450px" 
                            popupHeight= "200px" sortOrder="Ascending"></ejs-dropdownlist>
                        </div>
                        <div class="col-12 form-group">
                            <label class="col-form-label col-form-label-lg">Is there anything interesting about you that you would like to share ?</label>
                            <textarea class="form-control" rows="10" type="text" name="aboutyourself" v-model="input.aboutyourself"></textarea>
                        </div>
                        <div class="col-12 form-group text-center">
                            <button type="button" v-on:click="save()">Save</button>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    // import Firebase from 'firebase';
    import Vue from 'vue';
    import {DropDownListPlugin} from '@syncfusion/ej2-vue-dropdowns';
    Vue.use(DropDownListPlugin);
    import db from '@/main';
    import * as data from '../dogs.json';
    export default {
        name: 'Profile',
        data() {
            return {
                imagePreview:'../assets/puppy.jpg',
                walktype: [
                    {'Id': 'Type1', 'type': 'Hike'},
                    {'Id': 'Type2', 'type': 'Trail'},
                    {'Id': 'Type3', 'type': 'Park'}
                ],
                localWalktype:{
                    value: 'Id',
                    text: 'type'
                },
                dogkind: data['dogs'],
                localDogkind:{
                    text: 'Type'
                },
                input: {
                    dognumber: '',
                    dogtype: '' ,
                    walktype: '' ,
                    aboutyourself: ''
                }
            };
        },
        
        methods: {
            save(){
                // var user = firebase.auth().currentUser;
                // this.email = user.email;
                if(this.input.dognumber != "" && this.walktype != "") {
                    console.log(this.walktype)
                    const user = {
                        dognumber: this.input.dognumber,
                        dogtype: this.dogkind,
                        walktype: this.walktype,
                        aboutyourself: this.input.aboutyourself
                    }
                    db.collection("users").add(user).then(()=>{
                        console.log("Added")
                    })
                }
                else {
                        console.log("Please fill in the required question");
                }
            }
            // previewImage(event) {
            //     this.uploadValue=0;
            //     this.picture=null;
            //     this.imageData = event.target.files[0];
            // },

            // onUpload(){
            //     this.picture=null;
            //     const storageRef=storage.ref(`${this.imageData.name}`).put(this.imageData);
            //     storageRef.on(`state_changed`,snapshot=>{
            //         this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
            //     }, error=>{console.log(error.message)},
            //     ()=>{this.uploadValue=100;
            //         storageRef.snapshot.ref.getDownloadURL().then((url)=>{
            //         this.picture =url;
            //         });
            //     }
            //     )
            // }
        }
    }
</script>
<style>
@import url(https://cdn.syncfusion.com/ej2/material.css);
</style>