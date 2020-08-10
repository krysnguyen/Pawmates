<template>
    <div class="home">
        <header class="masthead mb-3">
            <!--     https://unsplash.com/photos/T-0EW-SEbsE-->

            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-md-10 mx-auto">
                        <div class="site-heading">
                            <h1>Pawmates</h1>
                            <span class="subheading">Where your best friend can make you another best friend.</span>
                        </div>
                        <span class="scroll-down">Scroll down to see what you can do with Pawmates.</span>
                    </div>
                </div>
            </div>
        </header>

        <b-container class="home-cards">
            <b-row align-h="start">
                <b-col sm="12" md="8">
                    <b-card no-body class="overflow-hidden mb-3" style="max-width: 565px;" data-aos="fade-right">
                        <b-row no-gutters>
                            <b-col md="6">
                                <b-card-img src="../assets/dog1.jpg" alt="Image" class="rounded-0"></b-card-img>
                                <!--                https://unsplash.com/photos/NqNa_ZlYTHM-->
                            </b-col>
                            <b-col align-self="center" md="6">
                                <b-card-body title="Make an account for you and your dog">
                                    <b-card-text>
                                        Share about yourself and upload your dog photos!
                                    </b-card-text>
                                </b-card-body>
                            </b-col>
                        </b-row>
                    </b-card>
                </b-col>
            </b-row>
            <b-row align-h="end">
                <b-col sm="12" md="8">
                    <b-card no-body class="overflow-hidden mb-3 float-right" style="max-width: 565px;"
                            data-aos="fade-left" data-aos-offset="155">
                        <b-row no-gutters>
                            <b-col align-self="center" md="6">
                                <b-card-body title="Find dog owners who want to hang out">
                                    <b-card-text>
                                        Like and dislike profiles in order to match with people!
                                    </b-card-text>
                                </b-card-body>
                            </b-col>
                            <b-col md="6">
                                <b-card-img src="../assets/dog2.jpg" alt="Image" class="rounded-0"></b-card-img>
                                <!--                https://unsplash.com/photos/qmT4p4gnLmg-->
                            </b-col>
                        </b-row>
                    </b-card>
                </b-col>
            </b-row>
            <b-row align-h="start">
                <b-col sm="12" md="8">
                    <b-card no-body class="overflow-hidden mb-3" style="max-width: 565px;" data-aos="fade-right"
                            data-aos-offset="170">
                        <b-row no-gutters>
                            <b-col md="6">
                                <b-card-img src="../assets/dog3.jpg" alt="Image" class="rounded-0"></b-card-img>
                                <!--                https://unsplash.com/photos/1tlUy6TBHW0-->
                            </b-col>
                            <b-col align-self="center" md="6">
                                <b-card-body title="Go on walks together">
                                    <b-card-text>
                                        Make or join walks with people you've matched with!
                                    </b-card-text>
                                </b-card-body>
                            </b-col>
                        </b-row>
                    </b-card>
                </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
    export default {
        name: 'Home'
    }
</script>

<style lang="scss" scoped>

    /*  the font used by Pawmates title text:
      font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif; */

    header.masthead {
        /*    margin-bottom: 50px;*/
        position: relative;
        background-size: cover;
        background: #868e96 url('../assets/dogs-home.jpg') no-repeat scroll center center;
        /*      https://unsplash.com/photos/T-0EW-SEbsE*/
    }

    header.masthead .overlay {
        position: absolute;
        top: 0;
        left: 0;
        height: 100%;
        width: 100%;
        background-color: #212529;
        opacity: 0.5;
    }

    header.masthead .site-heading {
        padding: 200px 0 150px;
        color: white;
    }

    @media only screen and (min-width: 768px) {
        header.masthead .site-heading {
            padding: 200px 0 175px;
        }
    }

    header.masthead .site-heading {
        text-align: center;
    }

    header.masthead .site-heading h1 {
        font-weight: 800;
        font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
        font-size: 50px;
        margin-top: 0;
    }

    header.masthead .site-heading .subheading, .scroll-down {
        font-size: 24px;
        font-weight: 300;
        line-height: 1.1;
        display: block;
        margin: 10px 0 0;
        font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
    }

    .scroll-down {
        margin-bottom: 15px;
        font-size: 22px;
        color: white;
    }

    .home-cards .card-text {
        padding-top: 1em;
    }

    @media only screen and (min-width: 768px) {
        header.masthead .site-heading h1 {
            font-size: 80px;
        }
    }


</style>
