// The Secure view is used just to display that the log in was successful

<template>
    <div id="secure">
        <h1>Secure Area</h1>
        <p>
            Congrats!! You have successfully logged in
        </p>
    </div>
</template>

<script>
    export default {
        name: 'Secure',
        data() {
            return {};
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
