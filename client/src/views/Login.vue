<template>
    <div class="login" v-on:keyup.enter="login">
        <h1>Login</h1>
        <div class="container-sm h-100">
            <label class="col-form-label-lg">Email Address</label>
            <div class="inputWithIcon">
                <input type="text" class ="form-control" v-model="email" name="userEmail"/>
                <i class="fas fa-envelope fa-lg fa-lw"></i>
            </div>
            <label class="col-form-label-lg">Password</label>
            <div class="inputWithIcon">
                <input type="password" class ="form-control" v-model="password" name="password"/>
                <i class="fas fa-key fa-lg fa-lw"></i>
            </div>
        </div>
            <b-button @click="login">Login</b-button>
        <p><br> Don't have an account?
            <b-link to="/sign-up">Create one.</b-link>
        </p>
    </div>
</template>

<script>
    import firebase from 'firebase/app';
    import 'firebase/auth';

    export default {
        name: 'login',
        data() {
            return {
                email: '',
                password: ''
            };
        },
        methods: {
            login: function () {
                firebase.auth()
                    .signInWithEmailAndPassword(this.email, this.password)
                    .then(() => this.$router.push('match'))
                    .catch(err => alert('Login not successful. ' + err.message));
            }
        }
    }
</script>

<style scoped>
    .login {
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
