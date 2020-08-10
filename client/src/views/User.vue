<template>
    <div class="user-card">
        <b-card
                :img-src="[images.length > 0 ? images[0] : this.$defaultImage]"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 40rem;"

                bg-variant="light"
                class="text-left">

            <div class="card-body">
                <h3>{{this.first_name + ' ' + this.last_name + ', ' + this.age}}</h3>
                <p style="color:rgb(112, 112, 112);">{{this.bio}}</p>
            </div> 
            <div class="card-body">
                <h4>{{this.dog_name + ', ' + this.dog_types[0]}}</h4>
                <p style="color:rgb(112, 112, 112);"> Walk preference: {{ this.walk_types[0]}}</p>
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
                images: []
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
        console.log(userId);
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