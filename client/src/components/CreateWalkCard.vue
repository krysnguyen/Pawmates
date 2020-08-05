<template>
  <div class="create-walk-card">
    <b-card class="mb-3">
      <b-card-title title="Create a walk" class="mb-0"></b-card-title>
      <b-link to="/createwalk" class="stretched-link card-link">
      </b-link>
    </b-card>
  </div>
</template>

<script>
  export default {
    name: 'CreateWalkCard'
  }
</script>

<style lang="scss" scoped>
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
</style>
