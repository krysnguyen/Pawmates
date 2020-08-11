<template>
    <div id="main-div">
        <UserCollage />
    </div>
</template>

<script>
    import UserCollage from "./UserCollage";
    export default {
        name: "MainArea",
        components: {UserCollage}
    }
</script>

<style type="scss" scoped>
    #main-div {
        background-color: #42b983;
    }
</style>