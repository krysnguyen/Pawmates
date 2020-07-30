// The Secure view is used just to display that the log in was successful

<template>
    <div id="secure">
        <h1>Secure Area</h1>
        <p>
            Congrats!! You have successfully logged in
        </p>
        <button @click="logout">Logout</button>
    </div>
</template>

<script>
    import firebase from 'firebase/app';
    import 'firebase/auth';

    export default {
        name: 'Secure',
        data() {
            return {};
        },
        methods: {
            logout: function() {
                firebase.auth().signOut().then(() => {
                    this.$router.replace('login')
                })
            }
        }
    }
</script>

<style scoped>
    #secure {
        background-color: #FFFFFF;
        border: 1px solid #CCCCCC;
        padding: 20px;
        margin-top: 10px;
    }
</style>
