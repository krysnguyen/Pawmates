<template>
    <div class="here-map">
        <div ref="map" v-bind:style="{ width: width, height: height }"></div>
    </div>
</template>

<script>
    
    export default {
        name: "HereMap",
        data() {
            return {
                map: {},
                platform: {}
            }
        },
        props: {
            appId: String,
            appCode: String,
            lat: String,
            lng: String,
            width: String,
            height: String
        },
        created() {
            // eslint-disable-next-line
            this.platform = new H.service.Platform({
                'app_id': "ui9WRGl7eFkoMFuGEed3",
                apikey: '_pbd0lc2y8nctrFKjEfiXs4I3-E611GPNW7rYcmkamA'
            });
        },
        mounted() {
            this.map = new H.Map(
                this.$refs.map,
                this.platform.createDefaultLayers().vector.normal.map,
                {
                    zoom: 10,
                    center: { lng: this.lng, lat: this.lat }
                }
            );
        }
    }
</script>

<style scoped></style>