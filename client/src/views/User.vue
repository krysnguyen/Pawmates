<template>
    <div>
        <h1>{{this.first_name + ' ' + this.last_name}}</h1>
        <p>{{this.bio}}</p>
        <p>{{this.number_of_dogs}}</p>
        <p>{{this.dog_types}}</p>
        <p>{{this.walk_types}}</p>
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
            })
    }
</script>