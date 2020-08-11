<template>
  <div class="create-walk-card">
    <b-card no-body class="overflow-hidden mb-3" style="max-height: 150px;">
      <b-link to="/createwalk" class="stretched-link card-link">
        <b-row class="row-in-card" no-gutters>
          <b-col cols="2" align-self="center">
            <b-card-img :src="image" alt="Profile image" class="rounded-circle" style="max-height: 120px; max-width: 120px"></b-card-img>
          </b-col>
          <b-col cols="10" align-self="center">
            <b-card-title class="mb-0" title="Create a walk">
            </b-card-title>
            <b-card-sub-title sub-title="(should have user's image to the left, and then delete this text)"></b-card-sub-title>
          </b-col>
        </b-row>
      </b-link>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: 'CreateWalkCard',
    props: ['image']
//    old placeholder image: "https://placedog.net/150/150?id=15"
  }
</script>

<style lang="scss" scoped>
  
  .create-walk-card .card-title {
    font-size: 1.75rem;
  }
  
  .card {
    box-shadow: 0px 0px 15px #bbb;
  }
  .card:hover {
    box-shadow: 0px 0px 15px #666;
  }
  .create-walk-card .card-link,
  .create-walk-card .card-link:hover {
    color: inherit;
  }
  .create-walk-card .card {
    padding: 10px;
    border: 10px;
    margin: 0 auto 10px; 
  }
</style>
