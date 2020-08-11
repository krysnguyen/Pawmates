<template>
    <div class="user-card">
        <b-card
                :img-src="images.length > 0 ? images[0] : defaultImage"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 400px; border-radius: 36px;"
                bg-variant="light"
                class="text-left mt-3 mb-3">

            <div class="card-body pt-2 pb-2">
                <h3>{{this.first_name + ' ' + this.last_name + ', ' + this.age}}</h3>
                <p 
                style="color:rgb(112, 112, 112);" 
                v-if="this.bio!=null && this.bio!=''">
                    {{this.bio}}
                </p>

                <h4>{{this.dog_name + ', ' + this.dog_types[0]}}</h4>
                <p 
                style="color:rgb(112, 112, 112);"
                v-if="this.walk_types != null && this.walk_types.length > 0">
                    Walk preference: {{this.walk_types[0]}}
                </p>
            </div> 

            <div class="text-center">
                <b-button v-on:click="goBack()" variant="primary">Go Back</b-button>
            </div>

           
        </b-card>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'User',
        data() {
            return {
                first_name: '',
                last_name: '',
                bio: '',
                gender: '',
                birthday: '',
                dog_name: '',
                age: '',
                dog_types: '',
                walk_types: '',
                images: [],
                defaultImage: 'https://firebasestorage.googleapis.com/v0/b/pawmates-71be7.appspot.com/o/puppy.jpg?alt=media&token=7ee37f7b-bc88-47ed-8db0-ee256beaf906',
            }
        },
        created() {
            serverGetUser(this.$route.params.id, this);
        },
        methods: {
            goBack() {
                this.$router.go(-1);
            }
        }
    }

    function serverGetUser(userId, that) {
//        console.log(userId);
        axios.get('http://localhost:8090/api/v1/users/' + userId)
            .then(res => {
                that.first_name = res.data.firstName;
                that.last_name = res.data.lastName;
                that.bio = res.data.bio;
                that.birthDate = res.data.birthday;
                that.number_of_dogs = res.data.numberOfDogs;
                that.dog_types = res.data.dogTypes;
                that.walk_types = res.data.walkTypes;
                that.dog_name = res.data.dogName;
                that.age = res.data.age;
                that.images = res.data.images;
            })
    }
</script>

<style scoped>
    .user-card .card {
        padding: 10px;
        border: 10px;
        margin: 0 auto 10px; 
      }
    .user-card .card-img-top {
        max-width: 325px;
        display: block;
        margin-left: auto;
        margin-right: auto;
        padding-top: 10px;
    }
</style>