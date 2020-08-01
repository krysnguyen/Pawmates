<script src="https://www.gstatic.com/firebasejs/7.16.1/firebase-app.js"></script>
<template>
    <div class="SignUp">
        <h1>Sign-Up</h1>
        <input type="text" v-model="email" placeholder="Email"><br>
        <input type="password" v-model="password" placeholder="Password"><br>
        <button @click="SignUp">Sign Up</button>
    </div>
</template>

<script>
    import firebase from 'firebase/app';
    import 'firebase/auth';

    export default {
        name: 'SignUp',
        data() {
            return {
            email: '',
            password: ''
            }
        },
        methods: {
            SignUp: function() {
                firebase.auth().createUserWithEmailAndPassword(this.email, this.password).then(
                    (user) => {
                        this.$router.replace('match')
                    },
                    function(err){
                        alert('Sign up not successful' + err.message)
                    }
                );
        }
    }
}
</script>

<style scoped>
    .SignUp {
        margin-top: 40px;
    }
    input {
        margin: 10px 0;
        width: 20%;
        padding: 15px;
    }
    button {
        margin-top: 10px;
        width: 10%;
        cursor: pointer;
    }
    span {
        display: block;
        margin-top: 20px;
        font-size: 11px;
    }
</style>
