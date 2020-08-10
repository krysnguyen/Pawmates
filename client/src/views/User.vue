<template>
    <div class="user-card">
        <b-card
                img-src="https://picsum.photos/600/300/?image=25"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 40rem;"

                bg-variant="light"
                class="text-left">
                <!--Human card body -->
            <div class="card-body">
                <h3>{{this.first_name + ' ' + this.last_name + ', ' + getAge()}}</h3>
                <p style="color:rgb(112, 112, 112);">{{this.bio}}</p>
                <!-- Add later if messages is not finsihed <p>{{this.phoneNumber}}</p> -->
            </div> 
            <div class="card-body">
                <h4>{{this.dog_name + ', ' + this.dog_types[0]}}</h4>
                <!--<h5 class="card-title">{{this.number_of_dogs}}</h5> -->
                <p style="color:rgb(112, 112, 112);"> Walk preference: {{ this.walk_types[0]}}</p>
            </div> 
<!--dog name, breed, types of walks -->
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
                birthdate: '',
                dog_name: '',
                //number_of_dogs: '',
                dog_types: '',
                walk_types: ''
            }
        },
        created() {
            serverGetUser(this.$route.params.id, this);
        },
        methods: {
            goBack() {
                this.$router.go(-1);
            },
            getAge() {
                const dateString = "1998-05-01";
                var today = new Date();
                var birthDate = new Date(dateString);
                var age = today.getFullYear() - birthDate.getFullYear();
                var m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }
                return age; 
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
                console.log(res);
            })
    }
</script>

<style>
    .card {
        /* Added */
        float: none; /* Added */
        /* Added */
        margin: 0 auto 10px;
        padding: 10px;
        border: 10px;
        box-shadow: 0px 0px 15px #666
    }

</style>