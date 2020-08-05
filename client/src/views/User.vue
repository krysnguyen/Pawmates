<template>
    <div>
        <b-card
                img-src="https://picsum.photos/600/300/?image=25"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 40rem;"

                bg-variant="light"
                class="text-center">

            <b-card-title>
                {{this.first_name + ' ' + this.last_name}}
            </b-card-title>

            <b-card-text>
                Has {{this.number_of_dogs}} dogs
            </b-card-text>

            <b-card-text>
                {{this.dog_types}}
            </b-card-text>

            <b-card-text>
                Likes walking on {{this.walk_types}}s
            </b-card-text>

            <b-card-text>
                Bio: {{this.bio}}
            </b-card-text>

            <b-button v-on:click="goBack()" variant="primary">Go Back</b-button>
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
                number_of_dogs: '',
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
                that.number_of_dogs = res.data.numberOfDogs;
                that.dog_types = res.data.dog_types;
                that.walk_types = res.data.walkTypes;
                console.log(res);
            })
    }
</script>

<style>
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
        padding: 10px;
        border: 10px;
        box-shadow: 0px 0px 15px #666
    }

</style>