<script src="https://www.gstatic.com/firebasejs/7.16.1/firebase-app.js"></script>
<template>
    <div class="login">
        <h1>Login</h1>
        <input type="text" v-model="email" placeholder="Username"><br>
        <input type="password" v-model="password" placeholder="Password"><br>
        <button @click = "login">Connection</button>
        <p> You don't have an account? You can create one</p>
    </div>
</template>

<script>
    import firebase from 'firebase';

    export default {
        name: 'login',
        data() {
            return {
                email: '',
                password: '' 
            };  
        },
        methods: {
            login: function() {
                firebase.auth().signInWithEmailAndPassword(this.email, this.password).then(
                    function(user){
                    alert('You are now connected')
                    },
                    function(err){
                        alert('oops' + err.message)
                    }
                );
            }
        }
    }
</script>

<style scoped>

</style>    

