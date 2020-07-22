<script src="https://www.gstatic.com/firebasejs/7.16.1/firebase-app.js"></script>
<template>
    <div class="sign-up">
        <p>create new account</p>
        <input type="text" v-model="email" placeholder="email"><br>
        <input type="password" v-model="password" placeholder="password"><br>
        <button @click="SignUp">Sign Up</button>
    </div>
</template>

<script>
    import firebase from 'firebase';
    
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
                    function(user){
                        alert('successfully created account')
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
