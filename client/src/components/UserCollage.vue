<template>
    <div id="collage">
        <collage
                :images="images"
                :collageSizeMin="1"
                :collageSizeMax="5"
                :showNoImagesMsg="true"
                noImagesMsg="No Images"
                :showLoadingMsg="true"
                loadingMsg="Loading..."
                height="500%"
        >
        </collage>
    </div>
</template>

<script>
    import Collage from 'vue-collage';

    export default {
        components: {
            Collage
        },
        data() {
            return {
                images: [
                    {image: "https://wallpapershome.com/images/pages/pic_v/5111.jpg"},
                    {image: "https://thumbor.forbes.com/thumbor/1280x868/https%3A%2F%2Fblogs-images.forbes.com%2Fannabel%2Ffiles%2F2018%2F02%2FLouisville_Skyline-1200x801.jpg"},
                    {image: "https://t-ec.bstatic.com/images/hotel/max1024x768/683/68345284.jpg"},
                    {image: "https://i.pinimg.com/originals/08/ec/94/08ec94ecf38048a2102ec4783dc88fa8.jpg"},
                    {image: "https://t-ec.bstatic.com/images/hotel/max1024x768/873/87316855.jpg"}

                ]
            }
        }
    };
</script>

<style type="text/css">
</style>