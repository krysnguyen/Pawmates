<template>
    <b-container fluid="xl">
        <b-row align-v="stretch">
            <b-col>
                <Sidebar/>
            </b-col>
            <b-col cols="6" md="4">
                <b-button>Like</b-button>
                <b-button>Dislike</b-button>
            </b-col>
            <b-col cols="10" md="5">
                <MainArea/>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import Sidebar from "../components/Sidebar";
    import MainArea from "../components/MainArea";
    import firebase from 'firebase/app'
    import 'firebase/auth'
    import 'firebase/firestore'
    import 'firebase/storage'

    export default {
        components: {MainArea, Sidebar},
        created(){
            var user = firebase.auth().currentUser;
            this.name = user.uid;
        }
    }
</script>

<style>
    .wrapper {
        display: grid;
        grid-template-columns: 200px 200px;
    }
</style>