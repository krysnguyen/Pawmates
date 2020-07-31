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

    export default {
        components: {MainArea, Sidebar}
    }
</script>

<style>
    .wrapper {
        display: grid;
        grid-template-columns: 200px 200px;
    }
</style>