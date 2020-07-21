<template>
    <div class="sign-up">
        <p>create new account</p>
        <input type="text" placeholder="email"><br>
        <input type="password" placeholder="password"><br>
        <button>Sign Up</button>
    </div>
</template>

<script>
    export default {
        name: 'SignUp',
        data() {
            return {}
        },
        methods: {}
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
